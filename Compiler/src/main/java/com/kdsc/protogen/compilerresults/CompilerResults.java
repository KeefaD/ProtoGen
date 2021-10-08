package com.kdsc.protogen.compilerresults;

import com.kdsc.protogen.parsetreenodes.FileNode;
import com.kdsc.protogen.parsetreenodes.EnumNode;
import com.kdsc.protogen.parsetreenodes.KeyNode;
import com.kdsc.protogen.parsetreenodes.TypeNode;
import com.kdsc.protogen.parsetreenodes.utils.ParseTreeUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class CompilerResults {

    private final List<FileNode> fileNodes;
    private final Map<String, TypeNode> allTypeNodeMap;
    private final Map<String, TypeNode> typeNodeMap;
    private final Map<String, TypeNode> typeInterfaceNodeMap;
    private final Map<String, KeyNode> allKeyNodeMap;
    private final Map<String, KeyNode> keyNodeMap;
    private final Map<String, KeyNode> keyInterfaceNodeMap;
    private final Map<String, EnumNode> enumNodeMap;

    public CompilerResults(final List<FileNode> fileNodes) {
        Objects.requireNonNull(fileNodes);
        this.fileNodes = Collections.unmodifiableList(fileNodes);

        allTypeNodeMap = Collections.unmodifiableMap(
            fileNodes
                .stream()
                .flatMap(fn -> fn.getTypeNodes().stream())
                .collect(Collectors.toMap(tn -> ParseTreeUtils.getNamespaceNameString(tn.getNamespaceNameNode()), tn -> tn, (tn1, tn2) -> tn2))
        );

        typeNodeMap = Collections.unmodifiableMap(
            fileNodes
                .stream()
                .flatMap(fn -> fn.getTypeNodes().stream())
                .filter(tn -> !tn.isInterface())
                .collect(Collectors.toMap(tn -> ParseTreeUtils.getNamespaceNameString(tn.getNamespaceNameNode()), tn -> tn, (tn1, tn2) -> tn2))
        );

        typeInterfaceNodeMap = Collections.unmodifiableMap(
            fileNodes
                .stream()
                .flatMap(fn -> fn.getTypeNodes().stream())
                .filter(TypeNode::isInterface)
                .collect(Collectors.toMap(tni -> ParseTreeUtils.getNamespaceNameString(tni.getNamespaceNameNode()), tni -> tni, (tni1, tni2) -> tni2))
        );

        allKeyNodeMap = Collections.unmodifiableMap(
            fileNodes
                .stream()
                .flatMap(fn -> fn.getKeyNodes().stream())
                .collect(Collectors.toMap(kn -> ParseTreeUtils.getNamespaceNameString(kn.getNamespaceNameNode()), kn -> kn, (kn1, kn2) -> kn2))
        );

        keyNodeMap = Collections.unmodifiableMap(
            fileNodes
                .stream()
                .flatMap(fn -> fn.getKeyNodes().stream())
                .filter(kn -> !kn.isInterface())
                .collect(Collectors.toMap(kn -> ParseTreeUtils.getNamespaceNameString(kn.getNamespaceNameNode()), kn -> kn, (kn1, kn2) -> kn2))
        );

        keyInterfaceNodeMap = Collections.unmodifiableMap(
            fileNodes
                .stream()
                .flatMap(fn -> fn.getKeyNodes().stream())
                .filter(KeyNode::isInterface)
                .collect(Collectors.toMap(kni -> ParseTreeUtils.getNamespaceNameString(kni.getNamespaceNameNode()), kni -> kni, (kni1, kni2) -> kni2))
        );

        enumNodeMap = Collections.unmodifiableMap(
            fileNodes
                .stream()
                .flatMap(fn -> fn.getEnumNodes().stream())
                .collect(Collectors.toMap(en -> ParseTreeUtils.getNamespaceNameString(en.getNamespaceNameNode()), en -> en, (en1, en2) -> en2))
        );
    }

    public List<FileNode> getFileNodes() {
        return fileNodes;
    }

    public Map<String, TypeNode> getAllTypeNodeMap() {
        return allTypeNodeMap;
    }

    public Map<String, TypeNode> getTypeNodeMap() {
        return typeNodeMap;
    }

    public Map<String, TypeNode> getTypeInterfaceNodeMap() {
        return typeInterfaceNodeMap;
    }

    public Map<String, KeyNode> getAllKeyNodeMap() {
        return allKeyNodeMap;
    }

    public Map<String, KeyNode> getKeyNodeMap() {
        return keyNodeMap;
    }

    public Map<String, KeyNode> getKeyInterfaceNodeMap() {
        return keyInterfaceNodeMap;
    }

    public Map<String, EnumNode> getEnumNodeMap() {
        return enumNodeMap;
    }

}