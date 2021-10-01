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
        stringBuilder.append("//EnumFileNode\n");
        stringBuilder.append(super.toFormattedString(1));
        stringBuilder.append(oneIndent() + "Namespace : " + namespace + "\n");
        stringBuilder.append(oneIndent() + "Name : " + name + "\n");
        enumCaseNodes.forEach(ecn -> stringBuilder.append(ecn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}