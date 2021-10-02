package com.kdsc.protogen.filegenerationtree.java;

import com.kdsc.protogen.filegenerationtree.shared.FieldNode;

import java.util.List;
import java.util.Set;

public class ClassFileNode extends JavaFileNode {

    private final String packageName;
    private final String name;
    private final Set<String> importStatements;
    private final List<FieldNode> fieldNodes;

    public ClassFileNode(
        final String fileName,
        final String path,
        final String packageName,
        final String name,
        final Set<String> importStatements,
        final List<FieldNode> fieldNodes
    ) {
        super(fileName, path);
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

    //TODO:KMD I think import statements should have a proper node
    public Set<String> getImportStatements() {
        return importStatements;
    }

    public List<FieldNode> getFieldNodes() {
        return fieldNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//ClassFileNode\n");
        stringBuilder.append(super.toFormattedString(1));
        stringBuilder.append(oneIndent() + "PackageName : " + packageName + "\n");
        stringBuilder.append(oneIndent() + "Name : " + name + "\n");
        stringBuilder.append(oneIndent() + "ImportStatements\n");
        importStatements.forEach(is -> stringBuilder.append(is.indent(INDENTATION_SPACE_COUNT)));
        fieldNodes.forEach(fn -> stringBuilder.append(fn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}