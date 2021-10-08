package com.kdsc.protogen.compilerresults;

import com.kdsc.protogen.parsetreenodes.FileNode;
import com.kdsc.protogen.parsetreenodes.ProtoGenEnumNode;
import com.kdsc.protogen.parsetreenodes.ProtoGenKeyNode;
import com.kdsc.protogen.parsetreenodes.ProtoGenTypeNode;
import com.kdsc.protogen.parsetreenodes.utils.ParseTreeUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class CompilerResults {

    private final List<FileNode> fileNodes;
    private final Map<String, ProtoGenTypeNode> allTypeNodeMap;
    private final Map<String, ProtoGenTypeNode> typeNodeMap;
    private final Map<String, ProtoGenTypeNode> typeInterfaceNodeMap;
    private final Map<String, ProtoGenKeyNode> allKeyNodeMap;
    private final Map<String, ProtoGenKeyNode> keyNodeMap;
    private final Map<String, ProtoGenKeyNode> keyInterfaceNodeMap;
    private final Map<String, ProtoGenEnumNode> enumNodeMap;

    public CompilerResults(final List<FileNode> fileNodes) {
        Objects.requireNonNull(fileNodes);
        this.fileNodes = Collections.unmodifiableList(fileNodes);

        allTypeNodeMap = Collections.unmodifiableMap(
            fileNodes
                .stream()
                .flatMap(fn -> fn.getProtoGenTypeNodes().stream())
                .collect(Collectors.toMap(tn -> ParseTreeUtils.getNamespaceNameString(tn.getNamespaceNameNode()), tn -> tn, (tn1, tn2) -> tn2))
        );

        typeNodeMap = Collections.unmodifiableMap(
            fileNodes
                .stream()
                .flatMap(fn -> fn.getProtoGenTypeNodes().stream())
                .filter(tn -> !tn.isInterface())
                .collect(Collectors.toMap(tn -> ParseTreeUtils.getNamespaceNameString(tn.getNamespaceNameNode()), tn -> tn, (tn1, tn2) -> tn2))
        );

        typeInterfaceNodeMap = Collections.unmodifiableMap(
            fileNodes
                .stream()
                .flatMap(fn -> fn.getProtoGenTypeNodes().stream())
                .filter(ProtoGenTypeNode::isInterface)
                .collect(Collectors.toMap(tni -> ParseTreeUtils.getNamespaceNameString(tni.getNamespaceNameNode()), tni -> tni, (tni1, tni2) -> tni2))
        );

        allKeyNodeMap = Collections.unmodifiableMap(
            fileNodes
                .stream()
                .flatMap(fn -> fn.getProtoGenKeyNodes().stream())
                .collect(Collectors.toMap(kn -> ParseTreeUtils.getNamespaceNameString(kn.getNamespaceNameNode()), kn -> kn, (kn1, kn2) -> kn2))
        );

        keyNodeMap = Collections.unmodifiableMap(
            fileNodes
                .stream()
                .flatMap(fn -> fn.getProtoGenKeyNodes().stream())
                .filter(kn -> !kn.isInterface())
                .collect(Collectors.toMap(kn -> ParseTreeUtils.getNamespaceNameString(kn.getNamespaceNameNode()), kn -> kn, (kn1, kn2) -> kn2))
        );

        keyInterfaceNodeMap = Collections.unmodifiableMap(
            fileNodes
                .stream()
                .flatMap(fn -> fn.getProtoGenKeyNodes().stream())
                .filter(ProtoGenKeyNode::isInterface)
                .collect(Collectors.toMap(kni -> ParseTreeUtils.getNamespaceNameString(kni.getNamespaceNameNode()), kni -> kni, (kni1, kni2) -> kni2))
        );

        enumNodeMap = Collections.unmodifiableMap(
            fileNodes
                .stream()
                .flatMap(fn -> fn.getProtoGenEnumNodes().stream())
                .collect(Collectors.toMap(en -> ParseTreeUtils.getNamespaceNameString(en.getNamespaceNameNode()), en -> en, (en1, en2) -> en2))
        );
    }

    public List<FileNode> getFileNodes() {
        return fileNodes;
    }

    public Map<String, ProtoGenTypeNode> getAllTypeNodeMap() {
        return allTypeNodeMap;
    }

    public Map<String, ProtoGenTypeNode> getTypeNodeMap() {
        return typeNodeMap;
    }

    public Map<String, ProtoGenTypeNode> getTypeInterfaceNodeMap() {
        return typeInterfaceNodeMap;
    }

    public Map<String, ProtoGenKeyNode> getAllKeyNodeMap() {
        return allKeyNodeMap;
    }

    public Map<String, ProtoGenKeyNode> getKeyNodeMap() {
        return keyNodeMap;
    }

    public Map<String, ProtoGenKeyNode> getKeyInterfaceNodeMap() {
        return keyInterfaceNodeMap;
    }

    public Map<String, ProtoGenEnumNode> getEnumNodeMap() {
        return enumNodeMap;
    }

}