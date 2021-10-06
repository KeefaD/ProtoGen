package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.nodes.BaseNode;
import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.utils.parameterchecking.Numbers;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

import static com.kdsc.protogen.parsetree.ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions;

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
        return toFormattedString(indentationLevel, defaultParseTreeFormattedStringOptions);
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var parseTreeFormattedStringOptions = (ParseTreeFormattedStringOptions)formattedStringOptions;
        if(!parseTreeFormattedStringOptions.hideBaseParseTreeNodeParameter()) {
            var stringBuilder = new StringBuilder();
            classToFormattedStringTitle(stringBuilder, formattedStringOptions, BaseParseTreeNode.class);
            fieldToFormattedStringField(stringBuilder, formattedStringOptions, "SourceFileName", sourceFileName);
            fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Line", line);
            fieldToFormattedStringField(stringBuilder, formattedStringOptions, "CharPosition", charPosition);
            return indentString(stringBuilder, formattedStringOptions, indentationLevel);
        }
        return "";
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