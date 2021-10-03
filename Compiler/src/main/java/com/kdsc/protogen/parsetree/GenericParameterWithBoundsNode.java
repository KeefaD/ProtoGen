package com.kdsc.protogen.parsetree;

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

}