package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetree.BaseParseTreeNode;
import com.kdsc.protogen.utils.parameterchecking.Numbers;

import java.util.Objects;

//TODO:KMD Think about the names ProtoGenType etc, not happy with it
public class ArrayFieldTypeNode extends BaseParseTreeNode {

    private final NonArrayFieldTypeNode nonArrayFieldTypeNode;
    private final long dimensions;

    public ArrayFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final NonArrayFieldTypeNode nonArrayFieldTypeNode,
        final long dimensions
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(nonArrayFieldTypeNode);
        Numbers.requireOneOrGreater(dimensions);
        this.nonArrayFieldTypeNode = nonArrayFieldTypeNode;
        this.dimensions = dimensions;
    }

    public NonArrayFieldTypeNode getNonArrayFieldTypeNode() {
        return nonArrayFieldTypeNode;
    }

    public long getDimensions() {
        return dimensions;
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, ArrayFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, nonArrayFieldTypeNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Dimensions", dimensions);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public ArrayFieldTypeNode clone() {
        return new ArrayFieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            nonArrayFieldTypeNode.clone(),
            dimensions
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        ArrayFieldTypeNode that = (ArrayFieldTypeNode) object;
        return dimensions == that.dimensions && nonArrayFieldTypeNode.equals(that.nonArrayFieldTypeNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nonArrayFieldTypeNode, dimensions);
    }

}