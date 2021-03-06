package com.kdsc.protogen.transform.proto;

import com.kdsc.protogen.compilerresults.CompilerResults;
import com.kdsc.protogen.filegenerationtreenodes.FileNode;

import com.kdsc.protogen.filegenerationtreenodes.proto.EnumCaseNode;
import com.kdsc.protogen.filegenerationtreenodes.proto.EnumFileNode;
import com.kdsc.protogen.filegenerationtreenodes.proto.MessageFileNode;
import com.kdsc.protogen.parsetreenodes.EnumNode;
import com.kdsc.protogen.parsetreenodes.TypeNode;
import com.kdsc.protogen.parsetreenodes.utils.ParseTreeUtils;
import com.kdsc.protogen.transform.TransformerContext;
import com.kdsc.protogen.transform.FileContext;
import com.kdsc.protogen.transform.shared.FieldsTransformer;
import com.kdsc.protogen.transform.utils.TransformUtils;
import com.kdsc.protogen.utils.Streams;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//TODO:KMD This is a total mess at the moment
public class Transformer implements com.kdsc.protogen.transform.Transformer {

    @Override
    public List<FileNode> transform(final CompilerResults compilerResults, final TransformerContext transformerContext) {
        return compilerResults
            .getFileNodes()
            .stream()
            .flatMap(fn -> transformFileNode(compilerResults, transformerContext, fn).stream())
            .collect(Collectors.toList());
    }

    private List<FileNode> transformFileNode(final CompilerResults compilerResults, final TransformerContext transformerContext, final com.kdsc.protogen.parsetreenodes.FileNode fileNode) {
        return Streams.concat(
            fileNode
                .getEnumNodes()
                .stream()
                .map(en -> transformEnumNode(compilerResults, transformerContext, en)),
            fileNode
                .getTypeNodes()
                .stream()
                .map(tn -> transformTypeNode(compilerResults, transformerContext, tn))//,
//            fileNode
//                .getKeyNodes()
//                .stream()
//                .map(kn -> transformKeyNode(transformerContext, kn))
        ).collect(Collectors.toList());
    }

    //TODO:KMD Figure out what to do about these paths
    private FileNode transformEnumNode(final CompilerResults compilerResults, final TransformerContext transformerContext, final EnumNode enumNode) {
        if(enumNode.getEnumCasesNode().isPresent()) {
            return new EnumFileNode(
                TransformUtils.convertNamespaceNameNodeToName(enumNode.getNamespaceNameNode()) + TransformerContext.protoFileExtension,
                "",
                ParseTreeUtils.getNamespaceString(enumNode.getNamespaceNameNode()),
                enumNode.getNamespaceNameNode().getNameNode().getName(),
                transformEnumCaseNodes(compilerResults, transformerContext, enumNode.getEnumCasesNode().get())
            );
        } else if(enumNode.getEnumVersionsNode().isPresent()) {
            return new EnumFileNode(
                TransformUtils.convertNamespaceNameNodeToName(enumNode.getNamespaceNameNode()) + TransformerContext.protoFileExtension,
                "",
                ParseTreeUtils.getNamespaceString(enumNode.getNamespaceNameNode()),
                enumNode.getNamespaceNameNode().getNameNode().getName(),
                Collections.emptyList()
            );
        }
        return new EnumFileNode(
            TransformUtils.convertNamespaceNameNodeToName(enumNode.getNamespaceNameNode()) + TransformerContext.protoFileExtension,
            "",
            ParseTreeUtils.getNamespaceString(enumNode.getNamespaceNameNode()),
            enumNode.getNamespaceNameNode().getNameNode().getName(),
            Collections.emptyList()
        );
    }

    private FileNode transformTypeNode(final CompilerResults compilerResults, final TransformerContext transformerContext, final TypeNode typeNode) {
        var fieldTransformer = new FieldsTransformer();

        var fileContext = new FileContext();

        var fieldNodes = fieldTransformer.transformFieldsNodes(compilerResults, transformerContext, fileContext, typeNode, true, false);

        return new MessageFileNode(
            TransformUtils.convertNamespaceNameNodeToName(typeNode.getNamespaceNameNode()) + TransformerContext.protoFileExtension,
            "",
            ParseTreeUtils.getNamespaceString(typeNode.getNamespaceNameNode()),
            typeNode.getNamespaceNameNode().getNameNode().getName(),
            fileContext.getProtoImportStatements(),
            //TODO:KMD Warning here
            fieldNodes
        );
    }

//    private FileNode transformKeyNode(final TransformerContext transformerContext, final com.kdsc.protogen.parsetree.KeyNode keyNode) {
//        var fieldTransformer = new FieldTransformer();
//
//        var fileContext = new FileContext();
//
//        var fieldNodes = fieldTransformer.transformFieldsNodes(transformerContext, fileContext, keyNode);
//
//        return new MessageFileNode(
//            TransformUtils.convertNamespaceNameNodeToName(keyNode.getNamespaceNameNode()) + TransformerContext.protoFileExtension,
//            "",
//            ParseTreeUtils.getNamespaceString(keyNode.getNamespaceNameNode()),
//            keyNode.getNamespaceNameNode().getNameNode().getName(),
//            fileContext.getProtoImportStatements(),
//            //TODO:KMD Warning here
//            fieldNodes
//        );
//    }

    private List<EnumCaseNode> transformEnumCaseNodes(final CompilerResults compilerResults, final TransformerContext transformerContext, final com.kdsc.protogen.parsetreenodes.EnumCasesNode enumCasesNode) {
        return enumCasesNode
            .getEnumNameNodes()
            .stream()
            .map(enn -> transformEnumCaseNode(compilerResults, transformerContext, enn))
            .collect(Collectors.toList());
    }

    private EnumCaseNode transformEnumCaseNode(final CompilerResults compilerResults, final TransformerContext transformerContext, final com.kdsc.protogen.parsetreenodes.EnumNameNode enumNameNode) {
        return new EnumCaseNode(
            enumNameNode.getEnumName()
        );
    }

}