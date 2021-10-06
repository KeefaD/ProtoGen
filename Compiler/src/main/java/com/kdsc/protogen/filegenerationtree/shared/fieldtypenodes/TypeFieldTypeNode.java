package com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public class TypeFieldTypeNode extends FieldTypeNode {

    private final String namespace;
    private final String name;

    public TypeFieldTypeNode(
        final boolean isOptional,
        final String namespace,
        final String name
    ) {
        super(isOptional);
        this.namespace = namespace;
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getName() {
        return name;
    }

    public String getFullyQualifiedName() {
        return namespace + "." + name;
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, TypeFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Namespace", namespace);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Name", name);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}