package com.kdsc.protogen.filegenerationtree.proto;

import java.util.List;

public class EnumFileNode extends ProtoFileNode {

    private final List<EnumCaseNode> enumCaseNodes;
    private final String packageName;
    private final String enumName;

    public EnumFileNode(
        final String fileName,
        final String path,
        final String packageName,
        final String enumName,
        List<EnumCaseNode> enumCaseNodes
    ) {
        super(fileName, path);
        //TODO:KMD Pre-conditions
        this.packageName = packageName;
        this.enumName = enumName;
        this.enumCaseNodes = enumCaseNodes;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getEnumName() {
        return enumName;
    }

    public List<EnumCaseNode> getEnumCaseNodes() {
        return enumCaseNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, EnumFileNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "PackageName", packageName);
        fieldToFormattedStringField(stringBuilder, "Name", enumName);
        fieldToFormattedStringField(stringBuilder, enumCaseNodes);
        return indentString(stringBuilder, indentationLevel);
    }

}