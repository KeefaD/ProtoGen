package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.nodes.FormattedStringOptions;
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
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, NamespaceNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Namespace", namespace);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
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