package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.utils.clone.Lists;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class GenericParameterWithBoundsNode extends BaseParseTreeNode {

    private final String identifier;
    private final List<NamespaceNameGenericParametersNode> namespaceNameGenericParametersNodes;

    public GenericParameterWithBoundsNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final String identifier,
        final List<NamespaceNameGenericParametersNode> namespaceNameGenericParametersNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(identifier);
        Strings.requireNonBlank(identifier);
        Objects.requireNonNull(namespaceNameGenericParametersNodes);
        this.identifier = identifier;
        this.namespaceNameGenericParametersNodes = Collections.unmodifiableList(namespaceNameGenericParametersNodes);
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<NamespaceNameGenericParametersNode> getNamespaceNameGenericParametersNodes() {
        return namespaceNameGenericParametersNodes;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, GenericParameterWithBoundsNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Identifier", identifier);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, namespaceNameGenericParametersNodes);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public GenericParameterWithBoundsNode clone() {
        return new GenericParameterWithBoundsNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            identifier,
            Lists.clone(namespaceNameGenericParametersNodes)
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        GenericParameterWithBoundsNode that = (GenericParameterWithBoundsNode) object;
        return identifier.equals(that.identifier) && namespaceNameGenericParametersNodes.equals(that.namespaceNameGenericParametersNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), identifier, namespaceNameGenericParametersNodes);
    }

}