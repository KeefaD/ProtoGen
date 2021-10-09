package com.kdsc.protogen.nodes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseNode {

    protected static final int INDENTATION_SPACE_COUNT = 4;

    protected String oneIndent() {
        return IntStream.range(0, INDENTATION_SPACE_COUNT).mapToObj(counter -> " ").collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return toFormattedString(0);
    }

    public abstract String toFormattedString(final int indentationLevel);

    public abstract String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel);

    public void classToFormattedStringTitle(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final Class clazz) {
        stringBuilder.append("//" + clazz.getSimpleName() + "\n");
    }

    public void superToFormattedStringSuper(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final String superFormattedToString) {
        var outputString = "//Super -> " + superFormattedToString;
        stringBuilder.append(outputString.indent(INDENTATION_SPACE_COUNT));
    }

    public void fieldToFormattedStringField(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final String fieldName, final String fieldValue) {
        stringBuilder.append(oneIndent() + fieldName + " : " + fieldValue + "\n");
    }

    public void fieldToFormattedStringField(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final String fieldName, final int fieldValue) {
        stringBuilder.append(oneIndent() + fieldName + " : " + fieldValue + "\n");
    }

    public void fieldToFormattedStringField(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final String fieldName, final long fieldValue) {
        stringBuilder.append(oneIndent() + fieldName + " : " + fieldValue + "\n");
    }

    public void fieldToFormattedStringField(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final String fieldName, final boolean fieldValue) {
        stringBuilder.append(oneIndent() + fieldName + " : " + fieldValue + "\n");
    }

    public void fieldToFormattedStringField(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final String fieldName, final BaseNode baseNode) {
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldName, baseNode, 1);
    }

    public void fieldToFormattedStringField(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final String fieldName, final BaseNode baseNode, final int indentationLevel) {
        var outputString = (fieldName + "\n").indent(indentationLevel * INDENTATION_SPACE_COUNT);
        stringBuilder.append(outputString);
        stringBuilder.append(baseNode.toFormattedString(formattedStringOptions, indentationLevel + 1));
    }

    public void fieldToFormattedStringField(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final BaseNode baseNode) {
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, baseNode, 1);
    }

    public void fieldToFormattedStringField(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final BaseNode baseNode, final int indentationLevel) {
        stringBuilder.append(baseNode.toFormattedString(formattedStringOptions, indentationLevel));
    }

    public <T extends BaseNode> void  fieldToFormattedStringField(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final Optional<T> baseNode) {
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, baseNode, 1);
    }

    public <T extends BaseNode> void  fieldToFormattedStringField(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final Optional<T> baseNode, int indentationLevel) {
        baseNode.ifPresent(bn -> stringBuilder.append(bn.toFormattedString(formattedStringOptions, indentationLevel)));
    }

    public <T extends BaseNode> void fieldToFormattedStringField(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final List<T> baseNodes) {
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, baseNodes, 1);
    }

    public <T extends BaseNode> void fieldToFormattedStringField(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final List<T> baseNodes, final int indentationLevel) {
        baseNodes.forEach(bn -> stringBuilder.append(bn.toFormattedString(formattedStringOptions, indentationLevel)));
    }

    //TODO:KMD This seems a bit inconsistent
    public String indentString(final StringBuilder stringBuilder, final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        return stringBuilder.toString().indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}