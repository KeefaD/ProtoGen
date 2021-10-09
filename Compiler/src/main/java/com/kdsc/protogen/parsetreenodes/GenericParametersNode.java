package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.fieldtypenodes.FieldTypeNode;
import com.kdsc.protogen.parsetreenodes.utils.clone.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class GenericParametersNode extends BaseParseTreeNode {

    private final List<FieldTypeNode> fieldTypeNodes;

    public GenericParametersNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<FieldTypeNode> fieldTypeNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(fieldTypeNodes);
        com.kdsc.protogen.utils.parameterchecking.Lists.requireAtLeastOne(fieldTypeNodes);
        this.fieldTypeNodes = Collections.unmodifiableList(fieldTypeNodes);
    }

    public List<FieldTypeNode> getFieldTypeNodes() {
        return fieldTypeNodes;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, GenericParametersNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldTypeNodes);
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