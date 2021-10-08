package com.kdsc.protogen.filegenerationtree.proto;

import com.kdsc.protogen.filegenerationtree.shared.FieldNode;
import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class MessageFileNode extends ProtoFileNode {

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
        Objects.requireNonNull(importStatements);
        Objects.requireNonNull(fieldNodes);
        this.packageName = packageName;
        this.name = name;
        this.importStatements = Collections.unmodifiableSet(importStatements);
        this.fieldNodes = Collections.unmodifiableList(fieldNodes);
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
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, MessageFileNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "PackageName", packageName);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Name", name);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldNodes);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}