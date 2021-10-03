package com.kdsc.protogen.transform.shared;

import com.kdsc.protogen.filegenerationtree.shared.FieldNode;
import com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes.*;
import com.kdsc.protogen.transform.TransformerContext;
import com.kdsc.protogen.transform.java.FileContext;

import java.util.List;
import java.util.stream.Collectors;

//TODO:KDM Should this be called FieldsTransformer
public class FieldTransformer {

    public List<FieldNode> transformFieldsNodes(final TransformerContext transformerContext, final FileContext fileContext, final com.kdsc.protogen.parsetree.FieldsNode fieldsNodes) {

        return fieldsNodes
            .getFieldNodes()
            .stream()
            .map(fn -> transformFieldNode(transformerContext, fileContext, fn))
            .collect(Collectors.toList());
    }

    private FieldNode transformFieldNode(final TransformerContext transformerContext, final FileContext fileContext, final com.kdsc.protogen.parsetree.FieldNode fieldNode) {
        return new FieldNode(
            fieldNode.getFieldNameNode().getFieldName(),
            transformFieldTypeNode(transformerContext, fileContext, fieldNode.getFieldTypeNode())
        );
    }

    private FieldTypeNode transformFieldTypeNode(final TransformerContext transformerContext, final FileContext fileContext, final com.kdsc.protogen.parsetree.fieldtypenodes.FieldTypeNode fieldTypeNode) {

        if(fieldTypeNode.getArrayFieldTypeNode().isPresent()) {
            return transformArrayFieldTypeNode(transformerContext, fileContext, fieldTypeNode.getArrayFieldTypeNode().get(), fieldTypeNode.isOptional());
        } else if (fieldTypeNode.getNonArrayFieldTypeNode().isPresent()) {
            return transformNonArrayFieldTypeNode(transformerContext, fileContext, fieldTypeNode.getNonArrayFieldTypeNode().get(), fieldTypeNode.isOptional());
        }

        throw new RuntimeException("This should never happen");
    }

    //TODO:KMD This is optional is a hack
    private FieldTypeNode transformArrayFieldTypeNode(final TransformerContext transformerContext, final FileContext fileContext, final com.kdsc.protogen.parsetree.fieldtypenodes.ArrayFieldTypeNode fieldTypeNode, final boolean isOptional) {
        //TODO:KMD Just for now
        return null;
    }

    //TODO:KMD This is optional is a hack
    private FieldTypeNode transformNonArrayFieldTypeNode(final TransformerContext transformerContext, final FileContext fileContext, final com.kdsc.protogen.parsetree.fieldtypenodes.NonArrayFieldTypeNode fieldTypeNode, final boolean isOptional) {
        if(isOptional) {
            fileContext.addImport("java.util.Optional");
        }
        return switch (fieldTypeNode) {
            case com.kdsc.protogen.parsetree.fieldtypenodes.DoubleFieldTypeNode ignored -> new DoubleFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.FloatFieldTypeNode ignored -> new FloatFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.Int32FieldTypeNode ignored -> new Int32FieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.Int64FieldTypeNode ignored -> new Int64FieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.BoolFieldTypeNode ignored -> new BoolFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.StringFieldTypeNode ignored -> new StringFieldTypeNode(isOptional);
            default -> throw new IllegalStateException("Unexpected value: " + fieldTypeNode);
        };
    }

}