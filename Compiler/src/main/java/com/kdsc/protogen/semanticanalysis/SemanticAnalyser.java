package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.parsetree.*;
import com.kdsc.protogen.parsetree.utils.ParseTreeUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.kdsc.protogen.semanticanalysis.SemanticErrorFactory.createSemanticError;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.*;

public class SemanticAnalyser {

    public static List<SemanticError> runSemanticAnalysis(final List<FileNode> fileNodes) {

        var returnSemanticErrors = new ArrayList<SemanticError>();

        checkRedefinitionOfObject(returnSemanticErrors, fileNodes);

        var typeNodes = fileNodes
            .stream()
            .flatMap(fn -> fn.getProtoGenTypeNodes().stream())
            .collect(Collectors.toMap(tn -> ParseTreeUtils.getNamespaceNameString(tn.getNamespaceNameNode()), tn -> tn, (tn1, tn2) -> tn1));

        var keyNodes = fileNodes
            .stream()
            .flatMap(fn -> fn.getProtoGenEnumNodes().stream())
            .collect(Collectors.toMap(kn -> ParseTreeUtils.getNamespaceNameString(kn.getNamespaceNameNode()), kn -> kn, (kn1, kn2) -> kn2));

        var enumNodes = fileNodes
            .stream()
            .flatMap(fn -> fn.getProtoGenEnumNodes().stream())
            .collect(Collectors.toMap(en -> ParseTreeUtils.getNamespaceNameString(en.getNamespaceNameNode()), en -> en, (en1, en2) -> en2));

        checkTypes(returnSemanticErrors, typeNodes);
        checkEnums(returnSemanticErrors, enumNodes);

        return returnSemanticErrors
            .stream()
            .sorted(
                Comparator.comparing(SemanticError::sourceFileName)
                    .thenComparing(SemanticError::line)
                    .thenComparing(SemanticError::charPosition)
            )
            .collect(Collectors.toList());
    }

