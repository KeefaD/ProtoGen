package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

public final class NamespaceNode extends BaseParseTreeNode {

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
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, NamespaceNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Namespace", namespace);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public NamespaceNode clone() {
        return new NamespaceNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            namespace
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        NamespaceNode that = (NamespaceNode) object;
        return namespace.equals(that.namespace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), namespace);
    }

}