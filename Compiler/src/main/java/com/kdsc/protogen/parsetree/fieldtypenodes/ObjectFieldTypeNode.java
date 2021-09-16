package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.NamespaceNameGenericParametersWithoutBoundsNode;

import java.util.Objects;

public class ObjectFieldTypeNode extends NonArrayFieldTypeNode {

    private final NamespaceNameGenericParametersWithoutBoundsNode namespaceNameGenericParametersWithoutBoundsNode;

    public ObjectFieldTypeNode(
        String sourceFileName,
        long line,
        long charPosition,
        NamespaceNameGenericParametersWithoutBoundsNode namespaceNameGenericParametersWithoutBoundsNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameGenericParametersWithoutBoundsNode);
        this.namespaceNameGenericParametersWithoutBoundsNode = namespaceNameGenericParametersWithoutBoundsNode;
    }

    public NamespaceNameGenericParametersWithoutBoundsNode getNamespaceNameGenericParametersWithoutBoundsNode() {
        return namespaceNameGenericParametersWithoutBoundsNode;
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