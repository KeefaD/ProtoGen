package com.kdsc.protogen.filegenerationtree.java;

import com.kdsc.protogen.filegenerationtree.shared.FieldNode;
import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class TypeInterfaceFileNode extends JavaFileNode {

    private final String packageName;
    private final String name;
    private final Set<String> importStatements;
    private final List<ImplementsNode> implementsNodes;
    private final List<FieldNode> fieldNodes;

    public TypeInterfaceFileNode(
        final String fileName,
        final String path,
        final String packageName,
        final String name,
        final Set<String> importStatements,
        final List<ImplementsNode> implementsNodes,
        final List<FieldNode> fieldNodes
    ) {
        super(fileName, path);
        Strings.requireNonBlank(packageName);
        Strings.requireNonBlank(name);
        Objects.requireNonNull(importStatements);
        Objects.requireNonNull(implementsNodes);
        Objects.requireNonNull(fieldNodes);
        this.packageName = packageName;
        this.name = name;
        this.importStatements = Collections.unmodifiableSet(importStatements);
        this.implementsNodes = Collections.unmodifiableList(implementsNodes);
        this.fieldNodes = Collections.unmodifiableList(fieldNodes);
    }

    public String getPackageName() {
        return packageName;
    }

    public String getName() {
        return name;
    }

    //TODO:KMD I think import statements should have a proper node
    public Set<String> getImportStatements() {
        return importStatements;
    }

    public List<ImplementsNode> getImplementsNodes() {
        return implementsNodes;
    }

    public List<FieldNode> getFieldNodes() {
        return fieldNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, TypeInterfaceFileNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "PackageName", packageName);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Name", name);
        //TODO:KMD Fix this
        stringBuilder.append(oneIndent() + "ImportStatements\n");
//        fieldToFormattedStringField(stringBuilder, importStatements);
        importStatements.forEach(is -> stringBuilder.append(is.indent(INDENTATION_SPACE_COUNT)));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, implementsNodes);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldNodes);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}