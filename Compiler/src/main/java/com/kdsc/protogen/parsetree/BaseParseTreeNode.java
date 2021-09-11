package com.kdsc.protogen.parsetree;

public abstract class BaseParseTreeNode {

    protected int indentationSpaceCount = 4;

    public abstract String toFormattedString(int indentationLevel);

    @Override
    public String toString() {
        return toFormattedString(0);
    }
}
