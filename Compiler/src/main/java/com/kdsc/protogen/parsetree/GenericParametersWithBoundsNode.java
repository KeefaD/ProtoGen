package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.utils.clone.Lists;

import java.util.List;
import java.util.Objects;

public class GenericParametersWithBoundsNode extends BaseParseTreeNode {

    private final List<GenericParameterWithBoundsNode> genericParameterWithBoundsNodes;

    public GenericParametersWithBoundsNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<GenericParameterWithBoundsNode> genericParameterWithBoundsNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(genericParameterWithBoundsNodes);
        this.genericParameterWithBoundsNodes = genericParameterWithBoundsNodes;
    }

    public List<GenericParameterWithBoundsNode> getGenericParameterWithBoundsNodes() {
        return genericParameterWithBoundsNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, GenericParametersWithBoundsNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, genericParameterWithBoundsNodes);
        return indentString(stringBuilder, indentationLevel);
    }

    @Override
    public GenericParametersWithBoundsNode clone() {
        return new GenericParametersWithBoundsNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            Lists.clone(genericParameterWithBoundsNodes)
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        GenericParametersWithBoundsNode that = (GenericParametersWithBoundsNode) object;
        return genericParameterWithBoundsNodes.equals(that.genericParameterWithBoundsNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), genericParameterWithBoundsNodes);
    }

}