package com.kdsc.protogen.filegenerationtree.java;

import com.kdsc.protogen.filegenerationtree.shared.FieldNode;

import java.util.List;
import java.util.Set;

public class TypeFileNode extends JavaFileNode {

    private final String packageName;
    private final String name;
    private final Set<String> importStatements;

    //TODO:KMD I think we should consider equals and hash code so we can make this a set
    private final List<ImplementsNode> implementsNodes;
    private final List<FieldNode> fieldNodes;

    public TypeFileNode(
        final String fileName,
        final String path,
        final String packageName,
        final String name,
        final Set<String> importStatements,
        final List<ImplementsNode> implementsNodes,
        final List<FieldNode> fieldNodes
    ) {
        super(fileName, path);
        this.packageName = packageName;
        this.name = name;
        this.importStatements = importStatements;
        this.implementsNodes = implementsNodes;
        this.fieldNodes = fieldNodes;
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
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, TypeFileNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "PackageName", packageName);
        fieldToFormattedStringField(stringBuilder, "Name", name);
        //TODO:KMD Fix this
        stringBuilder.append(oneIndent() + "ImportStatements\n");
//        fieldToFormattedStringField(stringBuilder, importStatements);
        importStatements.forEach(is -> stringBuilder.append(is.indent(INDENTATION_SPACE_COUNT)));
        fieldToFormattedStringField(stringBuilder, implementsNodes);
        fieldToFormattedStringField(stringBuilder, fieldNodes);
        return indentString(stringBuilder, indentationLevel);
    }

}