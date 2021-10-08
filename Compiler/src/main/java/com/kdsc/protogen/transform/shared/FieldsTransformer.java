package com.kdsc.protogen.transform.shared;

import com.kdsc.protogen.compilerresults.CompilerResults;
import com.kdsc.protogen.filegenerationtreenodes.shared.FieldNode;
import com.kdsc.protogen.filegenerationtreenodes.shared.fieldtypenodes.*;
import com.kdsc.protogen.parsetreenodes.ImplementsListNode;
import com.kdsc.protogen.parsetreenodes.NamespaceNameGenericParametersNode;
import com.kdsc.protogen.parsetreenodes.TypeNode;
import com.kdsc.protogen.parsetreenodes.utils.ParseTreeUtils;
import com.kdsc.protogen.transform.TransformerContext;
import com.kdsc.protogen.transform.FileContext;

import java.util.*;
import java.util.stream.Collectors;

public class FieldsTransformer {

    private static final Set<Class> protogenTypes = new HashSet<>();

    static {
        protogenTypes.add(com.kdsc.protogen.parsetreenodes.fieldtypenodes.DecimalFieldTypeNode.class);
        protogenTypes.add(com.kdsc.protogen.parsetreenodes.fieldtypenodes.DateFieldTypeNode.class);
        protogenTypes.add(com.kdsc.protogen.parsetreenodes.fieldtypenodes.DateTimeFieldTypeNode.class);
        protogenTypes.add(com.kdsc.protogen.parsetreenodes.fieldtypenodes.LocalDateTimeFieldTypeNode.class);
    }

    public List<FieldNode> transformFieldsNodes(final CompilerResults compilerResults, final TransformerContext transformerContext, final FileContext fileContext, final TypeNode typeNode, final boolean protoMode, final boolean interfaceMode) {
        List<com.kdsc.protogen.parsetreenodes.FieldNode> fieldNodes = typeNode.getFieldsNode().isPresent()
            ? new ArrayList<>(typeNode.getFieldsNode().get().getFieldNodes())
            : Collections.emptyList();
        if(protoMode) {

            //TODO:KMD Why do I have to not use var here, a bit strange
            List<FieldNode> interfaceFieldsNode = typeNode.getImplementsListNode().isPresent()
                ? typeNode
                    .getImplementsListNode()
                    .stream()
                    .flatMap(
                        iln -> iln.getNamespaceNameGenericParametersNodes().stream()
                    )
                    .filter(nngp ->compilerResults.getTypeInterfaceNodeMap().containsKey(ParseTreeUtils.getNamespaceNameString(nngp.getNamespaceNameNode())))
                    .map(
                        nngp ->
                        {
                            fileContext.addProtoImport(ParseTreeUtils.getNamespaceNameString(nngp.getNamespaceNameNode()));
                            return new FieldNode(
                                "__interface__" + ParseTreeUtils.getNamespaceNameString(nngp.getNamespaceNameNode()).replace(".", "_"),
                                new TypeFieldTypeNode(
                                        false,
                                        ParseTreeUtils.getNamespaceString(nngp.getNamespaceNameNode()),
                                        nngp.getNamespaceNameNode().getNameNode().getName()
                                )
                            );
                        }
                    )
                    .collect(Collectors.toList())
                : Collections.emptyList();


            var transformedFieldsNode = new ArrayList<FieldNode>();
            transformedFieldsNode.addAll(interfaceFieldsNode);
            transformedFieldsNode.addAll(transformFieldsNodes(compilerResults, transformerContext, fileContext, fieldNodes));
            return transformedFieldsNode;
        } else {
            if(!interfaceMode) {
                collectAllFieldNodes(compilerResults, transformerContext, fieldNodes, typeNode.getImplementsListNode());
            }
            return transformFieldsNodes(compilerResults, transformerContext, fileContext, fieldNodes);
        }
    }

    //TODO:KMD This is an utter hack, just making it work for now
    public void collectAllFieldNodes(final CompilerResults compilerResults, final TransformerContext transformerContext, final List<com.kdsc.protogen.parsetreenodes.FieldNode> fieldNodes, final Optional<ImplementsListNode> implementsListNode) {
        implementsListNode.ifPresent(
            listNode -> listNode
                .getNamespaceNameGenericParametersNodes()
                .forEach(
                    nngp -> collectAllFieldNodes(compilerResults, transformerContext, fieldNodes, nngp)
                )
        );
    }

    public void collectAllFieldNodes(final CompilerResults compilerResults, final TransformerContext transformerContext, final List<com.kdsc.protogen.parsetreenodes.FieldNode> fieldNodes, final NamespaceNameGenericParametersNode namespaceNameGenericParametersNode) {
        var typeInterface = compilerResults.getTypeInterfaceNodeMap().get(ParseTreeUtils.getNamespaceNameString(namespaceNameGenericParametersNode.getNamespaceNameNode()));
        if(typeInterface.getFieldsNode().isPresent()) {
            fieldNodes.addAll(typeInterface.getFieldsNode().get().getFieldNodes());
        }
        typeInterface
            .getImplementsListNode()
            .stream()
            .flatMap(iln -> iln.getNamespaceNameGenericParametersNodes().stream())
            .forEach(nngp -> collectAllFieldNodes(compilerResults, transformerContext, fieldNodes, nngp));
    }

