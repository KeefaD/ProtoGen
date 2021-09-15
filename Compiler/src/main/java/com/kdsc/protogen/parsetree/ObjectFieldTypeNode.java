package com.kdsc.protogen.parsetree;

public class ObjectFieldTypeNode extends NonArrayFieldTypeNode {

    private final NamespaceNameGenericParametersWithoutBoundsNode namespaceNameGenericParametersWithoutBoundsNode;

    public ObjectFieldTypeNode(
        String sourceFileName,
        long line,
        long charPosition,
        NamespaceNameGenericParametersWithoutBoundsNode namespaceNameGenericParametersWithoutBoundsNode
    ) {
        super(sourceFileName, line, charPosition);
        this.namespaceNameGenericParametersWithoutBoundsNode = namespaceNameGenericParametersWithoutBoundsNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//ObjectFieldTypeNode\n");
        stringBuilder.append(namespaceNameGenericParametersWithoutBoundsNode.toFormattedString(1));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}