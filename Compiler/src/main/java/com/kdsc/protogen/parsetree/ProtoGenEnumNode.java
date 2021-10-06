package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.commoninterfaces.HasNamespaceName;
import com.kdsc.protogen.parsetree.commoninterfaces.TopLevelObject;
import com.kdsc.protogen.utils.parameterchecking.Optionals;

import java.util.Objects;
import java.util.Optional;

public class ProtoGenEnumNode extends BaseParseTreeNode implements TopLevelObject, HasNamespaceName {

    private NamespaceNameNode namespaceNameNode;
    private Optional<EnumVersionsNode> enumVersionsNode;
    private Optional<EnumCasesNode> enumCasesNode;

    public ProtoGenEnumNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final NamespaceNameNode namespaceNameNode,
        final Optional<EnumVersionsNode> enumVersionsNode,
        final Optional<EnumCasesNode> enumCasesNode
    ) {
        //TODO:KMD Also need to make all lists immutable
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameNode);
        Objects.requireNonNull(enumVersionsNode);
        Objects.requireNonNull(enumCasesNode);
        Optionals.requireAtMostOne(enumVersionsNode, enumCasesNode);
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

    @Override
    public boolean isLibraryNode() {
        return false;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, ProtoGenEnumNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, namespaceNameNode);
        fieldToFormattedStringField(stringBuilder, enumVersionsNode);
        fieldToFormattedStringField(stringBuilder, enumCasesNode);
        return indentString(stringBuilder, indentationLevel);
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
    public boolean equals(Object object) {
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