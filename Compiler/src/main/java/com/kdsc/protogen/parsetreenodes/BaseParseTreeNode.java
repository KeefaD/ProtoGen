package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.BaseNode;
import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.utils.parameterchecking.Numbers;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

import static com.kdsc.protogen.parsetreenodes.ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions;

public abstract class BaseParseTreeNode extends BaseNode implements Cloneable {

    private final String sourceFileName;
    private final long line;
    private final long charPosition;

    public BaseParseTreeNode(final String sourceFileName, final long line, final long charPosition) {
        Objects.requireNonNull(sourceFileName);
        Strings.requireNonBlank(sourceFileName);
        Numbers.requireZeroOrGreater(line);
        Numbers.requireZeroOrGreater(charPosition);
        this.sourceFileName = sourceFileName;
        this.line = line;
        this.charPosition = charPosition;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public long getLine() {
        return line;
    }

    public long getCharPosition() {
        return charPosition;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        return toFormattedString(defaultParseTreeFormattedStringOptions, indentationLevel);
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "SourceFileName", sourceFileName);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Line", line);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "CharPosition", charPosition);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    public void superToFormattedStringSuper(final StringBuilder stringBuilder, FormattedStringOptions formattedStringOptions, final String superFormattedToString, final Class baseClass) {

        var parseTreeFormattedStringOptions = (ParseTreeFormattedStringOptions)formattedStringOptions;
        if(parseTreeFormattedStringOptions.hideBaseParseTreeNodeParameter() && baseClass.getName().equals(BaseParseTreeNode.class.getName())) return;

        var outputString = "//Super -> " + superFormattedToString;
        stringBuilder.append(outputString.indent(INDENTATION_SPACE_COUNT));
    }

    @Override
    public void superToFormattedStringSuper(final StringBuilder stringBuilder, FormattedStringOptions formattedStringOptions, final String superFormattedToString) {
        throw new UnsupportedOperationException("superToFormattedStringSuper(StringBuilder, FormattedStringOptions, String) is unsupported for ParseTreeNodes");
    }

    public abstract Object clone();

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BaseParseTreeNode that = (BaseParseTreeNode) object;
        return line == that.line && charPosition == that.charPosition && sourceFileName.equals(that.sourceFileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceFileName, line, charPosition);
    }

}