package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.utils.clone.Lists;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.List;
import java.util.Objects;

public class GenericParameterWithBoundsNode extends BaseParseTreeNode {

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
        this.namespaceNameGenericParametersNodes = namespaceNameGenericParametersNodes;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<NamespaceNameGenericParametersNode> getNamespaceNameGenericParametersNodes() {
        return namespaceNameGenericParametersNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, GenericParameterWithBoundsNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "Identifier", identifier);
        fieldToFormattedStringField(stringBuilder, namespaceNameGenericParametersNodes);
        return indentString(stringBuilder, indentationLevel);
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
    public boolean equals(Object object) {
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