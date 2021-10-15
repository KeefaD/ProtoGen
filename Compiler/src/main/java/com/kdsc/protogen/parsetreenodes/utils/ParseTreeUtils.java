package com.kdsc.protogen.parsetreenodes.utils;

import com.kdsc.protogen.parsetreenodes.NamespaceNameNode;
import com.kdsc.protogen.parsetreenodes.NamespaceNode;
import com.kdsc.protogen.parsetreenodes.fieldtypenodes.*;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ParseTreeUtils {

    public static final String NAMESPACE_SEPARATOR = ".";

    public static String getNamespaceString(final NamespaceNameNode namespaceNameNode) {
        Objects.requireNonNull(namespaceNameNode);
        return namespaceNameNode
            .getNamespaceNodes()
            .stream()
            .map(NamespaceNode::getNamespace)
            .collect(Collectors.joining(NAMESPACE_SEPARATOR));
    }

    public static String getNamespaceNameString(final NamespaceNameNode namespaceNameNode) {
        Objects.requireNonNull(namespaceNameNode);
        var returnString = namespaceNameNode
            .getNamespaceNodes()
            .stream()
            .map(NamespaceNode::getNamespace)
            .collect(Collectors.joining(NAMESPACE_SEPARATOR));
        return returnString + NAMESPACE_SEPARATOR + namespaceNameNode.getNameNode().getName();
    }

    public static String convertFieldTypeToString(final FieldTypeNode fieldTypeNode) {
        Objects.requireNonNull(fieldTypeNode);
        var stringBuilder = new StringBuilder();
        if(fieldTypeNode.getArrayFieldTypeNode().isPresent()) {
            stringBuilder.append(convertFieldTypeToString(fieldTypeNode.getArrayFieldTypeNode().get()));
        } else if(fieldTypeNode.getNonArrayFieldTypeNode().isPresent()) {
            stringBuilder.append(convertFieldTypeToString(fieldTypeNode.getNonArrayFieldTypeNode().get()));
        } else {
            throw new RuntimeException("One of either ArrayFieldTypeNode or NonArrayFieldTypeNode must be populated");
        }
        return stringBuilder.toString();
    }

    public static String convertFieldTypeToString(final ArrayFieldTypeNode arrayFieldTypeNode) {
        Objects.requireNonNull(arrayFieldTypeNode);
        var stringBuilder = new StringBuilder();
        stringBuilder.append(convertFieldTypeToString(arrayFieldTypeNode.getNonArrayFieldTypeNode()));
        LongStream.range(0, arrayFieldTypeNode.getDimensions())
            .forEach(i -> stringBuilder.append("[]"));
        return stringBuilder.toString();
    }

    //CHECK_ALL_FIELD_TYPES_PRESENT
    public static String convertFieldTypeToString(final NonArrayFieldTypeNode nonArrayFieldTypeNode) {
        Objects.requireNonNull(nonArrayFieldTypeNode);
        var stringBuilder = new StringBuilder();
        switch (nonArrayFieldTypeNode) {
            case DoubleFieldTypeNode ignored -> stringBuilder.append("double");
            case FloatFieldTypeNode ignored -> stringBuilder.append("float");
            case Int32FieldTypeNode ignored -> stringBuilder.append("int32");
            case Int64FieldTypeNode ignored -> stringBuilder.append("int64");
            case BoolFieldTypeNode ignored -> stringBuilder.append("bool");
            case StringFieldTypeNode ignored -> stringBuilder.append("string");
            case BytesFieldTypeNode ignored -> stringBuilder.append("bytes");
            case DecimalFieldTypeNode ignored -> stringBuilder.append("decimal");
            case DateFieldTypeNode ignored -> stringBuilder.append("date");
            case DateTimeFieldTypeNode ignored -> stringBuilder.append("datetime");
            case LocalDateFieldTypeNode ignored -> stringBuilder.append("localdate");
            case LocalDateTimeFieldTypeNode ignored -> stringBuilder.append("localdatetime");
            case GenericObjectFieldTypeNode genericObjectFieldTypeNode -> stringBuilder.append(genericObjectFieldTypeNode.getGenericParameterNode().getIdentifier());
            case ObjectFieldTypeNode objectFieldTypeNode -> stringBuilder.append("UnknownObject(" + ParseTreeUtils.getNamespaceNameString(objectFieldTypeNode.getNamespaceNameNode()) + ")");
            case TypeFieldTypeNode typeFieldTypeNode -> stringBuilder.append(ParseTreeUtils.getNamespaceNameString(typeFieldTypeNode.getNamespaceNameNode()));
            case KeyFieldTypeNode keyFieldTypeNode -> stringBuilder.append(ParseTreeUtils.getNamespaceNameString(keyFieldTypeNode.getNamespaceNameNode()));
            case EnumFieldTypeNode enumFieldTypeNode -> stringBuilder.append(ParseTreeUtils.getNamespaceNameString(enumFieldTypeNode.getNamespaceNameNode()));
            case ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode -> stringBuilder.append("valueorerror<" + convertFieldTypeToString(valueOrErrorFieldTypeNode.getFieldTypeNode()) + ">");
            case SetFieldTypeNode setFieldTypeNode -> stringBuilder.append("set<" + convertFieldTypeToString(setFieldTypeNode.getFieldTypeNode()) + ">");
            case ListFieldTypeNode listFieldTypeNode -> stringBuilder.append("list<" + convertFieldTypeToString(listFieldTypeNode.getFieldTypeNode()) + ">");
            case MapFieldTypeNode mapFieldTypeNode -> stringBuilder.append("map<" + convertFieldTypeToString(mapFieldTypeNode.getKeyFieldTypeNode()) + ", " + convertFieldTypeToString(mapFieldTypeNode.getValueFieldTypeNode()) + ">");
            default -> throw new IllegalStateException("Unexpected value: " + nonArrayFieldTypeNode);
        }
        return stringBuilder.toString();
    }

}