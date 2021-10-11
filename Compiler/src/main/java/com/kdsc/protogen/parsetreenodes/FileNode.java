package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.utils.clone.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class FileNode extends BaseParseTreeNode {

    private final List<TypeNode> typeNodes;
    private final List<KeyNode> keyNodes;
    private final List<EnumNode> enumNodes;

    //TODO:KMD we don't actually need line and char position for FileNodes
    public FileNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<TypeNode> typeNodes,
        final List<KeyNode> keyNodes,
        final List<EnumNode> enumNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(typeNodes);
        Objects.requireNonNull(keyNodes);
        Objects.requireNonNull(enumNodes);
        this.typeNodes = Collections.unmodifiableList(typeNodes);
        this.keyNodes = Collections.unmodifiableList(keyNodes);
        this.enumNodes = Collections.unmodifiableList(enumNodes);
    }

    public List<TypeNode> getTypeNodes() {
        return typeNodes;
    }

    public List<KeyNode> getKeyNodes() {
        return keyNodes;
    }

    public List<EnumNode> getEnumNodes() {
        return enumNodes;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, FileNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, typeNodes);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, keyNodes);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, enumNodes);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public FileNode clone() {
        return new FileNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            Lists.clone(typeNodes),
            Lists.clone(keyNodes),
            Lists.clone(enumNodes)
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        FileNode fileNode = (FileNode) object;
        return typeNodes.equals(fileNode.typeNodes) && keyNodes.equals(fileNode.keyNodes) && enumNodes.equals(fileNode.enumNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), typeNodes, keyNodes, enumNodes);
    }

}