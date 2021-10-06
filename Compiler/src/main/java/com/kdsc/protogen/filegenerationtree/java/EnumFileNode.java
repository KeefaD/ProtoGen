package com.kdsc.protogen.filegenerationtree.java;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.utils.parameterchecking.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class EnumFileNode extends JavaFileNode {

    private final String namespace;
    private final String name;
    private final List<EnumCaseNode> enumCaseNodes;

    public EnumFileNode(
        final String fileName,
        final String path,
        final String namespace,
        final String name,
        final List<EnumCaseNode> enumCaseNodes
    ) {
        super(fileName, path);
        this.namespace = namespace;
        this.name = name;
        Objects.requireNonNull(enumCaseNodes);
        Lists.requireAtLeastOne(enumCaseNodes);
        this.enumCaseNodes = Collections.unmodifiableList(enumCaseNodes);
    }

    public String getNamespace() {
        return namespace;
    }

    public String getName() {
        return name;
    }

    public List<EnumCaseNode> getEnumCaseNodes() {
        return enumCaseNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, EnumFileNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Namespace", namespace);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Name", namespace);
        fieldToFormattedStringField(formattedStringOptions, stringBuilder, enumCaseNodes);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}