    private List<FieldNode> transformFieldsNodes(final CompilerResults compilerResults, final TransformerContext transformerContext, final FileContext fileContext, final List<com.kdsc.protogen.parsetreenodes.FieldNode> fieldNodes) {
        return fieldNodes
            .stream()
            .map(fn -> transformFieldNode(compilerResults, transformerContext, fileContext, fn))
            .collect(Collectors.toList());
    }

    private FieldNode transformFieldNode(final CompilerResults compilerResults, final TransformerContext transformerContext, final FileContext fileContext, final com.kdsc.protogen.parsetreenodes.FieldNode fieldNode) {
        return new FieldNode(
            fieldNode.getFieldNameNode().getFieldName(),
            transformFieldTypeNode(compilerResults, transformerContext, fileContext, fieldNode.getFieldTypeNode())
        );
    }

    private FieldTypeNode transformFieldTypeNode(final CompilerResults compilerResults, final TransformerContext transformerContext, final FileContext fileContext, final com.kdsc.protogen.parsetreenodes.fieldtypenodes.FieldTypeNode fieldTypeNode) {

        if(fieldTypeNode.getArrayFieldTypeNode().isPresent()) {
            return transformArrayFieldTypeNode(compilerResults, transformerContext, fileContext, fieldTypeNode.getArrayFieldTypeNode().get(), fieldTypeNode.isOptional());
        } else if (fieldTypeNode.getNonArrayFieldTypeNode().isPresent()) {
            return transformNonArrayFieldTypeNode(compilerResults, transformerContext, fileContext, fieldTypeNode.getNonArrayFieldTypeNode().get(), fieldTypeNode.isOptional());
        }

        throw new RuntimeException("This should never happen");
    }

    //TODO:KMD This is optional is a hack
    private FieldTypeNode transformArrayFieldTypeNode(final CompilerResults compilerResults, final TransformerContext transformerContext, final FileContext fileContext, final com.kdsc.protogen.parsetreenodes.fieldtypenodes.ArrayFieldTypeNode arrayFieldTypeNode, final boolean isOptional) {
        if(isOptional) {
            fileContext.addJavaImport("java.util.Optional");
        }
        return new ArrayFieldTypeNode(
            isOptional,
            //TODO:KMD Got a problem here with optionals in arrays
            transformNonArrayFieldTypeNode(compilerResults, transformerContext, fileContext, arrayFieldTypeNode.getNonArrayFieldTypeNode(), false)
        );
    }

    //TODO:KMD This is optional is a hack
    private FieldTypeNode transformNonArrayFieldTypeNode(final CompilerResults compilerResults, final TransformerContext transformerContext, final FileContext fileContext, final com.kdsc.protogen.parsetreenodes.fieldtypenodes.NonArrayFieldTypeNode fieldTypeNode, final boolean isOptional) {
        if(isOptional) {
            fileContext.addJavaImport("java.util.Optional");
        }
        //TODO:KMD Need to filter out own package
        if(fieldTypeNode instanceof com.kdsc.protogen.parsetreenodes.fieldtypenodes.TypeFieldTypeNode typeFieldTypeNode) {
            fileContext.addProtoImport(ParseTreeUtils.getNamespaceNameString(typeFieldTypeNode.getNamespaceNameGenericParametersNode().getNamespaceNameNode()));
        }
        if(protogenTypes.contains(fieldTypeNode.getClass())) {
            fileContext.addProtoImport("protogentypes");
        }
        return switch (fieldTypeNode) {
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.DoubleFieldTypeNode ignored -> new DoubleFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.FloatFieldTypeNode ignored -> new FloatFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.Int32FieldTypeNode ignored -> new Int32FieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.Int64FieldTypeNode ignored -> new Int64FieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.BoolFieldTypeNode ignored -> new BoolFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.StringFieldTypeNode ignored -> new StringFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.DecimalFieldTypeNode ignored -> new DecimalFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.DateFieldTypeNode ignored -> new DateFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.DateTimeFieldTypeNode ignored -> new DateTimeFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.LocalDateFieldTypeNode ignored -> new LocalDateFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.LocalDateTimeFieldTypeNode ignored -> new LocalDateTimeFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.BytesFieldTypeNode ignored -> new BytesFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.TypeFieldTypeNode typeFieldTypeNode -> new TypeFieldTypeNode(
                isOptional,
                ParseTreeUtils.getNamespaceString(typeFieldTypeNode.getNamespaceNameGenericParametersNode().getNamespaceNameNode()), //TODO:KMD Perhaps add some helper methods here on the has namespace name interface
                typeFieldTypeNode.getNamespaceNameGenericParametersNode().getNamespaceNameNode().getNameNode().getName()
            );
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode -> new ValueOrErrorFieldTypeNode(
                isOptional,
                transformFieldTypeNode(compilerResults, transformerContext, fileContext, valueOrErrorFieldTypeNode.getFieldTypeNode())
            );
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.SetFieldTypeNode setFieldTypeNode -> new SetFieldTypeNode(
                isOptional,
                transformFieldTypeNode(compilerResults, transformerContext, fileContext, setFieldTypeNode.getFieldTypeNode())
            );
            case com.kdsc.protogen.parsetreenodes.fieldtypenodes.MapFieldTypeNode mapFieldTypeNode -> new MapFieldTypeNode(
                isOptional,
                transformFieldTypeNode(compilerResults, transformerContext, fileContext, mapFieldTypeNode.getKeyFieldTypeNode()),
                transformFieldTypeNode(compilerResults, transformerContext, fileContext, mapFieldTypeNode.getValueFieldTypeNode())
            );
            default -> throw new IllegalStateException("Unexpected value: " + fieldTypeNode);
        };
    }

}