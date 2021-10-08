package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetree.utils.clone.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

//TODO:KMD Check consistency of all these nodes in terms of Antlr lists and optionals, it's only going to get harder to change later
public final class FileNode extends BaseParseTreeNode {

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
        this.protoGenTypeNodes = Collections.unmodifiableList(protoGenTypeNodes);
        this.protoGenKeyNodes = Collections.unmodifiableList(protoGenKeyNodes);
        this.protoGenEnumNodes = Collections.unmodifiableList(protoGenEnumNodes);
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
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, FileNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, protoGenTypeNodes);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, protoGenKeyNodes);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, protoGenEnumNodes);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public FileNode clone() {
        return new FileNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            Lists.clone(protoGenTypeNodes),
            Lists.clone(protoGenKeyNodes),
            Lists.clone(protoGenEnumNodes)
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        FileNode fileNode = (FileNode) object;
        return protoGenTypeNodes.equals(fileNode.protoGenTypeNodes) && protoGenKeyNodes.equals(fileNode.protoGenKeyNodes) && protoGenEnumNodes.equals(fileNode.protoGenEnumNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), protoGenTypeNodes, protoGenKeyNodes, protoGenEnumNodes);
    }

}