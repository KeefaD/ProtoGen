package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;

//TODO:KMD Check consistency of all these nodes in terms of Antlr lists and optionals, it's only going to get harder to change later
public class FileNode extends BaseNode {

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
        stringBuilder.append("//FileNode\n");
        protoGenTypeNodes.forEach(pgtn -> stringBuilder.append(pgtn.toFormattedString(1)));
        protoGenKeyNodes.forEach(pgkn -> stringBuilder.append(pgkn.toFormattedString(1)));
        protoGenEnumNodes.forEach(pgkn -> stringBuilder.append(pgkn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}