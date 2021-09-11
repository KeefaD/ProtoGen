package com.kdsc.protogen.parsetree;

public class ProtoGenTypeNode extends BaseParseTreeNode {

    @Override
    public String toFormattedString(int indentationLevel) {
        var outputString = "//ProtoGenTypeNode";
        return outputString.indent(indentationLevel * indentationSpaceCount);
    }
}