    private static void checkRedefinitionOfObject(final List<SemanticError> semanticErrors, final List<FileNode> fileNodes) {

        var topLevelObjects = fileNodes
            .stream()
            .flatMap(
                fn -> Stream.of(
                    fn.getProtoGenTypeNodes().stream(),
                    fn.getProtoGenKeyNodes().stream(),
                    fn.getProtoGenEnumNodes().stream()
                ).flatMap(s -> s)
            )
            .collect(Collectors.toList());

        var topLevelObjectNamespaceNamesAsString = new HashSet<String>();

        topLevelObjects
            .forEach(
                tln -> {
                    var namespaceNameAsString = ParseTreeUtils.getNamespaceNameString(tln.getNamespaceNameNode());
                    if(topLevelObjectNamespaceNamesAsString.contains(namespaceNameAsString)) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_OBJECT, tln, namespaceNameAsString));
                    } else {
                        topLevelObjectNamespaceNamesAsString.add(namespaceNameAsString);
                    }
                }
            );
    }

    private static void checkTypes(final List<SemanticError> semanticErrors, final Map<String, ProtoGenTypeNode> typeNodeMap) {
        typeNodeMap
            .values()
            .forEach(tn -> checkType(semanticErrors, typeNodeMap, tn));
    }

    private static void checkType(final List<SemanticError> semanticErrors, final Map<String, ProtoGenTypeNode> typeNodeMap, final ProtoGenTypeNode typeNode) {

        //TODO:KMD Generic parameters and bounds
        //TODO:KMD Inheritance loop

        var genericParameters = typeNode
            .getNamespaceNameGenericParametersWithBoundsNode()
            .getGenericParametersWithBoundsNode()
            .stream()
            .flatMap(gpwb -> gpwb.getGenericParameterWithBoundsNodes().stream())
            .collect(Collectors.toList());

        var genericParametersSet = new HashSet<String>();
        genericParameters
            .forEach(
                gp -> {
                    if(genericParametersSet.contains(gp.getIdentifier())) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_GENERIC_PARAMETER, gp, ParseTreeUtils.getNamespaceNameString(typeNode.getNamespaceNameNode()), gp.getIdentifier()));
                    } else {
                        genericParametersSet.add(gp.getIdentifier());
                    }
                }
            );

        typeNode
            .getNamespaceNameGenericParametersWithBoundsNode()
            .getGenericParametersWithBoundsNode()
            .stream()
            .flatMap(gpwb -> gpwb.getGenericParameterWithBoundsNodes().stream())
            .forEach(
                gpwb -> {
                    var namespaceNameAsStringSet = new HashSet<String>();
                    gpwb
                        .getNamespaceNameGenericParametersWithoutBoundsNodes()
                        .forEach(
                            nngpwb -> {
                                if(!typeNodeMap.containsKey(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode()))) {
                                    semanticErrors.add(createSemanticError(GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE, nngpwb, gpwb.getIdentifier(), ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode())));
                                } else if(namespaceNameAsStringSet.contains(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode()))) {
                                    semanticErrors.add(createSemanticError(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES, nngpwb, gpwb.getIdentifier(), ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode())));
                                } else {
                                    namespaceNameAsStringSet.add(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode()));
                                }
                            }
                        );
                }
            );

        if(typeNode.getImplementsListNode().isPresent()) {
            typeNode
                .getImplementsListNode()
                .get()
                .getNamespaceNameGenericParametersWithoutBoundsNodes()
                .forEach(
                    nngp -> {
                        var namespaceNameAsString = ParseTreeUtils.getNamespaceNameString(nngp.getNamespaceNameNode());
                        if(!typeNodeMap.containsKey(namespaceNameAsString)) {
                            semanticErrors.add(createSemanticError(TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST, nngp, ParseTreeUtils.getNamespaceNameString(typeNode.getNamespaceNameNode()), namespaceNameAsString));
                        } else {
                            var numberOfGenericParametersOnImplementsListDefinition = nngp.getGenericParametersWithoutBoundsNode().isPresent()
                                ? nngp.getGenericParametersWithoutBoundsNode().get().getGenericParameterWithoutBoundsNodes().size()
                                : 0;

                            var implementsTypeDefinition = typeNodeMap.get(namespaceNameAsString);
                            var numberOfGenericParametersOnTypeDefinition = implementsTypeDefinition.getNamespaceNameGenericParametersWithBoundsNode().getGenericParametersWithBoundsNode().isPresent()
                                ? implementsTypeDefinition.getNamespaceNameGenericParametersWithBoundsNode().getGenericParametersWithBoundsNode().get().getGenericParameterWithBoundsNodes().size()
                                : 0;

                            if(numberOfGenericParametersOnImplementsListDefinition != numberOfGenericParametersOnTypeDefinition) {
                                semanticErrors.add(createSemanticError(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION, nngp, numberOfGenericParametersOnImplementsListDefinition, ParseTreeUtils.getNamespaceNameString(nngp.getNamespaceNameNode()), numberOfGenericParametersOnTypeDefinition));
                            }
                            //TODO:KMD Check bounds match
                        }
                        nngp
                            .getGenericParametersWithoutBoundsNode()
                            .stream()
                            .flatMap(nngpwb -> nngpwb.getGenericParameterWithoutBoundsNodes().stream())
                            .forEach(
                                gpwb -> {
                                    if(!genericParametersSet.contains(gpwb.getIdentifier())) {
                                        semanticErrors.add(createSemanticError(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE, gpwb, gpwb.getIdentifier(), ParseTreeUtils.getNamespaceNameString(typeNode.getNamespaceNameNode())));
                                    }
                                }
                            );
                    }
                );



            var nonInterfaceTypeNodes = typeNode
                .getImplementsListNode()
                .stream()
                .flatMap(iln -> iln.getNamespaceNameGenericParametersWithoutBoundsNodes().stream())
                .filter(nngpwb -> typeNodeMap.containsKey(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode())))
                .filter(nngpwb -> !typeNodeMap.get(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode())).isInterface())
                .collect(Collectors.toList());

            if(nonInterfaceTypeNodes.size() > 1) {
                nonInterfaceTypeNodes
                    .forEach(
                        nngpn -> semanticErrors.add(createSemanticError(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE, nngpn, ParseTreeUtils.getNamespaceNameString(typeNode.getNamespaceNameNode()), ParseTreeUtils.getNamespaceNameString(nngpn.getNamespaceNameNode())))
                    );
            }
        }

        //TODO:KMD Test number and types of generic parameters

        var versions = typeNode
            .getVersionsNode()
            .stream()
            .flatMap(vn -> vn.getVersionNodes().stream())
            .sorted(Comparator.comparing(vn -> vn.getVersionNumberNode().getVersionNumber()))
            .collect(Collectors.toList());

        var versionNumbersSet = new HashSet<Long>();
        versions
            .stream()
            .map(VersionNode::getVersionNumberNode)
            .forEach(
                vn -> {
                    if(versionNumbersSet.contains(vn.getVersionNumber())) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_TYPE_VERSION, vn, vn.getVersionNumber()));
                    } else {
                        versionNumbersSet.add(vn.getVersionNumber());
                    }
                }
            );
    }

    private static void checkEnums(final List<SemanticError> semanticErrors, final Map<String, ProtoGenEnumNode> enumNodeMap) {
        enumNodeMap
            .values()
            .forEach(en -> checkEnum(semanticErrors, en));
    }

    private static void checkEnum(final List<SemanticError> semanticErrors, final ProtoGenEnumNode enumNode) {
        var versions = enumNode
            .getEnumVersionsNode()
            .stream()
            .flatMap(evn -> evn.getEnumVersionNodes().stream())
            .sorted(Comparator.comparing(evn -> evn.getVersionNumberNode().getVersionNumber()))
            .collect(Collectors.toList());

        var versionNumbersSet = new HashSet<Long>();
        versions
            .stream()
            .map(EnumVersionNode::getVersionNumberNode)
            .forEach(
                vn -> {
                    if(versionNumbersSet.contains(vn.getVersionNumber())) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_ENUM_VERSION, vn, vn.getVersionNumber()));
                    } else {
                        versionNumbersSet.add(vn.getVersionNumber());
                    }
                }
            );

        versions
            .stream()
            .filter(evn -> evn.getEnumCasesNode().isPresent())
            .forEach(evn -> checkEnumCases(semanticErrors, evn.getEnumCasesNode().get()));

        if(enumNode.getEnumCasesNode().isPresent()) checkEnumCases(semanticErrors, enumNode.getEnumCasesNode().get());
    }

    private static void checkEnumCases(final List<SemanticError> semanticErrors, final EnumCasesNode enumCasesNode) {
        var enumNameSet = new HashSet<String>();
        enumCasesNode
            .getEnumNameNodes()
            .forEach(
                enn -> {
                    if(enumNameSet.contains(enn.getEnumName())) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_ENUM_CASE, enn, enn.getEnumName()));
                    } else {
                        enumNameSet.add(enn.getEnumName());
                    }
                }
            );
    }

}