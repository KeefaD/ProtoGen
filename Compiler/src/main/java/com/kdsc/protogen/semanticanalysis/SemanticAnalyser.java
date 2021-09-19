package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.parsetree.*;
import com.kdsc.protogen.parsetree.utils.ParseTreeUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.kdsc.protogen.semanticanalysis.SemanticErrorFactory.createSemanticError;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.*;

public class SemanticAnalyser {

    public static List<SemanticError> runSemanticAnalysis(List<FileNode> fileNodes) {

        var returnSemanticErrors = new ArrayList<SemanticError>();

        checkRedefinitionOfObject(returnSemanticErrors, fileNodes);

        var typeNodes = fileNodes
            .stream()
            .flatMap(fn -> fn.getProtoGenTypeNodes().stream())
            .collect(Collectors.toMap(tn -> ParseTreeUtils.getNamespaceNameString(tn.getNamespaceNameNode()), tn -> tn, (tn1, tn2) -> tn1));

        var keyNodes = fileNodes
            .stream()
            .flatMap(fn -> fn.getProtoGenEnumNodes().stream())
            .collect(Collectors.toMap(kn -> ParseTreeUtils.getNamespaceNameString(kn.getNamespaceNameNode()), kn -> kn, (kn1, kn2) -> kn2));

        var enumNodes = fileNodes
            .stream()
            .flatMap(fn -> fn.getProtoGenEnumNodes().stream())
            .collect(Collectors.toMap(en -> ParseTreeUtils.getNamespaceNameString(en.getNamespaceNameNode()), en -> en, (en1, en2) -> en2));

        checkTypes(returnSemanticErrors, typeNodes);
        checkEnums(returnSemanticErrors, enumNodes);

        return returnSemanticErrors
            .stream()
            .sorted(
                Comparator.comparing(SemanticError::sourceFileName)
                    .thenComparing(SemanticError::line)
                    .thenComparing(SemanticError::charPosition)
            )
            .collect(Collectors.toList());
    }

    private static void checkRedefinitionOfObject(List<SemanticError> semanticErrors, List<FileNode> fileNodes) {

        var topLevelObjects = fileNodes
            .stream()
            .flatMap(
                fn -> Stream.of(
                    fn.getProtoGenTypeNodes().stream(),
                    fn.getProtoGenKeyNodes().stream(),
                    fn.getProtoGenEnumNodes().stream()
                ).flatMap(s -> s)
            )
            .collect(Collectors.toList());

        var topLevelObjectNamespaceNamesAsString = new HashSet<String>();

        topLevelObjects
            .forEach(
                tln -> {
                    var namespaceNameAsString = ParseTreeUtils.getNamespaceNameString(tln.getNamespaceNameNode());
                    if(topLevelObjectNamespaceNamesAsString.contains(namespaceNameAsString)) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_OBJECT, tln, namespaceNameAsString));
                    } else {
                        topLevelObjectNamespaceNamesAsString.add(namespaceNameAsString);
                    }
                }
            );
    }

    private static void checkTypes(List<SemanticError> semanticErrors, Map<String, ProtoGenTypeNode> typeNodeMap) {
        typeNodeMap
            .values()
            .forEach(tn -> checkType(semanticErrors, typeNodeMap, tn));
    }

    private static void checkType(List<SemanticError> semanticErrors, Map<String, ProtoGenTypeNode> typeNodeMap, ProtoGenTypeNode typeNode) {

        //TODO:KMD Generic parameters and bounds
        //TODO:KMD Inheritance loop

        if(typeNode.getImplementsListNode().isPresent()) {
            typeNode
                .getImplementsListNode()
                .get()
                .getNamespaceNameGenericParametersWithoutBoundsNodes()
                .forEach(
                    nngp -> {
                        var namespaceNameAsString = ParseTreeUtils.getNamespaceNameString(nngp.getNamespaceNameNode());
                        if(!typeNodeMap.containsKey(namespaceNameAsString)) {
                            semanticErrors.add(createSemanticError(TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST, nngp, ParseTreeUtils.getNamespaceNameString(typeNode.getNamespaceNameNode()), namespaceNameAsString));
                        }
                    }
                );
        }

        var versions = typeNode
            .getVersionsNode()
            .stream()
            .flatMap(vn -> vn.getVersionNodes().stream())
            .sorted(Comparator.comparing(vn -> vn.getVersionNumberNode().getVersionNumber()))
            .collect(Collectors.toList());

        var versionNumbersSet = new HashSet<Long>();
        versions
            .stream()
            .map(VersionNode::getVersionNumberNode)
            .forEach(
                vn -> {
                    if(versionNumbersSet.contains(vn.getVersionNumber())) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_TYPE_VERSION, vn, vn.getVersionNumber()));
                    } else {
                        versionNumbersSet.add(vn.getVersionNumber());
                    }
                }
            );
    }

    private static void checkEnums(List<SemanticError> semanticErrors, Map<String, ProtoGenEnumNode> enumNodeMap) {
        enumNodeMap
            .values()
            .forEach(en -> checkEnum(semanticErrors, en));
    }

    private static void checkEnum(List<SemanticError> semanticErrors, ProtoGenEnumNode enumNode) {
        var versions = enumNode
            .getEnumVersionsNode()
            .stream()
            .flatMap(evn -> evn.getEnumVersionNodes().stream())
            .sorted(Comparator.comparing(evn -> evn.getVersionNumberNode().getVersionNumber()))
            .collect(Collectors.toList());

        var versionNumbersSet = new HashSet<Long>();
        versions
            .stream()
            .map(EnumVersionNode::getVersionNumberNode)
            .forEach(
                vn -> {
                    if(versionNumbersSet.contains(vn.getVersionNumber())) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_ENUM_VERSION, vn, vn.getVersionNumber()));
                    } else {
                        versionNumbersSet.add(vn.getVersionNumber());
                    }
                }
            );

        versions
            .stream()
            .filter(evn -> evn.getEnumCasesNode().isPresent())
            .forEach(evn -> checkEnumCases(semanticErrors, evn.getEnumCasesNode().get()));

        if(enumNode.getEnumCasesNode().isPresent()) checkEnumCases(semanticErrors, enumNode.getEnumCasesNode().get());
    }

    private static void checkEnumCases(List<SemanticError> semanticErrors, EnumCasesNode enumCasesNode) {
        var enumNameSet = new HashSet<String>();
        enumCasesNode
            .getEnumNameNodes()
            .forEach(
                enn -> {
                    if(enumNameSet.contains(enn.getEnumName())) {
                        semanticErrors.add(createSemanticError(REDEFINITION_OF_ENUM_CASE, enn, enn.getEnumName()));
                    } else {
                        enumNameSet.add(enn.getEnumName());
                    }
                }
            );
    }

}