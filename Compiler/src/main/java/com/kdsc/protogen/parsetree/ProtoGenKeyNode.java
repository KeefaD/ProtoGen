package com.kdsc.protogen.parsetree;

import java.util.Optional;

public class ProtoGenKeyNode extends BaseNode {

    private boolean isInterface;
    private NamespaceNameGenericParametersWithBoundsNode namespaceNameGenericParametersWithBoundsNode;
    private Optional<ImplementsListNode> implementsListNode;
    private Optional<VersionsNode> versionsNode;
    private Optional<FieldsNode> fieldsNode;

    public ProtoGenKeyNode(
        String sourceFileName,
        long line,
        long charPosition,
        boolean isInterface,
        NamespaceNameGenericParametersWithBoundsNode namespaceNameGenericParametersWithBoundsNode,
        Optional<ImplementsListNode> implementsListNode,
        Optional<VersionsNode> versionsNode,
        Optional<FieldsNode> fieldsNode
    ) {
        super(sourceFileName, line, charPosition);
        this.isInterface = isInterface;
        this.namespaceNameGenericParametersWithBoundsNode = namespaceNameGenericParametersWithBoundsNode;
        this.implementsListNode = implementsListNode;
        this.versionsNode = versionsNode;
        this.fieldsNode = fieldsNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//ProtoGenKeyNode\n");
        stringBuilder.append(oneIndent() + "IsInterface : " + isInterface + "\n");
        stringBuilder.append(namespaceNameGenericParametersWithBoundsNode.toFormattedString(1) + "\n");
        implementsListNode.ifPresent(listNode -> stringBuilder.append(listNode.toFormattedString(1)));
        versionsNode.ifPresent(versionsNode -> stringBuilder.append(versionsNode.toFormattedString(1)));
        fieldsNode.ifPresent(fieldsNode -> stringBuilder.append(fieldsNode.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}
