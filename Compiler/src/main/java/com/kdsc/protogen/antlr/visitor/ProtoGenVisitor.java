package com.kdsc.protogen.antlr.visitor;

import com.kdsc.protogen.antlr.generated.ProtoGenParser;
import com.kdsc.protogen.parsetreenodes.*;
import com.kdsc.protogen.parsetreenodes.fieldtypenodes.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Optional;
import java.util.stream.Collectors;

public final class ProtoGenVisitor extends com.kdsc.protogen.antlr.generated.ProtoGenBaseVisitor<Object> {

    private static final String INTERFACE = "interface";
    private static final String OPTIONAL = "optional";
    private static final String DOUBLE = "double";
    private static final String FLOAT = "float";
    private static final String INT_32 = "int32";
    private static final String INT_64 = "int64";
    private static final String BOOL = "bool";
    private static final String STRING = "string";
    private static final String BYTES = "bytes";
    private static final String DECIMAL = "decimal";
    private static final String DATE = "date";
    private static final String DATE_TIME = "datetime";
    private static final String LOCAL_DATE = "localdate";
    private static final String LOCAL_DATE_TIME = "localdatetime";
    private static final String OPEN_SQUARE_BRACKET = "[";
    private static final String CLOSE_SQUARE_BRACKET = "]";

    private final String sourceFileName;

    public ProtoGenVisitor(final String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    @Override
    public FileNode visitFile(final ProtoGenParser.FileContext ctx) {
        return new FileNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.protogen_type().stream().map(t -> (TypeNode) visit(t)).collect(Collectors.toList()),
            ctx.protogen_key().stream().map(k -> (KeyNode) visit(k)).collect(Collectors.toList()),
            ctx.protogen_enum().stream().map(e -> (EnumNode) visit(e)).collect(Collectors.toList())
        );
    }

