package com.kdsc.protogen.parsetree;

import java.util.List;

public class GenericParameterWithBoundsNode extends BaseNode {

    private final String identifier;
    private final List<NamespaceNameGenericParametersWithoutBoundsNode> namespaceNameGenericParametersWithoutBoundsNodes;

    public GenericParameterWithBoundsNode(
        String sourceFileName,
        long line,
        long charPosition,
        String identifier,
        List<NamespaceNameGenericParametersWithoutBoundsNode> namespaceNameGenericParametersWithoutBoundsNodes
    ) {
        super(sourceFileName, line, charPosition);
        this.identifier = identifier;
        this.namespaceNameGenericParametersWithoutBoundsNodes = namespaceNameGenericParametersWithoutBoundsNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//GenericParameterWithBoundsNode\n");
        stringBuilder.append(oneIndent() + "Identifier : " + identifier + "\n");
        if(null != namespaceNameGenericParametersWithoutBoundsNodes) {
            namespaceNameGenericParametersWithoutBoundsNodes.forEach(nngpwbn -> stringBuilder.append(nngpwbn.toFormattedString(1)));
        }
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}