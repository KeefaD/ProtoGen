package com.kdsc.protogen.semanticanalysis;
import com.kdsc.protogen.compilerresults.CompilerResults;
import com.kdsc.protogen.parsetreenodes.*;
import com.kdsc.protogen.parsetreenodes.fieldtypenodes.*;
import com.kdsc.protogen.parsetreenodes.utils.ParseTreeUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.kdsc.protogen.semanticanalysis.SemanticErrorFactory.createSemanticError;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.*;

//TODO:KMD Redefinition of field in interface with different type
//TODO:KMD This really needs to be refactored to be super neat
//TODO:KMD Obviously we need to make this work for keys but there is no point until Types are done
//TODO:KMD Test disallowed map / set key types
//TODO:KMD Test disallowed key types
public class SemanticAnalyser {

    private record VersionNumberImplementsList(Optional<Long> versionNumber, Optional<ImplementsListNode> implementsList) {}
    private final String typeNameAndVersionStringTemplate = "%s(Version %d)";

    public List<SemanticError> runSemanticAnalysis(final CompilerResults compilerResults) {

        var returnSemanticErrors = new ArrayList<SemanticError>();

        checkRedefinitionOfObject(compilerResults, returnSemanticErrors);

        checkTypes(compilerResults, returnSemanticErrors);
        checkEnums(compilerResults, returnSemanticErrors);

        return returnSemanticErrors
            .stream()
            .sorted(
                Comparator.comparing(SemanticError::sourceFileName)
                    .thenComparing(SemanticError::line)
                    .thenComparing(SemanticError::charPosition)
            )
            .collect(Collectors.toList());
    }

