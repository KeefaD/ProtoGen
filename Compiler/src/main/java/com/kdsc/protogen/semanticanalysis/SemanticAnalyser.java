package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.parsetree.*;
import com.kdsc.protogen.parsetree.fieldtypenodes.*;
import com.kdsc.protogen.parsetree.utils.ParseTreeUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.kdsc.protogen.semanticanalysis.SemanticErrorFactory.createSemanticError;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.*;

//TODO:KMD Redefinition of field in interface with different type
//TODO:KMD Generic parameters on outer type and versions
public class SemanticAnalyser {

    private record Objects(
        Map<String, ProtoGenTypeNode> typeNodeMap,
        Map<String, ProtoGenKeyNode> keyNodeMap,
        Map<String, ProtoGenEnumNode> enumNodeMap
    ) {
        private Objects {
            java.util.Objects.requireNonNull(typeNodeMap);
            java.util.Objects.requireNonNull(keyNodeMap);
            java.util.Objects.requireNonNull(enumNodeMap);
        }
    }

    public static List<SemanticError> runSemanticAnalysis(final List<FileNode> fileNodes) {

        var returnSemanticErrors = new ArrayList<SemanticError>();

        checkRedefinitionOfObject(returnSemanticErrors, fileNodes);

        var typeNodes = fileNodes
            .stream()
            .flatMap(fn -> fn.getProtoGenTypeNodes().stream())
            .collect(Collectors.toMap(tn -> ParseTreeUtils.getNamespaceNameString(tn.getNamespaceNameNode()), tn -> tn, (tn1, tn2) -> tn1));

        var keyNodes = fileNodes
            .stream()
            .flatMap(fn -> fn.getProtoGenKeyNodes().stream())
            .collect(Collectors.toMap(kn -> ParseTreeUtils.getNamespaceNameString(kn.getNamespaceNameNode()), kn -> kn, (kn1, kn2) -> kn2));

        var enumNodes = fileNodes
            .stream()
            .flatMap(fn -> fn.getProtoGenEnumNodes().stream())
            .collect(Collectors.toMap(en -> ParseTreeUtils.getNamespaceNameString(en.getNamespaceNameNode()), en -> en, (en1, en2) -> en2));

        var objects = new Objects(typeNodes, keyNodes, enumNodes);

        checkTypes(returnSemanticErrors, objects);
        checkEnums(returnSemanticErrors, objects);

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

    private static void checkTypes(final List<SemanticError> semanticErrors, final Objects objects) {
        objects.typeNodeMap
            .values()
            .forEach(tn -> checkType(semanticErrors, objects, tn));
    }

    private static void checkType(final List<SemanticError> semanticErrors, final Objects objects, final ProtoGenTypeNode typeNode) {

        checkInheritanceLoop(semanticErrors, objects, new HashSet<>(), "", typeNode, typeNode);

        //Generic Parameters
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
                        .getNamespaceNameGenericParametersNodes()
                        .forEach(
                            nngpwb -> {
                                if(!objects.typeNodeMap.containsKey(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode()))) {
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
            var versionsImplementsListNodes = typeNode
                .getVersionsNode()
                .stream()
                .flatMap(vn -> vn.getVersionNodes().stream())
                .flatMap(vn -> vn.getImplementsListNode().stream())
                .flatMap(il -> il.getNamespaceNameGenericParametersNodes().stream())
                .collect(Collectors.toList());
            versionsImplementsListNodes
                .forEach(nngp -> semanticErrors.add(createSemanticError(CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME, nngp, ParseTreeUtils.getNamespaceNameString(nngp.getNamespaceNameNode()))));
        }

        if(typeNode.getImplementsListNode().isPresent()) {
            typeNode
                .getImplementsListNode()
                .ifPresent(iln -> checkImplementsList(semanticErrors, objects, typeNode, genericParametersSet, iln));
        }

        typeNode
            .getVersionsNode()
            .stream()
            .flatMap(vn -> vn.getVersionNodes().stream())
            .flatMap(vn -> vn.getImplementsListNode().stream())
            //TODO:KMD This is wrong, it is using the wrong set of generic parameters
            .forEach(iln -> checkImplementsList(semanticErrors, objects, typeNode, genericParametersSet, iln));

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

        checkFields(semanticErrors, objects, typeNode.getFieldsNode());

        typeNode
            .getVersionsNode()
            .stream()
            .flatMap(vn -> vn.getVersionNodes().stream())
            .forEach(vn -> checkFields(semanticErrors, objects, vn.getFieldsNode()));

        typeNode
            .getImplementsListNode()
            .stream()
            .flatMap(iln -> iln.getNamespaceNameGenericParametersNodes().stream())
            .map(NamespaceNameGenericParametersNode::getGenericParametersNode)
            .flatMap(Optional::stream)
            .flatMap(gpn -> gpn.getFieldTypeNodes().stream())
            .forEach(ftn -> checkFieldType(semanticErrors, objects, ftn));

        typeNode
            .getVersionsNode()
            .stream()
            .flatMap(vn -> vn.getVersionNodes().stream())
            .flatMap(vn -> vn.getImplementsListNode().stream())
            .flatMap(iln -> iln.getNamespaceNameGenericParametersNodes().stream())
            .map(NamespaceNameGenericParametersNode::getGenericParametersNode)
            .flatMap(Optional::stream)
            .flatMap(gpn -> gpn.getFieldTypeNodes().stream())
            .forEach(ftn -> checkFieldType(semanticErrors, objects, ftn));
    }

    private static void checkImplementsList(final List<SemanticError> semanticErrors, final Objects objects, final ProtoGenTypeNode typeNode, final Set<String> genericParametersSet, final ImplementsListNode implementsListNode) {
        implementsListNode
            .getNamespaceNameGenericParametersNodes()
            .forEach(
                nngp -> {
                    var namespaceNameAsString = ParseTreeUtils.getNamespaceNameString(nngp.getNamespaceNameNode());
                    if(!objects.typeNodeMap.containsKey(namespaceNameAsString)) {
                        semanticErrors.add(createSemanticError(TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST, nngp, ParseTreeUtils.getNamespaceNameString(typeNode.getNamespaceNameNode()), namespaceNameAsString));
                    } else {
                        checkGenericParameters(semanticErrors, objects, typeNode, nngp);
                    }
                    var usedGenericParameterIdentifiers = nngp
                        .getGenericParametersNode()
                        .stream()
                        .flatMap(gp -> gp.getFieldTypeNodes().stream())
                        .flatMap(ftn -> getGenericParametersFieldTypeNode(ftn).stream())
                        .collect(Collectors.toList());
                    usedGenericParameterIdentifiers
                        .forEach(
                            i -> {
                                if(!genericParametersSet.contains(i.getGenericParameterNode().getIdentifier())) {
                                    semanticErrors.add(createSemanticError(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE, i, i.getGenericParameterNode().getIdentifier(), ParseTreeUtils.getNamespaceNameString(typeNode.getNamespaceNameNode())));
                                }
                            }
                        );
                }
            );

        var nonInterfaceTypeNodes = implementsListNode
            .getNamespaceNameGenericParametersNodes()
            .stream()
            .filter(nngpwb -> objects.typeNodeMap.containsKey(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode())))
            .filter(nngpwb -> !objects.typeNodeMap.get(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode())).isInterface())
            .collect(Collectors.toList());

        if(typeNode.isInterface()) {
            if(nonInterfaceTypeNodes.size() > 0) {
                nonInterfaceTypeNodes
                    .forEach(
                        nngpn -> semanticErrors.add(createSemanticError(EXTENDING_INTERFACE_WITH_NON_INTERFACE, nngpn, ParseTreeUtils.getNamespaceNameString(typeNode.getNamespaceNameNode()), ParseTreeUtils.getNamespaceNameString(nngpn.getNamespaceNameNode())))
                    );
            }
        } else {
            if(nonInterfaceTypeNodes.size() > 1) {
                nonInterfaceTypeNodes
                    .forEach(
                        nngpn -> semanticErrors.add(createSemanticError(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE, nngpn, ParseTreeUtils.getNamespaceNameString(typeNode.getNamespaceNameNode()), ParseTreeUtils.getNamespaceNameString(nngpn.getNamespaceNameNode())))
                    );
            }
        }
    }

