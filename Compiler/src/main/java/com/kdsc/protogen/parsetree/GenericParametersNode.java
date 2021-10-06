package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetree.fieldtypenodes.FieldTypeNode;
import com.kdsc.protogen.parsetree.utils.clone.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GenericParametersNode extends BaseParseTreeNode {

    private final List<FieldTypeNode> fieldTypeNodes;

    public GenericParametersNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<FieldTypeNode> fieldTypeNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(fieldTypeNodes);
        this.fieldTypeNodes = Collections.unmodifiableList(fieldTypeNodes);
    }

    public List<FieldTypeNode> getFieldTypeNodes() {
        return fieldTypeNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, GenericParametersNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions), BaseParseTreeNode.class);
        fieldToFormattedStringField(formattedStringOptions, stringBuilder, fieldTypeNodes);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public GenericParametersNode clone() {
        return new GenericParametersNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            Lists.clone(fieldTypeNodes)
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        GenericParametersNode that = (GenericParametersNode) object;
        return fieldTypeNodes.equals(that.fieldTypeNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fieldTypeNodes);
    }

}