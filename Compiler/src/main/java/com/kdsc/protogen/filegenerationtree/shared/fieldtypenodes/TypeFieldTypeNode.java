package com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes;

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
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, TypeFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "Namespace", namespace);
        fieldToFormattedStringField(stringBuilder, "Name", name);
        return indentString(stringBuilder, indentationLevel);
    }

}