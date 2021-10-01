package com.kdsc.protogen.filegenerationtree.proto;

import java.util.List;

public class EnumFileNode extends ProtoFileNode {

    private final List<EnumCaseNode> enumCaseNodes;
    private final String enumName;

    public EnumFileNode(
        final String fileName,
        final String path,
        final String enumName,
        List<EnumCaseNode> enumCaseNodes
    ) {
        super(fileName, path);
        //TODO:KMD Pre-conditions
        this.enumName = enumName;
        this.enumCaseNodes = enumCaseNodes;
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
        stringBuilder.append("//EnumFileNode\n");
        //TODO:KMD Should probably print super in the output
        stringBuilder.append(super.toFormattedString(1));
        //TODO:KMD These \n's should be everywhere, in fact we should factor out this to a util method or put it in the base class
        stringBuilder.append(oneIndent() + "Name : " + enumName + "\n");
        enumCaseNodes.forEach(ecn -> stringBuilder.append(ecn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}