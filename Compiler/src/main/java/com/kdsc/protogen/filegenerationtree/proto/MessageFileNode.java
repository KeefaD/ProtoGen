package com.kdsc.protogen.filegenerationtree.proto;

import com.kdsc.protogen.filegenerationtree.shared.FieldNode;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.List;
import java.util.Set;

public class MessageFileNode extends ProtoFileNode {

    private final String packageName;
    private final String name;
    private final Set<String> importStatements;
    private final List<FieldNode> fieldNodes;

    public MessageFileNode(
        final String fileName,
        final String path,
        final String packageName,
        final String name,
        final Set<String> importStatements,
        final List<FieldNode> fieldNodes
    ) {
        super(fileName, path);
        Strings.requireNonBlank(name);
        Strings.requireNonBlank(packageName);
        this.packageName = packageName;
        this.name = name;
        this.importStatements = importStatements;
        this.fieldNodes = fieldNodes;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getName() {
        return name;
    }

    public Set<String> getImportStatements() {
        return importStatements;
    }

    public List<FieldNode> getFieldNodes() {
        return fieldNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, MessageFileNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "PackageName", packageName);
        fieldToFormattedStringField(stringBuilder, "Name", name);
        fieldToFormattedStringField(stringBuilder, fieldNodes);
        return indentString(stringBuilder, indentationLevel);
    }

}