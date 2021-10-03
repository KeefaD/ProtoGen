package com.kdsc.protogen.filegenerationtree.java;

import java.util.List;

public class EnumFileNode extends JavaFileNode {

    //TODO:KMD Should these be private or protected
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
        this.enumCaseNodes = enumCaseNodes;
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
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, EnumFileNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "", namespace);
        fieldToFormattedStringField(stringBuilder, "", namespace);
        fieldToFormattedStringField(stringBuilder, enumCaseNodes);
        return indentString(stringBuilder, indentationLevel);
    }

}