    private static void checkInheritanceLoop(final List<SemanticError> semanticErrors, final Objects objects, final Set<String> alreadyVisitedTypes, final String path, final ProtoGenTypeNode initialTypeNode, final ProtoGenTypeNode typeNode) {
        var namespaceNameAsString = ParseTreeUtils.getNamespaceNameString(typeNode.getNamespaceNameNode());
        var newPath = path + namespaceNameAsString;
        if(alreadyVisitedTypes.contains(namespaceNameAsString)) {
            semanticErrors.add(createSemanticError(INHERITANCE_LOOP_DETECTED, initialTypeNode, newPath));
            return;
        } else {
            alreadyVisitedTypes.add(namespaceNameAsString);
        }
        typeNode
            .getImplementsListNode()
            .stream()
            .flatMap(iln -> iln.getNamespaceNameGenericParametersNodes().stream())
            .forEach(
                iln -> {
                    var newAlreadyVisitedTypes = new HashSet<>(alreadyVisitedTypes);
                    var newTypeNode = objects.typeNodeMap.get(ParseTreeUtils.getNamespaceNameString(iln.getNamespaceNameNode()));

                    //It is possible that the type node can refer to a non-existent type at this stage, that will get picked up somewhere else in the analysis
                    if(newTypeNode == null) return;
                    checkInheritanceLoop(semanticErrors, objects, newAlreadyVisitedTypes, newPath + "->", initialTypeNode, newTypeNode);
                }
            );
    }

