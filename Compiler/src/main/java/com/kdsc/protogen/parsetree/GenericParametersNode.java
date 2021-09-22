package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.fieldtypenodes.FieldTypeNode;

import java.util.List;
import java.util.Objects;

public class GenericParametersNode extends BaseNode {

    private final List<FieldTypeNode> fieldTypeNodes;

    public GenericParametersNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<FieldTypeNode> fieldTypeNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(fieldTypeNodes);
        this.fieldTypeNodes = fieldTypeNodes;
    }

    public List<FieldTypeNode> getFieldTypeNodes() {
        return fieldTypeNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//GenericParametersNode\n");
        fieldTypeNodes.forEach(gpwb -> stringBuilder.append(gpwb.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}