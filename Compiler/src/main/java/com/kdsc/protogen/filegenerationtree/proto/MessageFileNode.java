package com.kdsc.protogen.filegenerationtree.proto;

import com.kdsc.protogen.filegenerationtree.shared.FieldNode;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.List;

public class MessageFileNode extends ProtoFileNode {

    private final String name;
    private final List<FieldNode> fieldNodes;

    public MessageFileNode(
        final String fileName,
        final String path,
        final String name,
        final List<FieldNode> fieldNodes
    ) {
        super(fileName, path);
        Strings.requireNonBlank(name);
        this.name = name;
        this.fieldNodes = fieldNodes;
    }

    public String getName() {
        return name;
    }

    public List<FieldNode> getFieldNodes() {
        return fieldNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, MessageFileNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "Name", name);
        fieldToFormattedStringField(stringBuilder, fieldNodes);
        return indentString(stringBuilder, indentationLevel);
    }

}