package com.kdsc.protogen.parsetree;

public class GenericParameterWithoutBoundsNode extends BaseParseTreeNode {

    private final String identifier;

    public GenericParameterWithoutBoundsNode(
        String identifier
    ) {
        this.identifier = identifier;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//GenericParameterWithoutBoundsNode\n");
        stringBuilder.append(oneIndent() + "Identifier : " + identifier + "\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}