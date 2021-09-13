package com.kdsc.protogen.parsetree;

public class NamespaceNode extends BaseParseTreeNode {

    private final String namespace;

    public NamespaceNode(
        long line,
        long charPosition,
        String namespace
    ) {
        super(line, charPosition);
        this.namespace = namespace;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//NamespaceNode\n");
        stringBuilder.append(oneIndent() + "Namespace : " + namespace);
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}
