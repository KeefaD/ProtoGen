package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.fieldtypenodes.FieldTypeNode;
import com.kdsc.protogen.parsetreenodes.utils.clone.Lists;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class GenericParameterWithBoundsNode extends BaseParseTreeNode {

    private final String identifier;
    private final List<FieldTypeNode> fieldTypeNodes;

    public GenericParameterWithBoundsNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final String identifier,
        final List<FieldTypeNode> fieldTypeNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(identifier);
        Strings.requireNonBlank(identifier);
        Objects.requireNonNull(fieldTypeNodes);
        this.identifier = identifier;
        this.fieldTypeNodes = Collections.unmodifiableList(fieldTypeNodes);
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<FieldTypeNode> getFieldTypeNodes() {
        return fieldTypeNodes;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, GenericParameterWithBoundsNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Identifier", identifier);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldTypeNodes);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public GenericParameterWithBoundsNode clone() {
        return new GenericParameterWithBoundsNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            identifier,
            Lists.clone(fieldTypeNodes)
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        GenericParameterWithBoundsNode that = (GenericParameterWithBoundsNode) object;
        return identifier.equals(that.identifier) && fieldTypeNodes.equals(that.fieldTypeNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), identifier, fieldTypeNodes);
    }

}