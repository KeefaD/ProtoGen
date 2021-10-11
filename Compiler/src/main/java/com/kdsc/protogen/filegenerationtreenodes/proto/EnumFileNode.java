package com.kdsc.protogen.filegenerationtreenodes.proto;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.utils.parameterchecking.Lists;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Collections;
import java.util.List;

public final class EnumFileNode extends ProtoFileNode {

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
        Lists.requireAtLeastOne(enumCaseNodes);
        Strings.requireNonBlank(packageName);
        Strings.requireNonBlank(enumName);
        this.packageName = packageName;
        this.enumName = enumName;
        this.enumCaseNodes = Collections.unmodifiableList(enumCaseNodes);
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
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, EnumFileNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "PackageName", packageName);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Name", enumName);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, enumCaseNodes);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}