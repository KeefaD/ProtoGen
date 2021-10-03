package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;

//TODO:KMD Check consistency of all these nodes in terms of Antlr lists and optionals, it's only going to get harder to change later
public class FileNode extends BaseParseTreeNode {

    private final List<ProtoGenTypeNode> protoGenTypeNodes;
    private final List<ProtoGenKeyNode> protoGenKeyNodes;
    private final List<ProtoGenEnumNode> protoGenEnumNodes;

    public FileNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<ProtoGenTypeNode> protoGenTypeNodes,
        final List<ProtoGenKeyNode> protoGenKeyNodes,
        final List<ProtoGenEnumNode> protoGenEnumNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(protoGenTypeNodes);
        Objects.requireNonNull(protoGenKeyNodes);
        Objects.requireNonNull(protoGenEnumNodes);
        this.protoGenTypeNodes = protoGenTypeNodes;
        this.protoGenKeyNodes = protoGenKeyNodes;
        this.protoGenEnumNodes = protoGenEnumNodes;
    }

    public List<ProtoGenTypeNode> getProtoGenTypeNodes() {
        return protoGenTypeNodes;
    }

    public List<ProtoGenKeyNode> getProtoGenKeyNodes() {
        return protoGenKeyNodes;
    }

    public List<ProtoGenEnumNode> getProtoGenEnumNodes() {
        return protoGenEnumNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, FileNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, protoGenTypeNodes);
        fieldToFormattedStringField(stringBuilder, protoGenKeyNodes);
        fieldToFormattedStringField(stringBuilder, protoGenEnumNodes);
        return indentString(stringBuilder, indentationLevel);
    }

}