    @Override
    public TypeNode visitProtogen_type(final ProtoGenParser.Protogen_typeContext ctx) {
        return new TypeNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.children.stream().map(ParseTree::getText).collect(Collectors.toList()).contains(INTERFACE),
            (NamespaceNameGenericParametersWithBoundsNode) visit(ctx.namespace_name_generic_parameters_with_bounds()),
            ctx.implements_list() == null ? Optional.empty() : Optional.of((ImplementsListNode) visit(ctx.implements_list())),
            ctx.versions() == null ? Optional.empty() : Optional.of((VersionsNode) visit(ctx.versions())),
            ctx.fields() == null ? Optional.empty() : Optional.of((FieldsNode) visit(ctx.fields()))
        );
    }

    @Override
    public KeyNode visitProtogen_key(final ProtoGenParser.Protogen_keyContext ctx) {
        return new KeyNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.children.stream().map(ParseTree::getText).collect(Collectors.toList()).contains(INTERFACE),
            (NamespaceNameGenericParametersWithBoundsNode) visit(ctx.namespace_name_generic_parameters_with_bounds()),
            ctx.implements_list() == null ? Optional.empty() : Optional.of((ImplementsListNode) visit(ctx.implements_list())),
            ctx.versions() == null ? Optional.empty() : Optional.of((VersionsNode) visit(ctx.versions())),
            ctx.fields() == null ? Optional.empty() : Optional.of((FieldsNode) visit(ctx.fields()))
        );
    }

    @Override
    public EnumNode visitProtogen_enum(final ProtoGenParser.Protogen_enumContext ctx) {
        return new EnumNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (NamespaceNameNode) visit(ctx.namespace_name()),
            ctx.enum_versions() == null ? Optional.empty() : Optional.of((EnumVersionsNode) visit(ctx.enum_versions())),
            ctx.enum_cases() == null ? Optional.empty() : Optional.of((EnumCasesNode) visit(ctx.enum_cases()))
        );
    }

    @Override
    public ImplementsListNode visitImplements_list(final ProtoGenParser.Implements_listContext ctx) {
        return new ImplementsListNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.namespace_name_generic_parameters().stream().map(nngpwb -> (NamespaceNameGenericParametersNode) visit(nngpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public VersionsNode visitVersions(final ProtoGenParser.VersionsContext ctx) {
        return new VersionsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.version().stream().map(vn -> (VersionNode) visit(vn)).collect(Collectors.toList())
        );
    }

    @Override
    public VersionNode visitVersion(final ProtoGenParser.VersionContext ctx) {
        return new VersionNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (VersionNumberNode) visit(ctx.version_number()),
            ctx.generic_parameters_with_bounds() == null ? Optional.empty() : Optional.of((GenericParametersWithBoundsNode) visit(ctx.generic_parameters_with_bounds())),
            ctx.implements_list() == null ? Optional.empty() : Optional.of((ImplementsListNode) visit(ctx.implements_list())),
            ctx.fields() == null ? Optional.empty() : Optional.of((FieldsNode) visit(ctx.fields()))
        );
    }

    @Override
    public EnumVersionsNode visitEnum_versions(final ProtoGenParser.Enum_versionsContext ctx) {
        return new EnumVersionsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.enum_version().stream().map(ev -> (EnumVersionNode) visit(ev)).collect(Collectors.toList())
        );
    }

    @Override
    public EnumVersionNode visitEnum_version(final ProtoGenParser.Enum_versionContext ctx) {
        return new EnumVersionNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (VersionNumberNode) visit(ctx.version_number()),
            (EnumCasesNode) visit(ctx.enum_cases())
        );
    }

    @Override
    public EnumCasesNode visitEnum_cases(final ProtoGenParser.Enum_casesContext ctx) {
        return new EnumCasesNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.enum_name().stream().map(enn -> (EnumNameNode) visit(enn)).collect(Collectors.toList())
        );
    }

    @Override
    public NamespaceNameGenericParametersWithBoundsNode visitNamespace_name_generic_parameters_with_bounds(final ProtoGenParser.Namespace_name_generic_parameters_with_boundsContext ctx) {
        return new NamespaceNameGenericParametersWithBoundsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (NamespaceNameNode) visit(ctx.namespace_name()),
            ctx.generic_parameters_with_bounds() == null ? Optional.empty() : Optional.of((GenericParametersWithBoundsNode) visit(ctx.generic_parameters_with_bounds()))
        );
    }

    @Override
    public NamespaceNameGenericParametersNode visitNamespace_name_generic_parameters(final ProtoGenParser.Namespace_name_generic_parametersContext ctx) {
        return new NamespaceNameGenericParametersNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (NamespaceNameNode) visit(ctx.namespace_name()),
            ctx.generic_parameters() == null ? Optional.empty() : Optional.of((GenericParametersNode) visit(ctx.generic_parameters()))
        );
    }

    @Override
    public NamespaceNameNode visitNamespace_name(final ProtoGenParser.Namespace_nameContext ctx) {
        return new NamespaceNameNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.namespace().stream().map(nn -> (NamespaceNode) visit(nn)).collect(Collectors.toList()),
            (NameNode)visit(ctx.name())
        );
    }

    @Override
    public GenericParametersWithBoundsNode visitGeneric_parameters_with_bounds(final ProtoGenParser.Generic_parameters_with_boundsContext ctx) {
        return new GenericParametersWithBoundsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.generic_parameter_with_bounds().stream().map(gpwb -> (GenericParameterWithBoundsNode) visit(gpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public GenericParametersNode visitGeneric_parameters(final ProtoGenParser.Generic_parametersContext ctx) {
        return new GenericParametersNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.field_type().stream().map(gpwb -> (FieldTypeNode) visit(gpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public FieldsNode visitFields(final ProtoGenParser.FieldsContext ctx) {
        return new FieldsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.field().stream().map(vn -> (FieldNode) visit(vn)).collect(Collectors.toList())
        );
    }

    @Override
    public FieldNode visitField(final ProtoGenParser.FieldContext ctx) {
        return new FieldNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (FieldNameNode) visit(ctx.field_name()),
            (FieldTypeNode) visit(ctx.field_type())
        );
    }

    @Override
    public FieldTypeNode visitField_type(final ProtoGenParser.Field_typeContext ctx) {
        return new FieldTypeNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.children.stream().map(ParseTree::getText).collect(Collectors.toList()).contains(OPTIONAL),
            ctx.array_field_type() == null ? Optional.empty() : Optional.of((ArrayFieldTypeNode) visit(ctx.array_field_type())),
            ctx.non_array_field_type() == null ? Optional.empty() : Optional.of((NonArrayFieldTypeNode) visit(ctx.non_array_field_type()))
        );
    }

    @Override
    public NonArrayFieldTypeNode visitNon_array_field_type(final ProtoGenParser.Non_array_field_typeContext ctx) {

        if(ctx.map() != null) {
            return (NonArrayFieldTypeNode) visit(ctx.map());
        } else if(ctx.set() != null) {
            return (NonArrayFieldTypeNode)visit(ctx.set());
        } else if(ctx.value_or_error() != null) {
            return (NonArrayFieldTypeNode)visit(ctx.value_or_error());
        } else if(ctx.object_field_type() != null) {
            return (NonArrayFieldTypeNode)visit(ctx.object_field_type());
        } else if(ctx.generic_object_field_type() != null) {
            return (NonArrayFieldTypeNode)visit(ctx.generic_object_field_type());
        }

        return switch (ctx.children.get(0).getText()) {
            case DOUBLE ->
                new DoubleFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            case FLOAT ->
                new FloatFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            case INT_32 ->
                new Int32FieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            case INT_64 ->
                new Int64FieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            case BOOL ->
                new BoolFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            case STRING ->
                new StringFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            case BYTES ->
                new BytesFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            case DECIMAL ->
                new DecimalFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            case DATE ->
                new DateFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            case DATE_TIME ->
                new DateTimeFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            case LOCAL_DATE ->
                new LocalDateFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            case LOCAL_DATE_TIME ->
                new LocalDateTimeFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            default -> throw new RuntimeException("Unexpected case " + ctx.children.get(0).getText());
        };
    }

    @Override
    public MapFieldTypeNode visitMap(final ProtoGenParser.MapContext ctx) {
        return new MapFieldTypeNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (FieldTypeNode) visit(ctx.field_type(0)),
            (FieldTypeNode) visit(ctx.field_type(1))
        );
    }

    @Override
    public SetFieldTypeNode visitSet(ProtoGenParser.SetContext ctx) {
        return new SetFieldTypeNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (FieldTypeNode) visit(ctx.field_type())
        );
    }

    @Override
    public ValueOrErrorFieldTypeNode visitValue_or_error(final ProtoGenParser.Value_or_errorContext ctx) {
        return new ValueOrErrorFieldTypeNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (FieldTypeNode) visit(ctx.field_type())
        );
    }

    @Override
    public ArrayFieldTypeNode visitArray_field_type(final ProtoGenParser.Array_field_typeContext ctx) {

        var openSquareBracketCount = ctx
            .children
            .stream()
            .map(ParseTree::getText)
            .filter(t -> t != null && t.equals(OPEN_SQUARE_BRACKET))
            .count();

        var closeSquareBracketCount = ctx
            .children
            .stream()
            .map(ParseTree::getText)
            .filter(t -> t != null && t.equals(CLOSE_SQUARE_BRACKET))
            .count();

        if(openSquareBracketCount != closeSquareBracketCount) {
            throw new RuntimeException("Opening and closing square brackets count doesn't match for array, opening count "  + openSquareBracketCount + " closing count " + closeSquareBracketCount);
        }

        return new ArrayFieldTypeNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (NonArrayFieldTypeNode) visit(ctx.non_array_field_type()),
            openSquareBracketCount
        );
    }

    @Override
    public ObjectFieldTypeNode visitObject_field_type(final ProtoGenParser.Object_field_typeContext ctx) {
        return new ObjectFieldTypeNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (NamespaceNameGenericParametersNode) visit(ctx.namespace_name_generic_parameters())
        );
    }

    @Override
    public GenericObjectFieldTypeNode visitGeneric_object_field_type(final ProtoGenParser.Generic_object_field_typeContext ctx) {
        return new GenericObjectFieldTypeNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (GenericParameterNode) visit(ctx.generic_parameter())
        );
    }

    @Override
    public GenericParameterWithBoundsNode visitGeneric_parameter_with_bounds(final ProtoGenParser.Generic_parameter_with_boundsContext ctx) {
        return new GenericParameterWithBoundsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText(),
            ctx.namespace_name_generic_parameters().stream().map(nngpwb -> (NamespaceNameGenericParametersNode) visit(nngpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public GenericParameterNode visitGeneric_parameter(final ProtoGenParser.Generic_parameterContext ctx) {
        return new GenericParameterNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

    @Override
    public NamespaceNode visitNamespace(final ProtoGenParser.NamespaceContext ctx) {
        return new NamespaceNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

    @Override
    public NameNode visitName(final ProtoGenParser.NameContext ctx) {
        return new NameNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

    @Override
    public FieldNameNode visitField_name(final ProtoGenParser.Field_nameContext ctx) {
        return new FieldNameNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

    @Override
    public VersionNumberNode visitVersion_number(final ProtoGenParser.Version_numberContext ctx) {
        return new VersionNumberNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            Long.parseLong(ctx.getText())
        );
    }

    @Override
    public EnumNameNode visitEnum_name(final ProtoGenParser.Enum_nameContext ctx) {
        return new EnumNameNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

}