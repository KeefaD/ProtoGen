package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.commoninterfaces.AllowableOutputFieldTypeNode;

import java.util.Objects;

public class ValueOrErrorFieldTypeNode extends NonArrayFieldTypeNode implements AllowableOutputFieldTypeNode {

    private final FieldTypeNode fieldTypeNode;

    public ValueOrErrorFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final FieldTypeNode fieldTypeNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(fieldTypeNode);
        this.fieldTypeNode = fieldTypeNode;
    }

    public FieldTypeNode getFieldTypeNode() {
        return fieldTypeNode;
    }

    //TODO:KMD Needs test
    public AllowableOutputFieldTypeNode getEntryFieldTypeNodeAsAllowableOutputFieldTypeNode() {
        return (AllowableOutputFieldTypeNode) fieldTypeNode;
    }

    //TODO:KMD Maybe I should get rid of the entry level
    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//ValueOrErrorFieldTypeNode\n");
        stringBuilder.append(oneIndent() + "//Entry\n");
        stringBuilder.append(fieldTypeNode.toFormattedString(2));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}