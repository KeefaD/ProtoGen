package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.fieldtypenodes.FieldTypeNode;
import com.kdsc.protogen.parsetree.fieldtypenodes.NonArrayFieldTypeNode;

public class SetFieldTypeNode extends NonArrayFieldTypeNode {

    private final FieldTypeNode entryFieldTypeNode;

    public SetFieldTypeNode(
        String sourceFileName,
        long line,
        long charPosition,
        FieldTypeNode entryFieldTypeNode
    ) {
        super(sourceFileName, line, charPosition);
        this.entryFieldTypeNode = entryFieldTypeNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//SetFieldTypeNode\n");
        stringBuilder.append(oneIndent() + "//Entry\n");
        stringBuilder.append(entryFieldTypeNode.toFormattedString(2));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}