    private static void checkFields(final List<SemanticError> semanticErrors, final Objects objects, final Optional<FieldsNode> fieldsNode) {
        fieldsNode
            .stream()
            .flatMap(fn -> fn.getFieldNodes().stream())
            .map(FieldNode::getFieldTypeNode)
            .forEach(fn -> checkFieldType(semanticErrors, objects, fn));
    }

    private static void checkFieldType(final List<SemanticError> semanticErrors, final Objects objects, final FieldTypeNode fieldTypeNode) {
        if(fieldTypeNode.getArrayFieldTypeNode().isPresent()) {
            checkArrayFieldType(semanticErrors, objects, fieldTypeNode.getArrayFieldTypeNode().get());
        } else if(fieldTypeNode.getNonArrayFieldTypeNode().isPresent()) {
            checkNonArrayFieldType(semanticErrors, objects, fieldTypeNode.getNonArrayFieldTypeNode().get());
        }
    }

    private static void checkNonArrayFieldType(final List<SemanticError> semanticErrors, final Objects objects, final NonArrayFieldTypeNode nonArrayFieldTypeNode) {
        if(nonArrayFieldTypeNode instanceof MapFieldTypeNode mapFieldTypeNode) {
            checkFieldType(semanticErrors, objects, mapFieldTypeNode.getKeyFieldTypeNode());
            checkFieldType(semanticErrors, objects, mapFieldTypeNode.getValueFieldTypeNode());
        }
        //TODO:KMD Put this to the bottom
        if(nonArrayFieldTypeNode instanceof ObjectFieldTypeNode objectFieldTypeNode) {
            semanticErrors.add(createSemanticError(UNKNOWN_OBJECT, objectFieldTypeNode, ParseTreeUtils.getNamespaceNameString(objectFieldTypeNode.getNamespaceNameNode())));
        }
        if(nonArrayFieldTypeNode instanceof SetFieldTypeNode setFieldTypeNode) {
            checkFieldType(semanticErrors, objects, setFieldTypeNode.getFieldTypeNode());
        }
        if(nonArrayFieldTypeNode instanceof ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode) {
            checkFieldType(semanticErrors, objects, valueOrErrorFieldTypeNode.getFieldTypeNode());
        }
    }

    private static void checkArrayFieldType(final List<SemanticError> semanticErrors, final Objects objects, final ArrayFieldTypeNode arrayFieldTypeNode) {
        checkNonArrayFieldType(semanticErrors, objects, arrayFieldTypeNode.getNonArrayFieldTypeNode());
    }

    private static List<GenericObjectFieldTypeNode> getGenericParametersFieldTypeNode(final FieldTypeNode fieldTypeNode) {
        var foundGenericParameters = new ArrayList<GenericObjectFieldTypeNode>();
        getGenericParametersFieldTypeNode(foundGenericParameters, fieldTypeNode);
        return foundGenericParameters;
    }

