package com.kdsc.protogen.parsetreepostprocessing;

import com.kdsc.protogen.parsetree.*;
import com.kdsc.protogen.parsetree.fieldtypenodes.*;
import com.kdsc.protogen.parsetree.utils.ParseTreeUtils;
import com.kdsc.protogen.parsetree.utils.clone.Lists;
import com.kdsc.protogen.parsetree.utils.clone.Optionals;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UndetectableNodeReplacer {

    public List<FileNode> replaceUndetectableNodes(final List<FileNode> fileNodes) {

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

    private List<FileNode> replaceUndetectableNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<FileNode> fileNodes) {
        return replaceUndetectableNodesForFileNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fileNodes);
    }

    private List<FileNode> replaceUndetectableNodesForFileNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<FileNode> fileNodes) {
        return fileNodes
            .stream()
            .map(fn -> replaceUndetectableNodesForFileNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fn))
            .collect(Collectors.toList());
    }

    private FileNode replaceUndetectableNodesForFileNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final FileNode fileNode) {
        return new FileNode(
            fileNode.getSourceFileName(),
            fileNode.getLine(),
            fileNode.getCharPosition(),
            replaceUndetectableNodesForProtoGenTypeNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fileNode.getProtoGenTypeNodes()),
            replaceUndetectableNodesForProtoGenKeyNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fileNode.getProtoGenKeyNodes()),
            Lists.clone(fileNode.getProtoGenEnumNodes())
        );
    }

    private List<ProtoGenTypeNode> replaceUndetectableNodesForProtoGenTypeNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<ProtoGenTypeNode> typeNodes) {
        return typeNodes
            .stream()
            .map(tn -> replaceUndetectableNodesForProtoGenTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, tn))
            .collect(Collectors.toList());
    }

    private ProtoGenTypeNode replaceUndetectableNodesForProtoGenTypeNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final ProtoGenTypeNode protoGenTypeNode) {
        return new ProtoGenTypeNode(
            protoGenTypeNode.getSourceFileName(),
            protoGenTypeNode.getLine(),
            protoGenTypeNode.getCharPosition(),
            protoGenTypeNode.isInterface(),
            protoGenTypeNode.getNamespaceNameGenericParametersWithBoundsNode().clone(),
            replaceUndetectableNodesForOptionalImplementsListNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, protoGenTypeNode.getImplementsListNode()),
            replaceUndetectableNodesForOptionalVersionsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, protoGenTypeNode.getVersionsNode()),
            replaceUndetectableNodesForOptionalFieldsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, protoGenTypeNode.getFieldsNode())
        );
    }

    private Optional<VersionsNode> replaceUndetectableNodesForOptionalVersionsNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final Optional<VersionsNode> versionsNode) {
        return versionsNode.isEmpty() ? Optional.empty() : Optional.of(replaceUndetectableNodesForVersionsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, versionsNode.get()));
    }

    private VersionsNode replaceUndetectableNodesForVersionsNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final VersionsNode versionsNode) {
        return new VersionsNode(
            versionsNode.getSourceFileName(),
            versionsNode.getLine(),
            versionsNode.getCharPosition(),
            replaceUndetectableNodesForVersionNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, versionsNode.getVersionNodes())
        );
    }

    private List<VersionNode> replaceUndetectableNodesForVersionNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<VersionNode> versionNodes) {
        return versionNodes
            .stream()
            .map(vn -> replaceUndetectableNodesForVersionNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, vn))
            .collect(Collectors.toList());
    }

    private VersionNode replaceUndetectableNodesForVersionNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final VersionNode versionNode) {
        return new VersionNode(
            versionNode.getSourceFileName(),
            versionNode.getLine(),
            versionNode.getCharPosition(),
            versionNode.getVersionNumberNode().clone(),
            Optionals.clone(versionNode.getGenericParametersWithBoundsNode()),
            replaceUndetectableNodesForOptionalImplementsListNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, versionNode.getImplementsListNode()),
            replaceUndetectableNodesForOptionalFieldsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, versionNode.getFieldsNode())
        );
    }

    private Optional<ImplementsListNode> replaceUndetectableNodesForOptionalImplementsListNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final Optional<ImplementsListNode> implementsListNode) {
        return implementsListNode.isEmpty() ? Optional.empty() : Optional.of(replaceUndetectableNodesForImplementsListNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, implementsListNode.get()));
    }

    private ImplementsListNode replaceUndetectableNodesForImplementsListNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final ImplementsListNode implementsListNode) {
        return new ImplementsListNode(
            implementsListNode.getSourceFileName(),
            implementsListNode.getLine(),
            implementsListNode.getCharPosition(),
            replaceUndetectableNodesForNamespaceNameGenericParametersNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, implementsListNode.getNamespaceNameGenericParametersNodes())
        );
    }

    private List<NamespaceNameGenericParametersNode> replaceUndetectableNodesForNamespaceNameGenericParametersNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<NamespaceNameGenericParametersNode> namespaceNameGenericParametersNodes) {
        return namespaceNameGenericParametersNodes
            .stream()
            .map(nngpn -> replaceUndetectableNodesForNamespaceNameGenericParametersNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, nngpn))
            .collect(Collectors.toList());
    }


    private NamespaceNameGenericParametersNode replaceUndetectableNodesForNamespaceNameGenericParametersNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final NamespaceNameGenericParametersNode namespaceNameGenericParametersNode) {
        return new NamespaceNameGenericParametersNode(
            namespaceNameGenericParametersNode.getSourceFileName(),
            namespaceNameGenericParametersNode.getLine(),
            namespaceNameGenericParametersNode.getCharPosition(),
            namespaceNameGenericParametersNode.getNamespaceNameNode().clone(),
            replaceUndetectableNodesForOptionalGenericParametersNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, namespaceNameGenericParametersNode.getGenericParametersNode())
        );
    }

    private Optional<GenericParametersNode> replaceUndetectableNodesForOptionalGenericParametersNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final Optional<GenericParametersNode> genericParametersNode) {
        return genericParametersNode.isEmpty() ? Optional.empty() : Optional.of(replaceUndetectableNodesForGenericParametersNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, genericParametersNode.get()));
    }

    private GenericParametersNode replaceUndetectableNodesForGenericParametersNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final GenericParametersNode genericParametersNode) {
        return new GenericParametersNode(
            genericParametersNode.getSourceFileName(),
            genericParametersNode.getLine(),
            genericParametersNode.getCharPosition(),
            replaceUndetectableNodesForFieldTypeNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, genericParametersNode.getFieldTypeNodes())
        );
    }

    private List<FieldTypeNode> replaceUndetectableNodesForFieldTypeNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<FieldTypeNode> fieldTypeNodes) {
        return fieldTypeNodes
            .stream()
            .map(ftn -> replaceUndetectableNodesForFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, ftn))
            .collect(Collectors.toList());
    }

    private List<ProtoGenKeyNode> replaceUndetectableNodesForProtoGenKeyNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<ProtoGenKeyNode> keyNodes) {
        return keyNodes
            .stream()
            .map(tn -> replaceUndetectableNodesForProtoGenKeyNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, tn))
            .collect(Collectors.toList());
    }

    private ProtoGenKeyNode replaceUndetectableNodesForProtoGenKeyNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final ProtoGenKeyNode protoGenKeyNode) {
        return new ProtoGenKeyNode(
            protoGenKeyNode.getSourceFileName(),
            protoGenKeyNode.getLine(),
            protoGenKeyNode.getCharPosition(),
            protoGenKeyNode.isInterface(),
            protoGenKeyNode.getNamespaceNameGenericParametersWithBoundsNode().clone(),
            replaceUndetectableNodesForOptionalImplementsListNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, protoGenKeyNode.getImplementsListNode()),
            replaceUndetectableNodesForOptionalVersionsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, protoGenKeyNode.getVersionsNode()),
            replaceUndetectableNodesForOptionalFieldsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, protoGenKeyNode.getFieldsNode())
        );
    }

    private Optional<FieldsNode> replaceUndetectableNodesForOptionalFieldsNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final Optional<FieldsNode> fieldsNode) {
        return fieldsNode.isEmpty() ? Optional.empty() : Optional.of(replaceUndetectableNodesForFieldsNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fieldsNode.get()));
    }

    private FieldsNode replaceUndetectableNodesForFieldsNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final FieldsNode fieldsNode) {
        return new FieldsNode(
            fieldsNode.getSourceFileName(),
            fieldsNode.getLine(),
            fieldsNode.getCharPosition(),
            replaceUndetectableNodesForFieldNodes(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fieldsNode.getFieldNodes())
        );
    }

    private List<FieldNode> replaceUndetectableNodesForFieldNodes(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final List<FieldNode> fieldNodes) {
        return fieldNodes
            .stream()
            .map(fn -> replaceUndetectableNodesForFieldNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fn))
            .collect(Collectors.toList());
    }

    private FieldNode replaceUndetectableNodesForFieldNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final FieldNode fieldNode) {
        return new FieldNode(
            fieldNode.getSourceFileName(),
            fieldNode.getLine(),
            fieldNode.getCharPosition(),
            fieldNode.getFieldNameNode().clone(),
            replaceUndetectableNodesForFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fieldNode.getFieldTypeNode())
        );
    }

    private FieldTypeNode replaceUndetectableNodesForFieldTypeNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final FieldTypeNode fieldTypeNode) {
        return new FieldTypeNode(
            fieldTypeNode.getSourceFileName(),
            fieldTypeNode.getLine(),
            fieldTypeNode.getCharPosition(),
            fieldTypeNode.isOptional(),
            replaceUndetectableNodesForOptionalArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fieldTypeNode.getArrayFieldTypeNode()),
            replaceUndetectableNodesForOptionalNonArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, fieldTypeNode.getNonArrayFieldTypeNode())
        );
    }

    private Optional<ArrayFieldTypeNode> replaceUndetectableNodesForOptionalArrayFieldTypeNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final Optional<ArrayFieldTypeNode> arrayFieldTypeNode) {
        return arrayFieldTypeNode.isEmpty() ? Optional.empty() : Optional.of(replaceUndetectableNodesForArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, arrayFieldTypeNode.get()));
    }

    private ArrayFieldTypeNode replaceUndetectableNodesForArrayFieldTypeNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final ArrayFieldTypeNode arrayFieldTypeNode) {
        return new ArrayFieldTypeNode(
            arrayFieldTypeNode.getSourceFileName(),
            arrayFieldTypeNode.getLine(),
            arrayFieldTypeNode.getCharPosition(),
            replaceUndetectableNodesForNonArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, arrayFieldTypeNode.getNonArrayFieldTypeNode()),
            arrayFieldTypeNode.getDimensions()
        );
    }

    private Optional<NonArrayFieldTypeNode> replaceUndetectableNodesForOptionalNonArrayFieldTypeNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final Optional<NonArrayFieldTypeNode> nonArrayFieldTypeNode) {
        return nonArrayFieldTypeNode.isEmpty() ? Optional.empty() : Optional.of(replaceUndetectableNodesForNonArrayFieldTypeNode(typesToSearchForAsStrings, keysToSearchForAsStrings, enumsToSearchForAsStrings, nonArrayFieldTypeNode.get()));
    }

    private NonArrayFieldTypeNode replaceUndetectableNodesForNonArrayFieldTypeNode(final Set<String> typesToSearchForAsStrings, final Set<String> keysToSearchForAsStrings, final Set<String> enumsToSearchForAsStrings, final NonArrayFieldTypeNode nonArrayFieldTypeNode) {

        if(nonArrayFieldTypeNode instanceof ObjectFieldTypeNode objectFieldTypeNode) {
            if(typesToSearchForAsStrings.contains(ParseTreeUtils.getNamespaceNameString(objectFieldTypeNode.getNamespaceNameNode()))) {
                return new TypeFieldTypeNode(
                    nonArrayFieldTypeNode.getSourceFileName(),
                    nonArrayFieldTypeNode.getLine(),
                    nonArrayFieldTypeNode.getCharPosition(),
                    objectFieldTypeNode.getNamespaceNameGenericParametersNode().clone()
                );
            }
            if(keysToSearchForAsStrings.contains(ParseTreeUtils.getNamespaceNameString(objectFieldTypeNode.getNamespaceNameNode()))) {
                return new KeyFieldTypeNode(
                    nonArrayFieldTypeNode.getSourceFileName(),
                    nonArrayFieldTypeNode.getLine(),
                    nonArrayFieldTypeNode.getCharPosition(),
                    objectFieldTypeNode.getNamespaceNameGenericParametersNode().clone()
                );
            }
            if(enumsToSearchForAsStrings.contains(ParseTreeUtils.getNamespaceNameString(objectFieldTypeNode.getNamespaceNameNode()))) {
                return new EnumFieldTypeNode(
                    nonArrayFieldTypeNode.getSourceFileName(),
                    nonArrayFieldTypeNode.getLine(),
                    nonArrayFieldTypeNode.getCharPosition(),
                    objectFieldTypeNode.getNamespaceNameGenericParametersNode().clone()
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