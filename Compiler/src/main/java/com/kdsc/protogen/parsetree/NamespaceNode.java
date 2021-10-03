package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

public class NamespaceNode extends BaseParseTreeNode {

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
        classToFormattedStringTitle(stringBuilder, NamespaceNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "Namespace", namespace);
        return indentString(stringBuilder, indentationLevel);
    }

}