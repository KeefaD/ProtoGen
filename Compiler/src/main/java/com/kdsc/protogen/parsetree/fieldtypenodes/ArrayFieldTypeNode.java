package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseNode;
import com.kdsc.protogen.utils.parameterchecking.Numbers;

import java.util.Objects;

//TODO:KMD I think it is worth putting equals and hashcode on parse tree nodes
//TODO:KMD Think about the names ProtoGenType etc, not happy with it
public class ArrayFieldTypeNode extends BaseNode {

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
        stringBuilder.append("//ArrayFieldTypeNode\n");
        stringBuilder.append(nonArrayFieldTypeNode.toFormattedString(1));
        //TODO:KMD Shouldn't this have a \n
        stringBuilder.append(oneIndent() + "Dimensions : " + dimensions);
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}