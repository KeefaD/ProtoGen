package com.kdsc.protogen.filegenerationtree.java;

import com.kdsc.protogen.filegenerationtree.shared.FieldNode;

import java.util.List;

public class ClassFileNode extends JavaFileNode {

    private final String packageName;
    private final String name;

    public ClassFileNode(
        final String fileName,
        final String path,
        final String packageName,
        final String name,
        final List<FieldNode> fieldNodes
    ) {
        super(fileName, path);
        this.packageName = packageName;
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//ClassFileNode\n");
        stringBuilder.append(super.toFormattedString(1));
        stringBuilder.append(oneIndent() + "PackageName : " + packageName + "\n");
        stringBuilder.append(oneIndent() + "Name : " + name + "\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}