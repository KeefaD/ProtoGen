package com.kdsc.protogen.parsetree;

import java.util.Objects;
import java.util.Optional;

public class EnumVersionNode extends BaseNode {

    private final VersionNumberNode versionNumberNode;
    private final Optional<EnumCasesNode> enumCasesNode;

    public EnumVersionNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final VersionNumberNode versionNumberNode,
        final Optional<EnumCasesNode> enumCasesNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(versionNumberNode);
        Objects.requireNonNull(enumCasesNode);
        this.versionNumberNode = versionNumberNode;
        this.enumCasesNode = enumCasesNode;
    }

    public VersionNumberNode getVersionNumberNode() {
        return versionNumberNode;
    }

    public Optional<EnumCasesNode> getEnumCasesNode() {
        return enumCasesNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {

        //TODO:KMD Perhaps you can extract this out and make it all nice, it is a bit repetitive
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//EnumVersionNode\n");
        stringBuilder.append(versionNumberNode.toFormattedString(1));
        enumCasesNode.ifPresent(ecn -> stringBuilder.append(ecn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}