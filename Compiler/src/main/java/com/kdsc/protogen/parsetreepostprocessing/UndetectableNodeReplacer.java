package com.kdsc.protogen.parsetreepostprocessing;

import com.kdsc.protogen.parsetree.*;
import com.kdsc.protogen.parsetree.fieldtypenodes.*;
import com.kdsc.protogen.parsetree.utils.ParseTreeUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TODO:KMD This could probably be made more generic, I so wish Java had proper generics without type erasure
public class UndetectableNodeReplacer {

    public static List<FileNode> replaceUndetectableNodes(final List<FileNode> fileNodes) {

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

    private static List<FileNode> replaceUndetectableNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<FileNode> fileNodes) {
        return replaceUndetectableNodesForFileNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fileNodes);
    }

    private static List<FileNode> replaceUndetectableNodesForFileNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<FileNode> fileNodes) {
        return fileNodes
            .stream()
            .map(fn -> replaceUndetectableNodesForFileNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fn))
            .collect(Collectors.toList());
    }

    private static FileNode replaceUndetectableNodesForFileNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final FileNode fileNode) {
        return new FileNode(
            fileNode.getSourceFileName(),
            fileNode.getLine(),
            fileNode.getCharPosition(),
            replaceUndetectableNodesForProtoGenTypeNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fileNode.getProtoGenTypeNodes()),
            replaceUndetectableNodesForProtoGenKeyNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fileNode.getProtoGenKeyNodes()),
            fileNode.getProtoGenEnumNodes()
        );
    }

    private static List<ProtoGenTypeNode> replaceUndetectableNodesForProtoGenTypeNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<ProtoGenTypeNode> typeNodes) {
        return typeNodes
            .stream()
            .map(tn -> replaceUndetectableNodesForProtoGenTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, tn))
            .collect(Collectors.toList());
    }

    private static ProtoGenTypeNode replaceUndetectableNodesForProtoGenTypeNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final ProtoGenTypeNode protoGenTypeNode) {
        return new ProtoGenTypeNode(
            protoGenTypeNode.getSourceFileName(),
            protoGenTypeNode.getLine(),
            protoGenTypeNode.getCharPosition(),
            protoGenTypeNode.isInterface(),
            protoGenTypeNode.getNamespaceNameGenericParametersWithBoundsNode(),
            replaceUndetectableNodesForOptionalImplementsListNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, protoGenTypeNode.getImplementsListNode()),
            replaceUndetectableNodesForOptionalVersionsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, protoGenTypeNode.getVersionsNode()),
            replaceUndetectableNodesForOptionalFieldsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, protoGenTypeNode.getFieldsNode())
        );
    }

    private static Optional<VersionsNode> replaceUndetectableNodesForOptionalVersionsNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final Optional<VersionsNode> versionsNode) {
        return versionsNode.isEmpty() ? Optional.empty() : Optional.of(replaceUndetectableNodesForVersionsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, versionsNode.get()));
    }

    private static VersionsNode replaceUndetectableNodesForVersionsNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final VersionsNode versionsNode) {
        return new VersionsNode(
            versionsNode.getSourceFileName(),
            versionsNode.getLine(),
            versionsNode.getCharPosition(),
            replaceUndetectableNodesForVersionNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, versionsNode.getVersionNodes())
        );
    }

    private static List<VersionNode> replaceUndetectableNodesForVersionNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<VersionNode> versionNodes) {
        return versionNodes
            .stream()
            .map(vn -> replaceUndetectableNodesForVersionNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, vn))
            .collect(Collectors.toList());
    }

    private static VersionNode replaceUndetectableNodesForVersionNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final VersionNode versionNode) {
        return new VersionNode(
            versionNode.getSourceFileName(),
            versionNode.getLine(),
            versionNode.getCharPosition(),
            versionNode.getVersionNumberNode(),
            versionNode.getGenericParametersWithBoundsNode(),
            replaceUndetectableNodesForOptionalImplementsListNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, versionNode.getImplementsListNode()),
            replaceUndetectableNodesForOptionalFieldsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, versionNode.getFieldsNode())
        );
    }

    private static Optional<ImplementsListNode> replaceUndetectableNodesForOptionalImplementsListNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final Optional<ImplementsListNode> implementsListNode) {
        return implementsListNode.isEmpty() ? Optional.empty() : Optional.of(replaceUndetectableNodesForImplementsListNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, implementsListNode.get()));
    }

    private static ImplementsListNode replaceUndetectableNodesForImplementsListNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final ImplementsListNode implementsListNode) {
        return new ImplementsListNode(
            implementsListNode.getSourceFileName(),
            implementsListNode.getLine(),
            implementsListNode.getCharPosition(),
            replaceUndetectableNodesForNamespaceNameGenericParametersNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, implementsListNode.getNamespaceNameGenericParametersNodes())
        );
    }

    private static List<NamespaceNameGenericParametersNode> replaceUndetectableNodesForNamespaceNameGenericParametersNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<NamespaceNameGenericParametersNode> namespaceNameGenericParametersNodes) {
        return namespaceNameGenericParametersNodes
            .stream()
            .map(nngpn -> replaceUndetectableNodesForNamespaceNameGenericParametersNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, nngpn))
            .collect(Collectors.toList());
    }


    private static NamespaceNameGenericParametersNode replaceUndetectableNodesForNamespaceNameGenericParametersNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final NamespaceNameGenericParametersNode namespaceNameGenericParametersNode) {
        return new NamespaceNameGenericParametersNode(
            namespaceNameGenericParametersNode.getSourceFileName(),
            namespaceNameGenericParametersNode.getLine(),
            namespaceNameGenericParametersNode.getCharPosition(),
            namespaceNameGenericParametersNode.getNamespaceNameNode(),
            replaceUndetectableNodesForOptionalGenericParametersNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, namespaceNameGenericParametersNode.getGenericParametersNode())
        );
    }

    private static Optional<GenericParametersNode> replaceUndetectableNodesForOptionalGenericParametersNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final Optional<GenericParametersNode> genericParametersNode) {
        return genericParametersNode.isEmpty() ? Optional.empty() : Optional.of(replaceUndetectableNodesForGenericParametersNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, genericParametersNode.get()));
    }

    private static GenericParametersNode replaceUndetectableNodesForGenericParametersNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final GenericParametersNode genericParametersNode) {
        return new GenericParametersNode(
            genericParametersNode.getSourceFileName(),
            genericParametersNode.getLine(),
            genericParametersNode.getCharPosition(),
            replaceUndetectableNodesForFieldTypeNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, genericParametersNode.getFieldTypeNodes())
        );
    }

    private static List<FieldTypeNode> replaceUndetectableNodesForFieldTypeNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<FieldTypeNode> fieldTypeNodes) {
        return fieldTypeNodes
            .stream()
            .map(ftn -> replaceUndetectableNodesForFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, ftn))
            .collect(Collectors.toList());
    }

    private static List<ProtoGenKeyNode> replaceUndetectableNodesForProtoGenKeyNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<ProtoGenKeyNode> keyNodes) {
        return keyNodes
            .stream()
            .map(tn -> replaceUndetectableNodesForProtoGenKeyNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, tn))
            .collect(Collectors.toList());
    }

    private static ProtoGenKeyNode replaceUndetectableNodesForProtoGenKeyNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final ProtoGenKeyNode protoGenKeyNode) {
        return new ProtoGenKeyNode(
            protoGenKeyNode.getSourceFileName(),
            protoGenKeyNode.getLine(),
            protoGenKeyNode.getCharPosition(),
            protoGenKeyNode.isInterface(),
            protoGenKeyNode.getNamespaceNameGenericParametersWithBoundsNode(),
            replaceUndetectableNodesForOptionalImplementsListNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, protoGenKeyNode.getImplementsListNode()),
            replaceUndetectableNodesForOptionalVersionsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, protoGenKeyNode.getVersionsNode()),
            replaceUndetectableNodesForOptionalFieldsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, protoGenKeyNode.getFieldsNode())
        );
    }

    private static Optional<FieldsNode> replaceUndetectableNodesForOptionalFieldsNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final Optional<FieldsNode> fieldsNode) {
        return fieldsNode.isEmpty() ? Optional.empty() : Optional.of(replaceUndetectableNodesForFieldsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fieldsNode.get()));
    }

    private static FieldsNode replaceUndetectableNodesForFieldsNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final FieldsNode fieldsNode) {
        return new FieldsNode(
            fieldsNode.getSourceFileName(),
            fieldsNode.getLine(),
            fieldsNode.getCharPosition(),
            replaceUndetectableNodesForFieldNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fieldsNode.getFieldNodes())
        );
    }

    private static List<FieldNode> replaceUndetectableNodesForFieldNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<FieldNode> fieldNodes) {
        return fieldNodes
            .stream()
            .map(fn -> replaceUndetectableNodesForFieldNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fn))
            .collect(Collectors.toList());
    }

    private static FieldNode replaceUndetectableNodesForFieldNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final FieldNode fieldNode) {
        return new FieldNode(
            fieldNode.getSourceFileName(),
            fieldNode.getLine(),
            fieldNode.getCharPosition(),
            fieldNode.getFieldNameNode(),
            replaceUndetectableNodesForFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fieldNode.getFieldTypeNode())
        );
    }

    private static FieldTypeNode replaceUndetectableNodesForFieldTypeNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final FieldTypeNode fieldTypeNode) {
        return new FieldTypeNode(
            fieldTypeNode.getSourceFileName(),
            fieldTypeNode.getLine(),
            fieldTypeNode.getCharPosition(),
            fieldTypeNode.isOptional(),
            replaceUndetectableNodesForOptionalArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fieldTypeNode.getArrayFieldTypeNode()),
            replaceUndetectableNodesForOptionalNonArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fieldTypeNode.getNonArrayFieldTypeNode())
        );
    }

    private static Optional<ArrayFieldTypeNode> replaceUndetectableNodesForOptionalArrayFieldTypeNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final Optional<ArrayFieldTypeNode> arrayFieldTypeNode) {
        return arrayFieldTypeNode.isEmpty() ? Optional.empty() : Optional.of(replaceUndetectableNodesForArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, arrayFieldTypeNode.get()));
    }

    private static ArrayFieldTypeNode replaceUndetectableNodesForArrayFieldTypeNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final ArrayFieldTypeNode arrayFieldTypeNode) {
        return new ArrayFieldTypeNode(
            arrayFieldTypeNode.getSourceFileName(),
            arrayFieldTypeNode.getLine(),
            arrayFieldTypeNode.getCharPosition(),
            replaceUndetectableNodesForNonArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, arrayFieldTypeNode.getNonArrayFieldTypeNode()),
            arrayFieldTypeNode.getDimensions()
        );
    }

    private static Optional<NonArrayFieldTypeNode> replaceUndetectableNodesForOptionalNonArrayFieldTypeNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final Optional<NonArrayFieldTypeNode> nonArrayFieldTypeNode) {
        return nonArrayFieldTypeNode.isEmpty() ? Optional.empty() : Optional.of(replaceUndetectableNodesForNonArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, nonArrayFieldTypeNode.get()));
    }

    private static NonArrayFieldTypeNode replaceUndetectableNodesForNonArrayFieldTypeNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final NonArrayFieldTypeNode nonArrayFieldTypeNode) {

        if(nonArrayFieldTypeNode instanceof ObjectFieldTypeNode objectFieldTypeNode) {
            if(typesToSearchForAsStrings.contains(ParseTreeUtils.getNamespaceNameString(objectFieldTypeNode.getNamespaceNameGenericParametersNode().getNamespaceNameNode()))) {
                return new TypeFieldTypeNode(
                    nonArrayFieldTypeNode.getSourceFileName(),
                    nonArrayFieldTypeNode.getLine(),
                    nonArrayFieldTypeNode.getCharPosition(),
                    objectFieldTypeNode.getNamespaceNameGenericParametersNode()
                );
            }
            if(keysToSearchForAsStrings.contains(ParseTreeUtils.getNamespaceNameString(objectFieldTypeNode.getNamespaceNameGenericParametersNode().getNamespaceNameNode()))) {
                return new KeyFieldTypeNode(
                    nonArrayFieldTypeNode.getSourceFileName(),
                    nonArrayFieldTypeNode.getLine(),
                    nonArrayFieldTypeNode.getCharPosition(),
                    objectFieldTypeNode.getNamespaceNameGenericParametersNode()
                );
            }
            if(enumsToSearchForAsStrings.contains(ParseTreeUtils.getNamespaceNameString(objectFieldTypeNode.getNamespaceNameGenericParametersNode().getNamespaceNameNode()))) {
                return new EnumFieldTypeNode(
                    nonArrayFieldTypeNode.getSourceFileName(),
                    nonArrayFieldTypeNode.getLine(),
                    nonArrayFieldTypeNode.getCharPosition(),
                    objectFieldTypeNode.getNamespaceNameGenericParametersNode()
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
                replaceUndetectableNodesForFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, setFieldTypeNode.getFieldTypeNode())
            );
        }

        if(nonArrayFieldTypeNode instanceof ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode) {
            return new ValueOrErrorFieldTypeNode(
                nonArrayFieldTypeNode.getSourceFileName(),
                nonArrayFieldTypeNode.getLine(),
                nonArrayFieldTypeNode.getCharPosition(),
                replaceUndetectableNodesForFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, valueOrErrorFieldTypeNode.getFieldTypeNode())
            );
        }

        return nonArrayFieldTypeNode;
    }
}