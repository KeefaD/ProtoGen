package com.kdsc.protogen.parsetreepostprocessing;

import com.kdsc.protogen.parsetree.*;
import com.kdsc.protogen.parsetree.fieldtypenodes.*;
import com.kdsc.protogen.parsetree.utils.ParseTreeUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TODO:KMD This could probably be made more generic
public class UndetectableNodeReplacer {

    public static List<FileNode> replaceUndetectableNodes(List<FileNode> fileNodes) {

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

        var typesToSearchForAsStrings = topLevelObjects
            .stream()
            .filter(t -> t instanceof ProtoGenTypeNode)
            .map(tlo -> ParseTreeUtils.getNamespaceNameString(tlo.getNamespaceNameNode()))
            .collect(Collectors.toSet());

        var keysToSearchForAsStrings = topLevelObjects
            .stream()
            .filter(t -> t instanceof ProtoGenKeyNode)
            .map(tlo -> ParseTreeUtils.getNamespaceNameString(tlo.getNamespaceNameNode()))
            .collect(Collectors.toSet());

        var enumsToSearchForAsStrings = topLevelObjects
            .stream()
            .filter(t -> t instanceof ProtoGenEnumNode)
            .map(tlo -> ParseTreeUtils.getNamespaceNameString(tlo.getNamespaceNameNode()))
            .collect(Collectors.toSet());

        return replaceUndetectableNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fileNodes);
    }

    private static List<FileNode> replaceUndetectableNodes(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, List<FileNode> fileNodes) {
        return replaceUndetectableNodesForFileNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fileNodes);
    }

    private static List<FileNode> replaceUndetectableNodesForFileNodes(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, List<FileNode> fileNodes) {
        return fileNodes
            .stream()
            .map(fn -> replaceUndetectableNodesForFileNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fn))
            .collect(Collectors.toList());
    }

    private static FileNode replaceUndetectableNodesForFileNode(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, FileNode fileNode) {
        return new FileNode(
            fileNode.getSourceFileName(),
            fileNode.getLine(),
            fileNode.getCharPosition(),
            replaceUndetectableNodesForProtoGenTypeNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fileNode.getProtoGenTypeNodes()),
            replaceUndetectableNodesForProtoGenKeyNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fileNode.getProtoGenKeyNodes()),
            fileNode.getProtoGenEnumNodes()
        );
    }

    private static List<ProtoGenTypeNode> replaceUndetectableNodesForProtoGenTypeNodes(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, List<ProtoGenTypeNode> typeNodes) {
        return typeNodes
            .stream()
            .map(tn -> replaceUndetectableNodesForProtoGenTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, tn))
            .collect(Collectors.toList());
    }

    private static ProtoGenTypeNode replaceUndetectableNodesForProtoGenTypeNode(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, ProtoGenTypeNode protoGenTypeNode) {
        return new ProtoGenTypeNode(
            protoGenTypeNode.getSourceFileName(),
            protoGenTypeNode.getLine(),
            protoGenTypeNode.getCharPosition(),
            protoGenTypeNode.isInterface(),
            protoGenTypeNode.getNamespaceNameGenericParametersWithBoundsNode(),
            protoGenTypeNode.getImplementsListNode(),
            protoGenTypeNode.getVersionsNode(),
            replaceUndetectableNodesForFieldsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, protoGenTypeNode.getFieldsNode())
        );
    }

    private static List<ProtoGenKeyNode> replaceUndetectableNodesForProtoGenKeyNodes(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, List<ProtoGenKeyNode> keyNodes) {
        return keyNodes
            .stream()
            .map(tn -> replaceUndetectableNodesForProtoGenKeyNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, tn))
            .collect(Collectors.toList());
    }

    private static ProtoGenKeyNode replaceUndetectableNodesForProtoGenKeyNode(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, ProtoGenKeyNode protoGenKeyNode) {
        return new ProtoGenKeyNode(
            protoGenKeyNode.getSourceFileName(),
            protoGenKeyNode.getLine(),
            protoGenKeyNode.getCharPosition(),
            protoGenKeyNode.isInterface(),
            protoGenKeyNode.getNamespaceNameGenericParametersWithBoundsNode(),
            protoGenKeyNode.getImplementsListNode(),
            protoGenKeyNode.getVersionsNode(),
            replaceUndetectableNodesForFieldsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, protoGenKeyNode.getFieldsNode())
        );
    }

    private static Optional<FieldsNode> replaceUndetectableNodesForFieldsNode(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, Optional<FieldsNode> fieldsNode) {
        return fieldsNode.isEmpty() ? Optional.empty() : Optional.of(
            new FieldsNode(
                fieldsNode.get().getSourceFileName(),
                fieldsNode.get().getLine(),
                fieldsNode.get().getCharPosition(),
                replaceUndetectableNodesForFieldNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fieldsNode.get().getFieldNodes())
            )
        );
    }

    private static List<FieldNode> replaceUndetectableNodesForFieldNodes(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, List<FieldNode> fieldNodes) {
        return fieldNodes
            .stream()
            .map(fn -> replaceUndetectableNodesForFieldNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fn))
            .collect(Collectors.toList());
    }

    private static FieldNode replaceUndetectableNodesForFieldNode(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, FieldNode fieldNode) {
        return new FieldNode(
            fieldNode.getSourceFileName(),
            fieldNode.getLine(),
            fieldNode.getCharPosition(),
            fieldNode.getFieldNameNode(),
            replaceUndetectableNodesForFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fieldNode.getFieldTypeNode())
        );
    }

    private static FieldTypeNode replaceUndetectableNodesForFieldTypeNode(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, FieldTypeNode fieldTypeNode) {
        return new FieldTypeNode(
            fieldTypeNode.getSourceFileName(),
            fieldTypeNode.getLine(),
            fieldTypeNode.getCharPosition(),
            fieldTypeNode.isOptional(),
            replaceUndetectableNodesForOptionalArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fieldTypeNode.getArrayFieldTypeNode()),
            replaceUndetectableNodesForOptionalNonArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fieldTypeNode.getNonArrayFieldTypeNode())
        );
    }

    private static Optional<ArrayFieldTypeNode> replaceUndetectableNodesForOptionalArrayFieldTypeNode(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, Optional<ArrayFieldTypeNode> arrayFieldTypeNode) {
        return arrayFieldTypeNode.isEmpty() ? Optional.empty() : Optional.of(replaceUndetectableNodesForArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, arrayFieldTypeNode.get()));
    }

    private static ArrayFieldTypeNode replaceUndetectableNodesForArrayFieldTypeNode(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, ArrayFieldTypeNode arrayFieldTypeNode) {
        return new ArrayFieldTypeNode(
            arrayFieldTypeNode.getSourceFileName(),
            arrayFieldTypeNode.getLine(),
            arrayFieldTypeNode.getCharPosition(),
            replaceUndetectableNodesForNonArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, arrayFieldTypeNode.getNonArrayFieldTypeNode()),
            arrayFieldTypeNode.getDimensions()
        );
    }

    private static Optional<NonArrayFieldTypeNode> replaceUndetectableNodesForOptionalNonArrayFieldTypeNode(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, Optional<NonArrayFieldTypeNode> nonArrayFieldTypeNode) {
        return nonArrayFieldTypeNode.isEmpty() ? Optional.empty() : Optional.of(replaceUndetectableNodesForNonArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, nonArrayFieldTypeNode.get()));
    }

    private static NonArrayFieldTypeNode replaceUndetectableNodesForNonArrayFieldTypeNode(Set<String> typesToSearchForAsStrings, Set<String> keysToSearchForAsStrings, Set<String> enumsToSearchForAsStrings, NonArrayFieldTypeNode nonArrayFieldTypeNode) {

        if(nonArrayFieldTypeNode instanceof ObjectFieldTypeNode objectFieldTypeNode) {
            if(typesToSearchForAsStrings.contains(ParseTreeUtils.getNamespaceNameString(objectFieldTypeNode.getNamespaceNameGenericParametersWithoutBoundsNode().getNamespaceNameNode()))) {
                return new TypeFieldTypeNode(
                    nonArrayFieldTypeNode.getSourceFileName(),
                    nonArrayFieldTypeNode.getLine(),
                    nonArrayFieldTypeNode.getCharPosition(),
                    objectFieldTypeNode.getNamespaceNameGenericParametersWithoutBoundsNode()
                );
            }
            if(keysToSearchForAsStrings.contains(ParseTreeUtils.getNamespaceNameString(objectFieldTypeNode.getNamespaceNameGenericParametersWithoutBoundsNode().getNamespaceNameNode()))) {
                return new KeyFieldTypeNode(
                    nonArrayFieldTypeNode.getSourceFileName(),
                    nonArrayFieldTypeNode.getLine(),
                    nonArrayFieldTypeNode.getCharPosition(),
                    objectFieldTypeNode.getNamespaceNameGenericParametersWithoutBoundsNode()
                );
            }
            if(enumsToSearchForAsStrings.contains(ParseTreeUtils.getNamespaceNameString(objectFieldTypeNode.getNamespaceNameGenericParametersWithoutBoundsNode().getNamespaceNameNode()))) {
                return new EnumFieldTypeNode(
                    nonArrayFieldTypeNode.getSourceFileName(),
                    nonArrayFieldTypeNode.getLine(),
                    nonArrayFieldTypeNode.getCharPosition(),
                    objectFieldTypeNode.getNamespaceNameGenericParametersWithoutBoundsNode()
                );
            }
        }

        if(nonArrayFieldTypeNode instanceof MapFieldTypeNode mapFieldTypeNode) {
            return new MapFieldTypeNode(
                nonArrayFieldTypeNode.getSourceFileName(),
                nonArrayFieldTypeNode.getLine(),
                nonArrayFieldTypeNode.getCharPosition(),
                replaceUndetectableNodesForFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, mapFieldTypeNode.getKeyFieldTypeNode()),
                replaceUndetectableNodesForFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, mapFieldTypeNode.getValueFieldTypeNode())
            );
        }

        if(nonArrayFieldTypeNode instanceof SetFieldTypeNode setFieldTypeNode) {
            return new SetFieldTypeNode(
                nonArrayFieldTypeNode.getSourceFileName(),
                nonArrayFieldTypeNode.getLine(),
                nonArrayFieldTypeNode.getCharPosition(),
                replaceUndetectableNodesForFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, setFieldTypeNode.getEntryFieldTypeNode())
            );
        }

        if(nonArrayFieldTypeNode instanceof ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode) {
            return new ValueOrErrorFieldTypeNode(
                nonArrayFieldTypeNode.getSourceFileName(),
                nonArrayFieldTypeNode.getLine(),
                nonArrayFieldTypeNode.getCharPosition(),
                replaceUndetectableNodesForFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, valueOrErrorFieldTypeNode.getEntryFieldTypeNode())
            );
        }

        return nonArrayFieldTypeNode;
    }
}