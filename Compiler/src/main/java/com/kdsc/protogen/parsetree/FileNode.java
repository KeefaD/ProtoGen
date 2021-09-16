package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;

public class FileNode extends BaseNode {

    private final List<ProtoGenTypeNode> protoGenTypeNodes;
    private final List<ProtoGenKeyNode> protoGenKeyNodes;
    private final List<ProtoGenEnumNode> protoGenEnumNodes;

    public FileNode(
        String sourceFileName,
        long line,
        long charPosition,
        List<ProtoGenTypeNode> protoGenTypeNodes,
        List<ProtoGenKeyNode> protoGenKeyNodes,
        List<ProtoGenEnumNode> protoGenEnumNodes
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
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//FileNode\n");
        if(null != protoGenTypeNodes) {
            protoGenTypeNodes.forEach(pgtn -> stringBuilder.append(pgtn.toFormattedString(1)));
        }
        if(null != protoGenKeyNodes) {
            protoGenKeyNodes.forEach(pgkn -> stringBuilder.append(pgkn.toFormattedString(1)));
        }
        if(null != protoGenEnumNodes) {
            protoGenEnumNodes.forEach(pgkn -> stringBuilder.append(pgkn.toFormattedString(1)));
        }
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}