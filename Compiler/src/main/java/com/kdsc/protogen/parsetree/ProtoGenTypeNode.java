package com.kdsc.protogen.parsetree;

import java.util.Optional;

public class ProtoGenTypeNode extends BaseParseTreeNode {

    private boolean isInterface;
    private NamespaceNameGenericParametersWithBoundsNode namespaceNameGenericParametersWithBoundsNode;
    private Optional<ImplementsListNode> implementsListNode;

    public ProtoGenTypeNode(
        long line,
        long charPosition,
        boolean isInterface,
        NamespaceNameGenericParametersWithBoundsNode namespaceNameGenericParametersWithBoundsNode,
        Optional<ImplementsListNode> implementsListNode
    ) {
        super(line, charPosition);
        this.isInterface = isInterface;
        this.namespaceNameGenericParametersWithBoundsNode = namespaceNameGenericParametersWithBoundsNode;
        this.implementsListNode = implementsListNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//ProtoGenTypeNode\n");
        stringBuilder.append(oneIndent() + "IsInterface : " + isInterface + "\n");
        stringBuilder.append(namespaceNameGenericParametersWithBoundsNode.toFormattedString(1) + "\n");
        implementsListNode.ifPresent(listNode -> stringBuilder.append(listNode.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}
