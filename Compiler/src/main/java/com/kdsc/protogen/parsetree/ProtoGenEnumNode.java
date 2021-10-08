package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetree.commoninterfaces.HasNamespaceName;
import com.kdsc.protogen.parsetree.commoninterfaces.TopLevelObject;
import com.kdsc.protogen.utils.parameterchecking.Optionals;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class ProtoGenEnumNode extends BaseParseTreeNode implements TopLevelObject, HasNamespaceName {

    private final NamespaceNameNode namespaceNameNode;
    private final Optional<EnumVersionsNode> enumVersionsNode;
    private final Optional<EnumCasesNode> enumCasesNode;

    public ProtoGenEnumNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final NamespaceNameNode namespaceNameNode,
        final Optional<EnumVersionsNode> enumVersionsNode,
        final Optional<EnumCasesNode> enumCasesNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameNode);
        Objects.requireNonNull(enumVersionsNode);
        Objects.requireNonNull(enumCasesNode);
        Optionals.requireOne(enumVersionsNode, enumCasesNode);
        this.namespaceNameNode = namespaceNameNode;
        this.enumVersionsNode = enumVersionsNode;
        this.enumCasesNode = enumCasesNode;
    }

    public NamespaceNameNode getNamespaceNameNode() {
        return namespaceNameNode;
    }

    public Optional<EnumVersionsNode> getEnumVersionsNode() {
        return enumVersionsNode;
    }

    public Optional<EnumCasesNode> getEnumCasesNode() {
        return enumCasesNode;
    }

    public List<NamespaceNode> getNamespaceNodes() {
        return namespaceNameNode.getNamespaceNodes();
    }

    public NameNode getNameNode() {
        return namespaceNameNode.getNameNode();
    }

    @Override
    public boolean isLibraryNode() {
        return false;
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, ProtoGenEnumNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, namespaceNameNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, enumVersionsNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, enumCasesNode);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public ProtoGenEnumNode clone() {
        return new ProtoGenEnumNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            namespaceNameNode.clone(),
            com.kdsc.protogen.parsetree.utils.clone.Optionals.clone(enumVersionsNode),
            com.kdsc.protogen.parsetree.utils.clone.Optionals.clone(enumCasesNode)
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        ProtoGenEnumNode that = (ProtoGenEnumNode) object;
        return namespaceNameNode.equals(that.namespaceNameNode) && enumVersionsNode.equals(that.enumVersionsNode) && enumCasesNode.equals(that.enumCasesNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), namespaceNameNode, enumVersionsNode, enumCasesNode);
    }

}