package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

public final class NameNode extends BaseParseTreeNode {

    private final String name;

    public NameNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final String name
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(name);
        Strings.requireNonBlank(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, NameNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Name", name);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public NameNode clone() {
        return new NameNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            name
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        NameNode nameNode = (NameNode) object;
        return name.equals(nameNode.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

}