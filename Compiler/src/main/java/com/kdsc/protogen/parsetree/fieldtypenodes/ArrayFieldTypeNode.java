package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseParseTreeNode;
import com.kdsc.protogen.utils.parameterchecking.Numbers;

import java.util.Objects;

//TODO:KMD I think it is worth putting equals and hashcode on parse tree nodes
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
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, ArrayFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, nonArrayFieldTypeNode);
        fieldToFormattedStringField(stringBuilder, "Dimensions", dimensions);
        return indentString(stringBuilder, indentationLevel);
    }

}