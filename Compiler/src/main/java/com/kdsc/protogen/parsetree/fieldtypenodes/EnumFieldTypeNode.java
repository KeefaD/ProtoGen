package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.NamespaceNameGenericParametersWithoutBoundsNode;

import java.util.Objects;

public class EnumFieldTypeNode extends NonArrayFieldTypeNode {

    private final NamespaceNameGenericParametersWithoutBoundsNode namespaceNameGenericParametersWithoutBoundsNode;

    public EnumFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final NamespaceNameGenericParametersWithoutBoundsNode namespaceNameGenericParametersWithoutBoundsNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameGenericParametersWithoutBoundsNode);
        this.namespaceNameGenericParametersWithoutBoundsNode = namespaceNameGenericParametersWithoutBoundsNode;
    }

    public NamespaceNameGenericParametersWithoutBoundsNode getNamespaceNameGenericParametersWithoutBoundsNode() {
        return namespaceNameGenericParametersWithoutBoundsNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//EnumFieldTypeNode\n");
        stringBuilder.append(namespaceNameGenericParametersWithoutBoundsNode.toFormattedString(1));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}