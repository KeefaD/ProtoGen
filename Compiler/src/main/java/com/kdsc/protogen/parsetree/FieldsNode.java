package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Optional;

public class FieldsNode extends BaseParseTreeNode {

    private final Optional<List<FieldNode>> fieldNodes;

    public FieldsNode(
        String sourceFileName,
        long line,
        long charPosition,
        Optional<List<FieldNode>> fieldNodes
    ) {
        super(sourceFileName, line, charPosition);
        this.fieldNodes = fieldNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//FieldsNode\n");
        if(null != fieldNodes && fieldNodes.isPresent()) {
            fieldNodes.get().forEach(fn -> stringBuilder.append(fn.toFormattedString(1)));
        }
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}