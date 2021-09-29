package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.commoninterfaces.HasNamespaceName;
import com.kdsc.protogen.parsetree.commoninterfaces.TopLevelObject;
import com.kdsc.protogen.utils.parameterchecking.Optionals;

import java.util.Objects;
import java.util.Optional;

public class ProtoGenEnumNode extends BaseNode implements TopLevelObject, HasNamespaceName {

    private NamespaceNameNode namespaceNameNode;
    private Optional<EnumVersionsNode> enumVersionsNode;
    private Optional<EnumCasesNode> enumCasesNode;

    public ProtoGenEnumNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final NamespaceNameNode namespaceNameNode,
        final Optional<EnumVersionsNode> enumVersionsNode,
        final Optional<EnumCasesNode> enumCasesNode
    ) {
        //TODO:KMD Also need to make all lists immutable
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameNode);
        Objects.requireNonNull(enumVersionsNode);
        Objects.requireNonNull(enumCasesNode);
        Optionals.requireAtMostOne(enumVersionsNode, enumCasesNode);
        this.namespaceNameNode = namespaceNameNode;
        this.enumVersionsNode = enumVersionsNode;
        this.enumCasesNode = enumCasesNode;
    }

    public NamespaceNameNode getNamespaceNameNode() {
        return namespaceNameNode;
    }

    public Optional<EnumVersionsNode> getEnumVersionsNode() {
        return enumVersionsNode;
    }

    public Optional<EnumCasesNode> getEnumCasesNode() {
        return enumCasesNode;
    }

    @Override
    public boolean isLibraryNode() {
        return false;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//ProtoGenEnumNode\n");
        stringBuilder.append(namespaceNameNode.toFormattedString(1));
        enumVersionsNode.ifPresent(evn -> stringBuilder.append(evn.toFormattedString(1)));
        enumCasesNode.ifPresent(ecn -> stringBuilder.append(ecn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}