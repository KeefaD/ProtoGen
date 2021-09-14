package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Optional;

public class VersionsNode extends BaseParseTreeNode {

    private final Optional<List<VersionNode>> versionNodes;

    public VersionsNode(
        String sourceFileName,
        long line,
        long charPosition,
        Optional<List<VersionNode>> versionNodes
    ) {
        super(sourceFileName, line, charPosition);
        this.versionNodes = versionNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//VersionsNode\n");
        if(null != versionNodes && versionNodes.isPresent()) {
            versionNodes.get().forEach(vn -> stringBuilder.append(vn.toFormattedString(1)));
        }
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}