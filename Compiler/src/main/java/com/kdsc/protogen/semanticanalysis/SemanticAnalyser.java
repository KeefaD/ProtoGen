package com.kdsc.protogen.semanticanalysis;
import com.kdsc.protogen.compilerresults.CompilerResults;
import com.kdsc.protogen.parsetreenodes.*;
import com.kdsc.protogen.parsetreenodes.commoninterfaces.KeyOrTypeFieldTypeNode;
import com.kdsc.protogen.parsetreenodes.commoninterfaces.KeyOrTypeNode;
import com.kdsc.protogen.parsetreenodes.fieldtypenodes.*;
import com.kdsc.protogen.parsetreenodes.utils.ParseTreeUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.kdsc.protogen.semanticanalysis.SemanticErrorFactory.createSemanticError;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.*;

//TODO:KMD This really needs to be refactored to be super neat
//TODO:KMD Obviously we need to make this work for keys but there is no point until Types are done
//TODO:KMD Test disallowed key types
//TODO:KMD Redefinition of field in interface with different type
//TODO:KMD Test disallowed map / set key types
//TODO:KMD What about bounds like map<T1 : TestInterfaces.TestInterface1, T2 : TestInterfaces.TestInterface2>, this is possible in Java, need to make it work
//TODO:KMD Test when generic bounds have a non interface type in there twice not in the same hierarchy
//TODO:KMD Test Missing in bounds, when you objects rather than types
//TODO:KMD Test generic parameters on outer type and implements list on versions and visa versa
//TODO:KMD Test generics with optionals
//TODO:KMD Need to check generics to make sure they don't have clashing fields, java does this obviously
//TODO:KMD TemporaryGenericsJavaTest<T1 : Integer, T2 : Map<T1, T2>> is valid, need to check for this
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
        var allGenericParameterIdentifiers = genericParametersWithBoundsNode
            .stream()
            .flatMap(gpwb -> gpwb.getGenericParameterWithBoundsNodes().stream())
            .map(GenericParameterWithBoundsNode::getIdentifier)
            .collect(Collectors.toSet());
        checkInheritanceLoop(compilerResults, semanticErrors, implementsListNode, new HashSet<>(), "", outerTypeNode, typeDescription, typeName, typeDescription);
        checkGenericParameters(compilerResults, semanticErrors, genericParametersWithBoundsNode, typeDescription);
        checkImplementsList(compilerResults, semanticErrors, allGenericParameterIdentifiers, typeDescription, isInterface, implementsListNode);
        checkFields(compilerResults, semanticErrors, allGenericParameterIdentifiers, fieldsNode);
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

    private void checkGenericParameters(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode, final String typeDescription) {
        var genericParameterSet = new HashSet<String>();
        genericParametersWithBoundsNode
            .stream()
            .flatMap(gpwb -> gpwb.getGenericParameterWithBoundsNodes().stream())
            .forEach(
                gp -> {
                    if (genericParameterSet.contains(gp.getIdentifier())) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_GENERIC_PARAMETER, gp, typeDescription, gp.getIdentifier()));
                    } else {
                        genericParameterSet.add(gp.getIdentifier());
                    }
                }
            );

        genericParametersWithBoundsNode
            .stream()
            .flatMap(gpwb -> gpwb.getGenericParameterWithBoundsNodes().stream())
            .forEach(gp -> checkGenericParameterBoundsFieldTypes(compilerResults, semanticErrors, genericParameterSet, gp, gp.getFieldTypeNodes()));
    }

    private void checkImplementsList(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final Set<String> allGenericParameterIdentifiers, final String typeDescription, final boolean isInterface, final Optional<ImplementsListNode> implementsListNode) {

        if(implementsListNode.isEmpty()) return;

        implementsListNode
            .get()
            .getNamespaceNameGenericParametersNodes()
            .forEach(
                nngp -> {
                    var namespaceNameAsString = ParseTreeUtils.getNamespaceNameString(nngp.getNamespaceNameNode());
                    if (!compilerResults.getAllTypeNodeMap().containsKey(namespaceNameAsString)) {
                        semanticErrors.add(createSemanticError(TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST, nngp, typeDescription, namespaceNameAsString));
                        return;
                    }
                    checkGenericParametersForImplementsListItem(compilerResults, semanticErrors, allGenericParameterIdentifiers, nngp);
                }
            );

        var nonInterfaceTypeNodes = implementsListNode
            .get()
            .getNamespaceNameGenericParametersNodes()
            .stream()
            .filter(nngpwb -> compilerResults.getAllTypeNodeMap().containsKey(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode())))
            .filter(nngpwb -> !compilerResults.getAllTypeNodeMap().get(ParseTreeUtils.getNamespaceNameString(nngpwb.getNamespaceNameNode())).isInterface())
            .collect(Collectors.toList());

        if (isInterface) {
            if (nonInterfaceTypeNodes.size() > 0) {
                nonInterfaceTypeNodes
                    .forEach(
                        nngpn -> semanticErrors.add(createSemanticError(EXTENDING_INTERFACE_WITH_NON_INTERFACE, nngpn, typeDescription, ParseTreeUtils.getNamespaceNameString(nngpn.getNamespaceNameNode())))
                    );
            }
        } else {
            if (nonInterfaceTypeNodes.size() > 1) {
                nonInterfaceTypeNodes
                    .forEach(
                        nngpn -> semanticErrors.add(createSemanticError(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE, nngpn, typeDescription, ParseTreeUtils.getNamespaceNameString(nngpn.getNamespaceNameNode())))
                    );
            }
        }
    }

    private void checkFields(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final Set<String> allGenericParameterIdentifiers, final Optional<FieldsNode> fieldsNode) {
        fieldsNode
            .stream()
            .flatMap(fn -> fn.getFieldNodes().stream())
            .map(FieldNode::getFieldTypeNode)
            .forEach(fn -> checkFieldType(compilerResults, semanticErrors, allGenericParameterIdentifiers, fn));
    }

    private void checkGenericParameterBoundsFieldTypes(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final Set<String> allGenericParameterIdentifiers, final GenericParameterWithBoundsNode genericParameter, final List<FieldTypeNode> fieldTypeNodes) {

        fieldTypeNodes
            .forEach(ftn -> checkFieldType(compilerResults, semanticErrors, allGenericParameterIdentifiers, ftn));

        var fieldTypeNodesCount = fieldTypeNodes.size();
        for(var lhsToCheckIndex = 0; lhsToCheckIndex < fieldTypeNodesCount - 1; lhsToCheckIndex++) {
            var rhsToCheckIndex = lhsToCheckIndex + 1;
            var foundDuplicateFieldTypeNode = false;
            FieldTypeNode lhsFieldTypeNode = null;
            FieldTypeNode rhsFieldTypeNode = null;
            for(; rhsToCheckIndex < fieldTypeNodesCount; rhsToCheckIndex++) {
                if(checkGenericParameterBoundsForFieldTypePair(compilerResults, semanticErrors, genericParameter, fieldTypeNodes.get(lhsToCheckIndex), fieldTypeNodes.get(rhsToCheckIndex))) {
                    foundDuplicateFieldTypeNode = true;
                    lhsFieldTypeNode = fieldTypeNodes.get(lhsToCheckIndex);
                    rhsFieldTypeNode = fieldTypeNodes.get(rhsToCheckIndex);
                }
            }
            if(foundDuplicateFieldTypeNode) {
                addSemanticErrorIfNotDuplicate(semanticErrors, createSemanticError(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES, lhsFieldTypeNode, genericParameter.getIdentifier(), ParseTreeUtils.convertFieldTypeToString(lhsFieldTypeNode)));
                addSemanticErrorIfNotDuplicate(semanticErrors, createSemanticError(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES, rhsFieldTypeNode, genericParameter.getIdentifier(), ParseTreeUtils.convertFieldTypeToString(rhsFieldTypeNode)));
            }
        }

    }

    private boolean checkGenericParameterBoundsForFieldTypePair(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final GenericParameterWithBoundsNode genericParameter, final FieldTypeNode lhsToCompare, final FieldTypeNode rhsToCompare) {

        if(lhsToCompare.getArrayFieldTypeNode().isPresent() && lhsToCompare.getNonArrayFieldTypeNode().isPresent()) {
            throw new RuntimeException("You should not be able to have a Field type that has both array field type and non array field types present");
        }

        if(lhsToCompare.getArrayFieldTypeNode().isEmpty() && lhsToCompare.getNonArrayFieldTypeNode().isEmpty()) {
            throw new RuntimeException("You should not be able to have a Field type that has both array field type and non array field types are not present");
        }

        if(rhsToCompare.getArrayFieldTypeNode().isPresent() && rhsToCompare.getNonArrayFieldTypeNode().isPresent()) {
            throw new RuntimeException("You should not be able to have a Field type that has both array field type and non array field types present");
        }

        if(rhsToCompare.getArrayFieldTypeNode().isEmpty() && rhsToCompare.getNonArrayFieldTypeNode().isEmpty()) {
            throw new RuntimeException("You should not be able to have a Field type that has both array field type and non array field types are not present");
        }

        if(lhsToCompare.getArrayFieldTypeNode().isPresent() && rhsToCompare.getArrayFieldTypeNode().isPresent()) {
            return checkGenericParameterBoundsForFieldTypePair(compilerResults, semanticErrors, genericParameter, lhsToCompare.getArrayFieldTypeNode().get(), rhsToCompare.getArrayFieldTypeNode().get());
        } else if(lhsToCompare.getNonArrayFieldTypeNode().isPresent() && rhsToCompare.getNonArrayFieldTypeNode().isPresent()) {
            return checkGenericParameterBoundsForFieldTypePair(compilerResults, semanticErrors, genericParameter, lhsToCompare.getNonArrayFieldTypeNode().get(), rhsToCompare.getNonArrayFieldTypeNode().get());
        }

        return false;
    }

    private boolean checkGenericParameterBoundsForFieldTypePair(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final GenericParameterWithBoundsNode genericParameter, final ArrayFieldTypeNode lhsToCompare, final ArrayFieldTypeNode rhsToCompare) {
        //TODO:KMD Not sure what to do here, not sure if you can have arrays as bounds at all
        return checkGenericParameterBoundsForFieldTypePair(compilerResults, semanticErrors, genericParameter, lhsToCompare.getNonArrayFieldTypeNode(), rhsToCompare.getNonArrayFieldTypeNode());
    }

    private boolean checkGenericParameterBoundsForFieldTypePair(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final GenericParameterWithBoundsNode genericParameter, final NonArrayFieldTypeNode lhsToCompare, final NonArrayFieldTypeNode rhsToCompare) {
        if(!lhsToCompare.getClass().getName().equals(rhsToCompare.getClass().getName())) return false;
        switch(lhsToCompare) {
            case GenericObjectFieldTypeNode lhsGenericObjectFieldTypeNode -> {
                var rhsAsGenericObjectFieldTypeNode = (GenericObjectFieldTypeNode) rhsToCompare;
                if(lhsGenericObjectFieldTypeNode.getGenericParameterNode().getIdentifier().equals(rhsAsGenericObjectFieldTypeNode.getGenericParameterNode().getIdentifier())) {
                    return true;
                }
            }
            case ListFieldTypeNode lhsListFieldTypeNode -> {
                var rhsAsListFieldTypeNode = (ListFieldTypeNode)rhsToCompare;
                return checkGenericParameterBoundsForFieldTypePair(compilerResults, semanticErrors, genericParameter, lhsListFieldTypeNode.getFieldTypeNode(), rhsAsListFieldTypeNode.getFieldTypeNode());
            }
            case MapFieldTypeNode rhsMapFieldTypeNode -> {
                var rhsAsMapFieldTypeNode = (MapFieldTypeNode)rhsToCompare;
                return
                    checkGenericParameterBoundsForFieldTypePair(compilerResults, semanticErrors, genericParameter, rhsMapFieldTypeNode.getKeyFieldTypeNode(), rhsAsMapFieldTypeNode.getKeyFieldTypeNode()) &&
                    checkGenericParameterBoundsForFieldTypePair(compilerResults, semanticErrors, genericParameter, rhsMapFieldTypeNode.getValueFieldTypeNode(), rhsAsMapFieldTypeNode.getValueFieldTypeNode());
            }
            case SetFieldTypeNode lhsSetFieldTypeNode -> {
                var rhsAsSetFieldTypeNode = (SetFieldTypeNode)rhsToCompare;
                return checkGenericParameterBoundsForFieldTypePair(compilerResults, semanticErrors, genericParameter, lhsSetFieldTypeNode.getFieldTypeNode(), rhsAsSetFieldTypeNode.getFieldTypeNode());
            }
            case TypeFieldTypeNode lhsTypeFieldTypeNode -> {
                var rhsAsTypeFieldTypeNode = (TypeFieldTypeNode) rhsToCompare;
                if(ParseTreeUtils.getNamespaceNameString(lhsTypeFieldTypeNode.getNamespaceNameNode()).equals(ParseTreeUtils.getNamespaceNameString(rhsAsTypeFieldTypeNode.getNamespaceNameNode()))) {
                    return true;
                }
            }
            case ValueOrErrorFieldTypeNode lhsValueOrErrorFieldTypeNode -> {
                var rhsAsValueOrErrorFieldTypeNode = (ValueOrErrorFieldTypeNode)rhsToCompare;
                return checkGenericParameterBoundsForFieldTypePair(compilerResults, semanticErrors, genericParameter, lhsValueOrErrorFieldTypeNode.getFieldTypeNode(), rhsAsValueOrErrorFieldTypeNode.getFieldTypeNode());
            }
            default -> {}
        }
        return false;
    }

    private void checkFieldType(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final Set<String> allGenericParameterIdentifiers, final FieldTypeNode fieldTypeNode) {
        if (fieldTypeNode.getArrayFieldTypeNode().isPresent()) {
            checkArrayFieldType(compilerResults, semanticErrors, allGenericParameterIdentifiers, fieldTypeNode.getArrayFieldTypeNode().get());
        } else if (fieldTypeNode.getNonArrayFieldTypeNode().isPresent()) {
            checkNonArrayFieldType(compilerResults, semanticErrors, allGenericParameterIdentifiers, fieldTypeNode.getNonArrayFieldTypeNode().get());
        }
    }

    private void checkArrayFieldType(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final Set<String> allGenericParameterIdentifiers, final ArrayFieldTypeNode arrayFieldTypeNode) {
        checkNonArrayFieldType(compilerResults, semanticErrors, allGenericParameterIdentifiers, arrayFieldTypeNode.getNonArrayFieldTypeNode());
    }

    private void checkNonArrayFieldType(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final Set<String> allGenericParameterIdentifiers, final NonArrayFieldTypeNode nonArrayFieldTypeNode) {
        if (nonArrayFieldTypeNode instanceof MapFieldTypeNode mapFieldTypeNode) {
            checkFieldType(compilerResults, semanticErrors, allGenericParameterIdentifiers, mapFieldTypeNode.getKeyFieldTypeNode());
            checkFieldType(compilerResults, semanticErrors, allGenericParameterIdentifiers, mapFieldTypeNode.getValueFieldTypeNode());
        }
        if (nonArrayFieldTypeNode instanceof SetFieldTypeNode setFieldTypeNode) {
            checkFieldType(compilerResults, semanticErrors, allGenericParameterIdentifiers, setFieldTypeNode.getFieldTypeNode());
        }
        if (nonArrayFieldTypeNode instanceof ListFieldTypeNode listFieldTypeNode) {
            checkFieldType(compilerResults, semanticErrors, allGenericParameterIdentifiers, listFieldTypeNode.getFieldTypeNode());
        }
        if (nonArrayFieldTypeNode instanceof ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode) {
            checkFieldType(compilerResults, semanticErrors, allGenericParameterIdentifiers, valueOrErrorFieldTypeNode.getFieldTypeNode());
        }
        if (nonArrayFieldTypeNode instanceof ObjectFieldTypeNode objectFieldTypeNode) {
            semanticErrors.add(createSemanticError(UNKNOWN_OBJECT, objectFieldTypeNode, ParseTreeUtils.getNamespaceNameString(objectFieldTypeNode.getNamespaceNameNode())));
        }
        if (nonArrayFieldTypeNode instanceof GenericObjectFieldTypeNode genericObjectFieldTypeNode) {
            if(!allGenericParameterIdentifiers.contains(genericObjectFieldTypeNode.getGenericParameterNode().getIdentifier())) {
                semanticErrors.add(createSemanticError(UNKNOWN_GENERIC_PARAMETER, genericObjectFieldTypeNode, genericObjectFieldTypeNode.getGenericParameterNode().getIdentifier()));
            }
        }
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

    private void checkGenericParametersForImplementsListItem(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final Set<String> allGenericParameterIdentifiers, final NamespaceNameGenericParametersNode implementsTypeNamespaceNameGenericParametersNode) {

        var numberOfGenericParametersOnImplementsListDefinition = implementsTypeNamespaceNameGenericParametersNode.getGenericParametersNode().isPresent()
            ? (int) implementsTypeNamespaceNameGenericParametersNode
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
            return;
        }

        if(numberOfGenericParametersOnTypeDefinition > 0) {
            IntStream
                .range(0, numberOfGenericParametersOnTypeDefinition)
                .forEach(
                    i -> {
                        var implementsListFieldTypeNode = implementsTypeNamespaceNameGenericParametersNode
                            .getGenericParametersNode()
                            .get()
                            .getFieldTypeNodes()
                            .get(i);

                        var typeDefinitionGenericParameterWithBoundsNode = implementsTypeDefinition
                            .getNamespaceNameGenericParametersWithBoundsNode()
                            .getGenericParametersWithBoundsNode()
                            .get()
                            .getGenericParameterWithBoundsNodes()
                            .get(i);
                        checkFieldType(compilerResults, semanticErrors, allGenericParameterIdentifiers, implementsListFieldTypeNode);
                        checkBounds(compilerResults, semanticErrors, implementsListFieldTypeNode, typeDefinitionGenericParameterWithBoundsNode);
                    }
                );
        }
    }

    private void checkBounds(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final FieldTypeNode fieldTypeNode, final GenericParameterWithBoundsNode genericParameterWithBoundsNode) {
        genericParameterWithBoundsNode
            .getFieldTypeNodes()
            .forEach(ftn -> checkFieldTypeNodesAreCompatible(compilerResults, semanticErrors, fieldTypeNode, ftn));
    }

    private void checkFieldTypeNodesAreCompatible(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final FieldTypeNode fieldTypeNode, final FieldTypeNode genericBoundsFieldTypeNode) {
        System.out.println("Comparing " + ParseTreeUtils.convertFieldTypeToString(fieldTypeNode) + " to " + ParseTreeUtils.convertFieldTypeToString(genericBoundsFieldTypeNode));

        if(fieldTypeNode.getArrayFieldTypeNode().isPresent() && genericBoundsFieldTypeNode.getArrayFieldTypeNode().isEmpty()) {
            //TODO:KMD Not sure what to do here at the moment
            return;
        }

        if(fieldTypeNode.getNonArrayFieldTypeNode().isPresent() && genericBoundsFieldTypeNode.getNonArrayFieldTypeNode().isEmpty()) {
            //TODO:KMD Not sure what to do here at the moment
            return;
        }

        if(fieldTypeNode.getArrayFieldTypeNode().isPresent()) {
            checkFieldTypeNodesAreCompatible(compilerResults, semanticErrors, fieldTypeNode.getArrayFieldTypeNode().get(), genericBoundsFieldTypeNode.getArrayFieldTypeNode().get());
        } else if(fieldTypeNode.getNonArrayFieldTypeNode().isPresent()) {
            checkFieldTypeNodesAreCompatible(compilerResults, semanticErrors, fieldTypeNode.getNonArrayFieldTypeNode().get(), genericBoundsFieldTypeNode.getNonArrayFieldTypeNode().get());
        } else {
            throw new RuntimeException("Invalid State");
        }

    }

    private void checkFieldTypeNodesAreCompatible(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final ArrayFieldTypeNode arrayFieldTypeNode, final ArrayFieldTypeNode genericBoundsArrayFieldTypeNode) {
        //TODO:KMD What about dimensions
        checkFieldTypeNodesAreCompatible(compilerResults, semanticErrors, arrayFieldTypeNode.getNonArrayFieldTypeNode(), genericBoundsArrayFieldTypeNode.getNonArrayFieldTypeNode());
    }

    private void checkFieldTypeNodesAreCompatible(final CompilerResults compilerResults, final List<SemanticError> semanticErrors, final NonArrayFieldTypeNode nonArrayFieldTypeNode, final NonArrayFieldTypeNode genericBoundsNonArrayFieldTypeNode) {

        if(nonArrayFieldTypeNode instanceof GenericObjectFieldTypeNode) {
            return;
        }

        //CHECK_ALL_FIELD_TYPES_PRESENT
        switch (nonArrayFieldTypeNode) {
            case DoubleFieldTypeNode ignored -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof DoubleFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case FloatFieldTypeNode ignored -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof FloatFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case Int32FieldTypeNode ignored -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof Int32FieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case Int64FieldTypeNode ignored -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof Int64FieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case BoolFieldTypeNode ignored -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof BoolFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case StringFieldTypeNode ignored -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof StringFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case BytesFieldTypeNode ignored -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof BytesFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case DecimalFieldTypeNode ignored -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof DecimalFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case DateFieldTypeNode ignored -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof DateFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case DateTimeFieldTypeNode ignored -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof DateTimeFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case LocalDateFieldTypeNode ignored -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof LocalDateFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case LocalDateTimeFieldTypeNode ignored -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof LocalDateTimeFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case ObjectFieldTypeNode ignored -> {
                //Do nothing this should be picked up elsewhere
            }
            case TypeFieldTypeNode typeFieldTypeNode -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof TypeFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                    return;
                }
                var allSuperClassesAndInterfacesForType = getAllSuperClassesAndInterfacesForKeyOrTypeFieldTypeNode(compilerResults, typeFieldTypeNode);
                if(!allSuperClassesAndInterfacesForType.contains(ParseTreeUtils.getNamespaceNameString(typeFieldTypeNode.getNamespaceNameNode()))) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, typeFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case KeyFieldTypeNode keyFieldTypeNode -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof KeyFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                    return;
                }
                var allSuperClassesAndInterfacesForKey = getAllSuperClassesAndInterfacesForKeyOrTypeFieldTypeNode(compilerResults, keyFieldTypeNode);
                if(!allSuperClassesAndInterfacesForKey.contains(ParseTreeUtils.getNamespaceNameString(keyFieldTypeNode.getNamespaceNameNode()))) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, keyFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case EnumFieldTypeNode enumFieldTypeNode -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof EnumFieldTypeNode genericBoundsNonArrayFieldTypeNodeAsEnumFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                    return;
                }
                if(!ParseTreeUtils.getNamespaceNameString(enumFieldTypeNode.getNamespaceNameNode()).equals(ParseTreeUtils.getNamespaceNameString(genericBoundsNonArrayFieldTypeNodeAsEnumFieldTypeNode.getNamespaceNameNode()))) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, enumFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                }
            }
            case ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof ValueOrErrorFieldTypeNode genericBoundsNonArrayFieldTypeNodeAsValueOrErrorTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                    return;
                }
                checkFieldTypeNodesAreCompatible(compilerResults, semanticErrors, valueOrErrorFieldTypeNode.getFieldTypeNode(), genericBoundsNonArrayFieldTypeNodeAsValueOrErrorTypeNode.getFieldTypeNode());
            }
            case SetFieldTypeNode setFieldTypeNode -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof SetFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                    return;
                }
                var genericBoundsNonArrayFieldTypeNodeAsSetFieldTypeNode = (ValueOrErrorFieldTypeNode) genericBoundsNonArrayFieldTypeNode;
                checkFieldTypeNodesAreCompatible(compilerResults, semanticErrors, setFieldTypeNode.getFieldTypeNode(), genericBoundsNonArrayFieldTypeNodeAsSetFieldTypeNode.getFieldTypeNode());
            }
            case ListFieldTypeNode listFieldTypeNode -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof ListFieldTypeNode genericBoundsNonArrayFieldTypeNodeAsListFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                    return;
                }
                checkFieldTypeNodesAreCompatible(compilerResults, semanticErrors, listFieldTypeNode.getFieldTypeNode(), genericBoundsNonArrayFieldTypeNodeAsListFieldTypeNode.getFieldTypeNode());
            }
            case MapFieldTypeNode mapFieldTypeNode -> {
                if(!(genericBoundsNonArrayFieldTypeNode instanceof MapFieldTypeNode genericBoundsNonArrayFieldTypeNodeAsMapFieldTypeNode)) {
                    semanticErrors.add(createSemanticError(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS, nonArrayFieldTypeNode, ParseTreeUtils.convertFieldTypeToString(nonArrayFieldTypeNode), ParseTreeUtils.convertFieldTypeToString(genericBoundsNonArrayFieldTypeNode)));
                    return;
                }
                checkFieldTypeNodesAreCompatible(compilerResults, semanticErrors, mapFieldTypeNode.getKeyFieldTypeNode(), genericBoundsNonArrayFieldTypeNodeAsMapFieldTypeNode.getKeyFieldTypeNode());
                checkFieldTypeNodesAreCompatible(compilerResults, semanticErrors, mapFieldTypeNode.getValueFieldTypeNode(), genericBoundsNonArrayFieldTypeNodeAsMapFieldTypeNode.getValueFieldTypeNode());
            }
            default -> throw new IllegalStateException("Unexpected value: " + nonArrayFieldTypeNode);
        }
    }

    private Set<String> getAllSuperClassesAndInterfacesForKeyOrTypeFieldTypeNode(final CompilerResults compilerResults, final KeyOrTypeFieldTypeNode keyOrTypeFieldTypeNode) {
        var returnSet = new HashSet<String>();
        var lookedUpType = compilerResults
            .getAllTypeNodeMap()
            .get(ParseTreeUtils.getNamespaceNameString(keyOrTypeFieldTypeNode.getNamespaceNameNode()));
        getAllSuperClassesAndInterfacesForKeyOrTypeFieldTypeNode(returnSet, compilerResults, lookedUpType);
        return returnSet;
    }

    private void getAllSuperClassesAndInterfacesForKeyOrTypeFieldTypeNode(final Set<String> setSoFar, final CompilerResults compilerResults, final KeyOrTypeNode keyOrTypeNode) {
        keyOrTypeNode
            .getImplementsListNode()
            .stream()
            .flatMap(iln -> iln.getNamespaceNameGenericParametersNodes().stream())
            .forEach(
                nngp -> {
                    setSoFar.add(ParseTreeUtils.getNamespaceNameString(nngp.getNamespaceNameNode()));
                    var implementsListItemType = compilerResults
                        .getAllTypeNodeMap()
                        .get(ParseTreeUtils.getNamespaceNameString(nngp.getNamespaceNameNode()));
                    getAllSuperClassesAndInterfacesForKeyOrTypeFieldTypeNode(setSoFar, compilerResults, implementsListItemType);
                }
            );
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

    private void addSemanticErrorIfNotDuplicate(List<SemanticError> semanticErrors, SemanticError semanticError) {
        var semanticErrorMessagesSet = semanticErrors
                .stream()
                .map(SemanticError::getFullErrorMessage)
                .collect(Collectors.toSet());
        if(!semanticErrorMessagesSet.contains(semanticError.getFullErrorMessage())) {
            semanticErrors.add(semanticError);
        }
    }

}