    private void checkRedefinitionOfObject(final CompilerResults compilerResults, final List<SemanticError> semanticErrors) {

        var topLevelObjects = compilerResults
            .getFileNodes()
            .stream()
            .flatMap(
                fn -> Stream.of(
                    fn.getTypeNodes().stream(),
                    fn.getKeyNodes().stream(),
                    fn.getEnumNodes().stream()
                ).flatMap(s -> s)
            )
            .collect(Collectors.toList());

        var topLevelObjectNamespaceNamesAsString = new HashSet<String>();

        topLevelObjects
            .forEach(
                tln -> {
                    var namespaceNameAsString = ParseTreeUtils.getNamespaceNameString(tln.getNamespaceNameNode());
                    if (topLevelObjectNamespaceNamesAsString.contains(namespaceNameAsString)) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_OBJECT, tln, namespaceNameAsString));
                    } else {
                        topLevelObjectNamespaceNamesAsString.add(namespaceNameAsString);
                    }
                }
            );
    }

    private void checkTypes(final CompilerResults compilerResults, final List<SemanticError> semanticErrors) {
        compilerResults.getAllTypeNodeMap()
            .values()
            .forEach(tn -> checkType(compilerResults, semanticErrors, tn));
    }

    private void checkType(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final TypeNode typeNode) {

        var typeName = ParseTreeUtils.getNamespaceNameString(typeNode.getNamespaceNameNode());

        if (typeNode.getImplementsListNode().isPresent()) {
            var versionsImplementsListNodes = typeNode
                .getVersionsNode()
                .stream()
                .flatMap(vn -> vn.getVersionNodes().stream())
                .flatMap(vn -> vn.getImplementsListNode().stream())
                .flatMap(il -> il.getNamespaceNameGenericParametersNodes().stream())
                .collect(Collectors.toList());
            versionsImplementsListNodes
                .forEach(nngp -> semanticErrors.add(createSemanticError(CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME, nngp, ParseTreeUtils.getNamespaceNameString(nngp.getNamespaceNameNode()), typeName)));
        }

        if (typeNode.getNamespaceNameGenericParametersWithBoundsNode().getGenericParametersWithBoundsNode().isPresent()) {
            var genericParameterWithBoundsNodes = typeNode
                .getVersionsNode()
                .stream()
                .flatMap(vn -> vn.getVersionNodes().stream())
                .flatMap(vn -> vn.getGenericParametersWithBoundsNode().stream())
                .flatMap(il -> il.getGenericParameterWithBoundsNodes().stream())
                .collect(Collectors.toList());
            genericParameterWithBoundsNodes
                .forEach(gp -> semanticErrors.add(createSemanticError(CANNOT_HAVE_GENERIC_PARAMETERS_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME, gp, gp.getIdentifier(), typeName)));
        }

        checkTypeVersion(compilerResults, semanticErrors, typeNode, typeName, typeName, typeNode.isInterface(), typeNode.getImplementsListNode(), typeNode.getNamespaceNameGenericParametersWithBoundsNode().getGenericParametersWithBoundsNode(), typeNode.getFieldsNode());

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
                    if (versionNumbersSet.contains(vn.getVersionNumber())) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_TYPE_VERSION, vn, vn.getVersionNumber()));
                    } else {
                        versionNumbersSet.add(vn.getVersionNumber());
                    }
                }
            );

        versions
            .forEach(
                vn -> {
                    var versionTypeName = typeNameAndVersionStringTemplate.formatted(typeName, vn.getVersionNumberNode().getVersionNumber());
                    checkTypeVersion(compilerResults, semanticErrors, typeNode, typeName, versionTypeName, typeNode.isInterface(), vn.getImplementsListNode(), vn.getGenericParametersWithBoundsNode(), vn.getFieldsNode());
                }
            );

    }

    private void checkTypeVersion(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final TypeNode outerTypeNode, final String typeName, final String typeDescription, final boolean isInterface, final Optional<ImplementsListNode> implementsListNode, final Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode, Optional<FieldsNode> fieldsNode) {

        checkInheritanceLoop(compilerResults, semanticErrors, implementsListNode, new HashSet<>(), "", outerTypeNode, typeDescription, typeName, typeDescription);

        var genericParametersSet = checkAndReturnGenericParameterSet(compilerResults, semanticErrors, typeDescription, genericParametersWithBoundsNode);

        implementsListNode.ifPresent(iln -> checkImplementsList(compilerResults, semanticErrors, typeDescription, isInterface, genericParametersSet, iln));

        checkFields(compilerResults, semanticErrors, fieldsNode);

        implementsListNode
            .stream()
            .flatMap(iln -> iln.getNamespaceNameGenericParametersNodes().stream())
            .map(NamespaceNameGenericParametersNode::getGenericParametersNode)
            .flatMap(Optional::stream)
            .flatMap(gpn -> gpn.getFieldTypeNodes().stream())
            .forEach(ftn -> checkFieldType(compilerResults, semanticErrors, ftn));

    }

    private Set<String> checkAndReturnGenericParameterSet(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final String typeDescription, final Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode)  {

        var genericParameters = genericParametersWithBoundsNode
            .stream()
            .flatMap(gpwb -> gpwb.getGenericParameterWithBoundsNodes().stream())
            .collect(Collectors.toList());

        var genericParametersSet = new HashSet<String>();
        genericParameters
            .forEach(
                gp -> {
                    if (genericParametersSet.contains(gp.getIdentifier())) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_GENERIC_PARAMETER, gp, typeDescription, gp.getIdentifier()));
                    } else {
                        genericParametersSet.add(gp.getIdentifier());
                    }
                }
            );

        genericParametersWithBoundsNode
            .stream()
            .flatMap(gpwb -> gpwb.getGenericParameterWithBoundsNodes().stream())
            .forEach(
                gpwb -> {
                    var namespaceNameAsStringSet = new HashSet<String>();
                    gpwb
                        .getNamespaceNameGenericParametersNodes()
                        .forEach(
                            nngpwb -> {
                                if (!compilerResults.getAllTypeNodeMap().containsKey(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode()))) {
                                    semanticErrors.add(createSemanticError(GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE, nngpwb, gpwb.getIdentifier(), ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode())));
                                } else if (namespaceNameAsStringSet.contains(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode()))) {
                                    semanticErrors.add(createSemanticError(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES, nngpwb, gpwb.getIdentifier(), ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode())));
                                } else {
                                    namespaceNameAsStringSet.add(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode()));
                                }
                            }
                        );
                }
            );

        return genericParametersSet;
    }

    private void checkImplementsList(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final String typeName, final boolean isInterface, final Set<String> genericParametersSet, final ImplementsListNode implementsListNode) {
        implementsListNode
            .getNamespaceNameGenericParametersNodes()
            .forEach(
                nngp -> {
                    var namespaceNameAsString = ParseTreeUtils.getNamespaceNameString(nngp.getNamespaceNameNode());
                    if (!compilerResults.getAllTypeNodeMap().containsKey(namespaceNameAsString)) {
                        semanticErrors.add(createSemanticError(TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST, nngp, typeName, namespaceNameAsString));
                    } else {
                        checkGenericParameters(compilerResults, semanticErrors, nngp);
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
                                if (!genericParametersSet.contains(i.getGenericParameterNode().getIdentifier())) {
                                    semanticErrors.add(createSemanticError(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE, i, i.getGenericParameterNode().getIdentifier(), typeName));
                                }
                            }
                        );
                }
            );

        var nonInterfaceTypeNodes = implementsListNode
            .getNamespaceNameGenericParametersNodes()
            .stream()
            .filter(nngpwb -> compilerResults.getAllTypeNodeMap().containsKey(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode())))
            .filter(nngpwb -> !compilerResults.getAllTypeNodeMap().get(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode())).isInterface())
            .collect(Collectors.toList());

        if (isInterface) {
            if (nonInterfaceTypeNodes.size() > 0) {
                nonInterfaceTypeNodes
                    .forEach(
                        nngpn -> semanticErrors.add(createSemanticError(EXTENDING_INTERFACE_WITH_NON_INTERFACE, nngpn, typeName, ParseTreeUtils.getNamespaceNameString(nngpn.getNamespaceNameNode())))
                    );
            }
        } else {
            if (nonInterfaceTypeNodes.size() > 1) {
                nonInterfaceTypeNodes
                    .forEach(
                        nngpn -> semanticErrors.add(createSemanticError(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE, nngpn, typeName, ParseTreeUtils.getNamespaceNameString(nngpn.getNamespaceNameNode())))
                    );
            }
        }
    }

    private void checkInheritanceLoop(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final Optional<ImplementsListNode> implementsListNode, final Set<String> alreadyVisitedTypes, final String path, final TypeNode outerTypeNode, final String outerTypeDescription, final String typeName, final String typeNameDescription) {
        var newPath = path + typeNameDescription;
        if (alreadyVisitedTypes.contains(typeName)) {
            semanticErrors.add(createSemanticError(INHERITANCE_LOOP_DETECTED, outerTypeNode, newPath, outerTypeDescription));
            return;
        } else {
            alreadyVisitedTypes.add(typeName);
        }
        implementsListNode
            .stream()
            .flatMap(iln -> iln.getNamespaceNameGenericParametersNodes().stream())
            .forEach(
                iln -> {
                    var newAlreadyVisitedTypes = new HashSet<>(alreadyVisitedTypes);
                    var newTypeNode = compilerResults.getAllTypeNodeMap().get(ParseTreeUtils.getNamespaceNameString(iln.getNamespaceNameNode()));

                    //It is possible that the type node can refer to a non-existent type at this stage, that will get picked up somewhere else in the analysis
                    if (newTypeNode == null) return;

                    var latestVersionOfImplementsListsNodeAndVersionNumber = getLatestVersionOfImplementsListNodeAndVersionNumber(newTypeNode);

                    var newTypeName = ParseTreeUtils.getNamespaceNameString(newTypeNode.getNamespaceNameNode());

                    var newTypeNameDescription = latestVersionOfImplementsListsNodeAndVersionNumber.versionNumber().isPresent()
                        ? typeNameAndVersionStringTemplate.formatted(ParseTreeUtils.getNamespaceNameString(newTypeNode.getNamespaceNameNode()), latestVersionOfImplementsListsNodeAndVersionNumber.versionNumber().get())
                        : ParseTreeUtils.getNamespaceNameString(newTypeNode.getNamespaceNameNode());

                    checkInheritanceLoop(compilerResults, semanticErrors, latestVersionOfImplementsListsNodeAndVersionNumber.implementsList(), newAlreadyVisitedTypes, newPath + "->", outerTypeNode, outerTypeDescription, newTypeName, newTypeNameDescription);
                }
            );
    }

    private VersionNumberImplementsList getLatestVersionOfImplementsListNodeAndVersionNumber(final TypeNode typeNode) {
        if(typeNode.getImplementsListNode().isPresent()) return new VersionNumberImplementsList(Optional.empty(), typeNode.getImplementsListNode());
        var latestVersion = typeNode
            .getVersionsNode()
            .stream()
            .flatMap(vn -> vn.getVersionNodes().stream())
            .max(Comparator.comparingLong(v -> v.getVersionNumberNode().getVersionNumber()));
        return latestVersion.map(versionNode -> new VersionNumberImplementsList(Optional.of(versionNode.getVersionNumberNode().getVersionNumber()), versionNode.getImplementsListNode())).orElseGet(() -> new VersionNumberImplementsList(Optional.empty(), Optional.empty()));
    }

    private void checkFields(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final Optional<FieldsNode> fieldsNode) {
        fieldsNode
            .stream()
            .flatMap(fn -> fn.getFieldNodes().stream())
            .map(FieldNode::getFieldTypeNode)
            .forEach(fn -> checkFieldType(compilerResults, semanticErrors, fn));
    }

    private void checkFieldType(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final FieldTypeNode fieldTypeNode) {
        if (fieldTypeNode.getArrayFieldTypeNode().isPresent()) {
            checkArrayFieldType(compilerResults, semanticErrors, fieldTypeNode.getArrayFieldTypeNode().get());
        } else if (fieldTypeNode.getNonArrayFieldTypeNode().isPresent()) {
            checkNonArrayFieldType(compilerResults, semanticErrors, fieldTypeNode.getNonArrayFieldTypeNode().get());
        }
    }

    private void checkNonArrayFieldType(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final NonArrayFieldTypeNode nonArrayFieldTypeNode) {
        if (nonArrayFieldTypeNode instanceof MapFieldTypeNode mapFieldTypeNode) {
            checkFieldType(compilerResults, semanticErrors, mapFieldTypeNode.getKeyFieldTypeNode());
            checkFieldType(compilerResults, semanticErrors, mapFieldTypeNode.getValueFieldTypeNode());
        }
        //TODO:KMD Put this to the bottom
        if (nonArrayFieldTypeNode instanceof ObjectFieldTypeNode objectFieldTypeNode) {
            semanticErrors.add(createSemanticError(UNKNOWN_OBJECT, objectFieldTypeNode, ParseTreeUtils.getNamespaceNameString(objectFieldTypeNode.getNamespaceNameNode())));
        }
        if (nonArrayFieldTypeNode instanceof SetFieldTypeNode setFieldTypeNode) {
            checkFieldType(compilerResults, semanticErrors, setFieldTypeNode.getFieldTypeNode());
        }
        if (nonArrayFieldTypeNode instanceof ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode) {
            checkFieldType(compilerResults, semanticErrors, valueOrErrorFieldTypeNode.getFieldTypeNode());
        }
    }

    private void checkArrayFieldType(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final ArrayFieldTypeNode arrayFieldTypeNode) {
        checkNonArrayFieldType(compilerResults, semanticErrors, arrayFieldTypeNode.getNonArrayFieldTypeNode());
    }

    private List<GenericObjectFieldTypeNode> getGenericParametersFieldTypeNode(final FieldTypeNode fieldTypeNode) {
        var foundGenericParameters = new ArrayList<GenericObjectFieldTypeNode>();
        getGenericParametersFieldTypeNode(foundGenericParameters, fieldTypeNode);
        return foundGenericParameters;
    }

    private void getGenericParametersFieldTypeNode(final List<GenericObjectFieldTypeNode> foundGenericParameters, final FieldTypeNode fieldTypeNode) {
        if (fieldTypeNode.getArrayFieldTypeNode().isPresent()) {
            getGenericParametersArrayFieldTypeNode(foundGenericParameters, fieldTypeNode.getArrayFieldTypeNode().get());
        } else if (fieldTypeNode.getNonArrayFieldTypeNode().isPresent()) {
            getGenericParametersNonArrayFieldTypeNode(foundGenericParameters, fieldTypeNode.getNonArrayFieldTypeNode().get());
        }
    }

    private void getGenericParametersNonArrayFieldTypeNode(final List<GenericObjectFieldTypeNode> foundGenericParameters, final NonArrayFieldTypeNode nonArrayFieldTypeNode) {
        if (nonArrayFieldTypeNode instanceof GenericObjectFieldTypeNode genericObjectFieldTypeNode) {
            foundGenericParameters.add(genericObjectFieldTypeNode);
        }
        if (nonArrayFieldTypeNode instanceof MapFieldTypeNode mapFieldTypeNode) {
            getGenericParametersFieldTypeNode(foundGenericParameters, mapFieldTypeNode.getKeyFieldTypeNode());
            getGenericParametersFieldTypeNode(foundGenericParameters, mapFieldTypeNode.getValueFieldTypeNode());
        }
        if (nonArrayFieldTypeNode instanceof SetFieldTypeNode setFieldTypeNode) {
            getGenericParametersFieldTypeNode(foundGenericParameters, setFieldTypeNode.getFieldTypeNode());
        }
        if (nonArrayFieldTypeNode instanceof ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode) {
            getGenericParametersFieldTypeNode(foundGenericParameters, valueOrErrorFieldTypeNode.getFieldTypeNode());
        }
    }

    private void getGenericParametersArrayFieldTypeNode(final List<GenericObjectFieldTypeNode> foundGenericParameters, final ArrayFieldTypeNode arrayFieldTypeNode) {
        getGenericParametersNonArrayFieldTypeNode(foundGenericParameters, arrayFieldTypeNode.getNonArrayFieldTypeNode());
    }

    private void checkGenericParameters(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final NamespaceNameGenericParametersNode implementsTypeNamespaceNameGenericParametersNode) {

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

        var implementsTypeDefinition = compilerResults.getAllTypeNodeMap().get(ParseTreeUtils.getNamespaceNameString(implementsTypeNamespaceNameGenericParametersNode.getNamespaceNameNode()));
        var numberOfGenericParametersOnTypeDefinition = implementsTypeDefinition.getNamespaceNameGenericParametersWithBoundsNode().getGenericParametersWithBoundsNode().isPresent()
            ? implementsTypeDefinition.getNamespaceNameGenericParametersWithBoundsNode().getGenericParametersWithBoundsNode().get().getGenericParameterWithBoundsNodes().size()
            : 0;

        if (numberOfGenericParametersOnImplementsListDefinition != numberOfGenericParametersOnTypeDefinition) {
            semanticErrors.add(createSemanticError(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION, implementsTypeNamespaceNameGenericParametersNode, ParseTreeUtils.getNamespaceNameString(implementsTypeNamespaceNameGenericParametersNode.getNamespaceNameNode()), numberOfGenericParametersOnImplementsListDefinition, numberOfGenericParametersOnTypeDefinition));
        } else {
            if (numberOfGenericParametersOnImplementsListDefinition > 0) {
                //TODO:KMD Check all the bounds
            }
        }
    }

    private void checkEnums(final CompilerResults compilerResults, final List<SemanticError> semanticErrors) {
        compilerResults
            .getEnumNodeMap()
            .values()
            .forEach(en -> checkEnum(semanticErrors, en));
    }

    private void checkEnum(final List<SemanticError> semanticErrors, final EnumNode enumNode) {
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
                    if (versionNumbersSet.contains(vn.getVersionNumber())) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_ENUM_VERSION, vn, vn.getVersionNumber()));
                    } else {
                        versionNumbersSet.add(vn.getVersionNumber());
                    }
                }
            );

        versions
            .forEach(evn -> checkEnumCases(semanticErrors, evn.getEnumCasesNode()));

        if (enumNode.getEnumCasesNode().isPresent()) checkEnumCases(semanticErrors, enumNode.getEnumCasesNode().get());
    }

    private void checkEnumCases(final List<SemanticError> semanticErrors, final EnumCasesNode enumCasesNode) {
        var enumNameSet = new HashSet<String>();
        enumCasesNode
            .getEnumNameNodes()
            .forEach(
                enn -> {
                    if (enumNameSet.contains(enn.getEnumName())) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_ENUM_CASE, enn, enn.getEnumName()));
                    } else {
                        enumNameSet.add(enn.getEnumName());
                    }
                }
            );
    }

}