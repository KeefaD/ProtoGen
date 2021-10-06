package com.kdsc.protogen.filegenerationtree.proto;

import com.kdsc.protogen.nodes.FormattedStringOptions;

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
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, EnumFileNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "PackageName", packageName);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Name", enumName);
        fieldToFormattedStringField(formattedStringOptions, stringBuilder, enumCaseNodes);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}