package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.fieldtypenodes.FieldTypeNode;
import com.kdsc.protogen.parsetree.utils.clone.Lists;

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
        this.fieldTypeNodes = fieldTypeNodes;
    }

    public List<FieldTypeNode> getFieldTypeNodes() {
        return fieldTypeNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, GenericParametersNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, fieldTypeNodes);
        return indentString(stringBuilder, indentationLevel);
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
    public boolean equals(Object object) {
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