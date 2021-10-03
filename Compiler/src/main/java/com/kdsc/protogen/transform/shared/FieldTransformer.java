package com.kdsc.protogen.transform.shared;

import com.kdsc.protogen.filegenerationtree.shared.FieldNode;
import com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes.*;
import com.kdsc.protogen.parsetree.utils.ParseTreeUtils;
import com.kdsc.protogen.transform.TransformerContext;
import com.kdsc.protogen.transform.FileContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//TODO:KDM Should this be called FieldsTransformer
public class FieldTransformer {

    private static final Set<Class> protogenTypes = new HashSet<>();

    static {
        protogenTypes.add(com.kdsc.protogen.parsetree.fieldtypenodes.DecimalFieldTypeNode.class);
        protogenTypes.add(com.kdsc.protogen.parsetree.fieldtypenodes.DateFieldTypeNode.class);
        protogenTypes.add(com.kdsc.protogen.parsetree.fieldtypenodes.DateTimeFieldTypeNode.class);
        protogenTypes.add(com.kdsc.protogen.parsetree.fieldtypenodes.LocalDateTimeFieldTypeNode.class);
    }

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
            fileContext.addJavaImport("java.util.Optional");
        }
        if(fieldTypeNode instanceof com.kdsc.protogen.parsetree.fieldtypenodes.TypeFieldTypeNode typeFieldTypeNode) {
            fileContext.addProtoImport(ParseTreeUtils.getNamespaceNameString(typeFieldTypeNode.getNamespaceNameGenericParametersNode().getNamespaceNameNode()));
        }
        if(protogenTypes.contains(fieldTypeNode.getClass())) {
            fileContext.addProtoImport("protogentypes");
        }
        return switch (fieldTypeNode) {
            case com.kdsc.protogen.parsetree.fieldtypenodes.DoubleFieldTypeNode ignored -> new DoubleFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.FloatFieldTypeNode ignored -> new FloatFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.Int32FieldTypeNode ignored -> new Int32FieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.Int64FieldTypeNode ignored -> new Int64FieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.BoolFieldTypeNode ignored -> new BoolFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.StringFieldTypeNode ignored -> new StringFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.DecimalFieldTypeNode ignored -> new DecimalFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.DateFieldTypeNode ignored -> new DateFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.DateTimeFieldTypeNode ignored -> new DateTimeFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.LocalDateTimeFieldTypeNode ignored -> new LocalDateTimeFieldTypeNode(isOptional);
            case com.kdsc.protogen.parsetree.fieldtypenodes.TypeFieldTypeNode typeFieldTypeNode -> new TypeFieldTypeNode(
                isOptional,
                ParseTreeUtils.getNamespaceString(typeFieldTypeNode.getNamespaceNameGenericParametersNode().getNamespaceNameNode()), //TODO:KMD Perhaps add some helper methods here on the has namespace name interface
                typeFieldTypeNode.getNamespaceNameGenericParametersNode().getNamespaceNameNode().getNameNode().getName()
            );
            case com.kdsc.protogen.parsetree.fieldtypenodes.ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode -> new ValueOrErrorFieldTypeNode(
                isOptional,
                transformFieldTypeNode(transformerContext, fileContext, valueOrErrorFieldTypeNode.getFieldTypeNode())
            );
            default -> throw new IllegalStateException("Unexpected value: " + fieldTypeNode);
        };
    }

}