package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class VersionsNode extends BaseNode {

    private final List<VersionNode> versionNodes;

    public VersionsNode(
        String sourceFileName,
        long line,
        long charPosition,
        List<VersionNode> versionNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(versionNodes);
        this.versionNodes = versionNodes;
    }

    public List<VersionNode> getVersionNodes() {
        return versionNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//VersionsNode\n");
        versionNodes.forEach(vn -> stringBuilder.append(vn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}