package com.kdsc.protogen.parsetree;

import java.util.List;

public class FileNode extends BaseParseTreeNode {

    private List<ProtoGenTypeNode> protoGenTypeNodes;

    public FileNode(List<ProtoGenTypeNode> protoGenTypeNodes) {
        this.protoGenTypeNodes = protoGenTypeNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuffer = new StringBuffer();
        stringBuffer.append("//FileNode\n");
        protoGenTypeNodes.forEach(pgtn -> stringBuffer.append(pgtn.toFormattedString(indentationLevel + 1)));
        var outputString = stringBuffer.toString();
        return outputString.indent(indentationLevel * indentationSpaceCount);
    }
}