    private static void getGenericParametersFieldTypeNode(final List<GenericObjectFieldTypeNode> foundGenericParameters, final FieldTypeNode fieldTypeNode) {
        if(fieldTypeNode.getArrayFieldTypeNode().isPresent()) {
            getGenericParametersArrayFieldTypeNode(foundGenericParameters, fieldTypeNode.getArrayFieldTypeNode().get());
        } else if (fieldTypeNode.getNonArrayFieldTypeNode().isPresent()) {
            getGenericParametersNonArrayFieldTypeNode(foundGenericParameters, fieldTypeNode.getNonArrayFieldTypeNode().get());
        }
    }

    private static void getGenericParametersNonArrayFieldTypeNode(final List<GenericObjectFieldTypeNode> foundGenericParameters, final NonArrayFieldTypeNode nonArrayFieldTypeNode) {
        if(nonArrayFieldTypeNode instanceof GenericObjectFieldTypeNode genericObjectFieldTypeNode) {
            foundGenericParameters.add(genericObjectFieldTypeNode);
        }
        if(nonArrayFieldTypeNode instanceof MapFieldTypeNode mapFieldTypeNode) {
            getGenericParametersFieldTypeNode(foundGenericParameters, mapFieldTypeNode.getKeyFieldTypeNode());
            getGenericParametersFieldTypeNode(foundGenericParameters, mapFieldTypeNode.getValueFieldTypeNode());
        }
        if(nonArrayFieldTypeNode instanceof SetFieldTypeNode setFieldTypeNode) {
            getGenericParametersFieldTypeNode(foundGenericParameters, setFieldTypeNode.getFieldTypeNode());
        }
        if(nonArrayFieldTypeNode instanceof ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode) {
            getGenericParametersFieldTypeNode(foundGenericParameters, valueOrErrorFieldTypeNode.getFieldTypeNode());
        }
    }

    private static void getGenericParametersArrayFieldTypeNode(final List<GenericObjectFieldTypeNode> foundGenericParameters, final ArrayFieldTypeNode arrayFieldTypeNode) {
        getGenericParametersNonArrayFieldTypeNode(foundGenericParameters, arrayFieldTypeNode.getNonArrayFieldTypeNode());
    }

    private static void checkGenericParameters(final List<SemanticError> semanticErrors, final Objects objects, final ProtoGenTypeNode typeNode, final NamespaceNameGenericParametersNode implementsTypeNamespaceNameGenericParametersNode) {

        var numberOfGenericParametersOnImplementsListDefinition = implementsTypeNamespaceNameGenericParametersNode.getGenericParametersNode().isPresent()
            ? implementsTypeNamespaceNameGenericParametersNode
                .getGenericParametersNode()
                .get()
                .getFieldTypeNodes()
                .stream()
                .flatMap(ftn -> Stream.of(ftn.getArrayFieldTypeNode().stream(), ftn.getNonArrayFieldTypeNode().stream()))
                .flatMap(s -> s)
                .count()
            : 0;

        var implementsTypeDefinition = objects.typeNodeMap.get(ParseTreeUtils.getNamespaceNameString(implementsTypeNamespaceNameGenericParametersNode.getNamespaceNameNode()));
        var numberOfGenericParametersOnTypeDefinition = implementsTypeDefinition.getNamespaceNameGenericParametersWithBoundsNode().getGenericParametersWithBoundsNode().isPresent()
            ? implementsTypeDefinition.getNamespaceNameGenericParametersWithBoundsNode().getGenericParametersWithBoundsNode().get().getGenericParameterWithBoundsNodes().size()
            : 0;

        if(numberOfGenericParametersOnImplementsListDefinition != numberOfGenericParametersOnTypeDefinition) {
            semanticErrors.add(createSemanticError(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION, implementsTypeNamespaceNameGenericParametersNode, ParseTreeUtils.getNamespaceNameString(implementsTypeNamespaceNameGenericParametersNode.getNamespaceNameNode()), numberOfGenericParametersOnImplementsListDefinition, numberOfGenericParametersOnTypeDefinition));
        } else {
            if(numberOfGenericParametersOnImplementsListDefinition > 0) {
                //TODO:KMD Check all the bounds
            }
        }
    }

    private static void checkEnums(final List<SemanticError> semanticErrors, final Objects objects) {
        objects.enumNodeMap
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