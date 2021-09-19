package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

public class NamespaceNode extends BaseNode {

    private final String namespace;

    public NamespaceNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final String namespace
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespace);
        Strings.requireNonBlank(namespace);
        this.namespace = namespace;
    }

    public String getNamespace() {
        return namespace;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//NamespaceNode\n");
        stringBuilder.append(oneIndent() + "Namespace : " + namespace);
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}
