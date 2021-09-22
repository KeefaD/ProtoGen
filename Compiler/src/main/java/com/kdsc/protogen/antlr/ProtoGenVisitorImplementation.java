package com.kdsc.protogen.antlr;

import com.kdsc.protogen.parsetree.*;
import com.kdsc.protogen.parsetree.fieldtypenodes.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Optional;
import java.util.stream.Collectors;

public class ProtoGenVisitorImplementation extends com.kdsc.protogen.antlr.ProtoGenBaseVisitor<Object> {

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
    private static final String DATETIME = "datetime";
    private static final String LOCALDATETIME = "localdatetime";
    private static final String OPEN_SQUARE_BRACKET = "[";
    private static final String CLOSE_SQUARE_BRACKET = "]";

    private final String sourceFileName;

    public ProtoGenVisitorImplementation(final String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    @Override
    public Object visitFile(final ProtoGenParser.FileContext ctx) {
        return new FileNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.protogen_type().stream().map(t -> (ProtoGenTypeNode) visit(t)).collect(Collectors.toList()),
            ctx.protogen_key().stream().map(k -> (ProtoGenKeyNode) visit(k)).collect(Collectors.toList()),
            ctx.protogen_enum().stream().map(e -> (ProtoGenEnumNode) visit(e)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitProtogen_type(final ProtoGenParser.Protogen_typeContext ctx) {
        return new ProtoGenTypeNode(
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
    public Object visitProtogen_key(final ProtoGenParser.Protogen_keyContext ctx) {
        return new ProtoGenKeyNode(
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
    public Object visitProtogen_enum(final ProtoGenParser.Protogen_enumContext ctx) {
        return new ProtoGenEnumNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (NamespaceNameNode) visit(ctx.namespace_name()),
            ctx.enum_versions() == null ? Optional.empty() : Optional.of((EnumVersionsNode) visit(ctx.enum_versions())),
            ctx.enum_cases() == null ? Optional.empty() : Optional.of((EnumCasesNode) visit(ctx.enum_cases()))
        );
    }

    @Override
    public Object visitImplements_list(final ProtoGenParser.Implements_listContext ctx) {
        return new ImplementsListNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.namespace_name_generic_parameters().stream().map(nngpwb -> (NamespaceNameGenericParametersNode) visit(nngpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitVersions(final ProtoGenParser.VersionsContext ctx) {
        return new VersionsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.version().stream().map(vn -> (VersionNode) visit(vn)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitVersion(final ProtoGenParser.VersionContext ctx) {
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
    public Object visitEnum_versions(final ProtoGenParser.Enum_versionsContext ctx) {
        return new EnumVersionsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.enum_version().stream().map(ev -> (EnumVersionNode) visit(ev)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitEnum_version(final ProtoGenParser.Enum_versionContext ctx) {
        return new EnumVersionNode(
                sourceFileName,
                ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                (VersionNumberNode) visit(ctx.version_number()),
                ctx.enum_cases() == null ? Optional.empty() : Optional.of((EnumCasesNode) visit(ctx.enum_cases()))
        );
    }

    @Override
    public Object visitEnum_cases(final ProtoGenParser.Enum_casesContext ctx) {
        return new EnumCasesNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.enum_name().stream().map(enn -> (EnumNameNode) visit(enn)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitNamespace_name_generic_parameters_with_bounds(final ProtoGenParser.Namespace_name_generic_parameters_with_boundsContext ctx) {
        return new NamespaceNameGenericParametersWithBoundsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (NamespaceNameNode) visit(ctx.namespace_name()),
            ctx.generic_parameters_with_bounds() == null ? Optional.empty() : Optional.of((GenericParametersWithBoundsNode) visit(ctx.generic_parameters_with_bounds()))
        );
    }

    @Override
    public Object visitNamespace_name_generic_parameters(final ProtoGenParser.Namespace_name_generic_parametersContext ctx) {
        return new NamespaceNameGenericParametersNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (NamespaceNameNode) visit(ctx.namespace_name()),
            ctx.generic_parameters() == null ? Optional.empty() : Optional.of((GenericParametersNode) visit(ctx.generic_parameters()))
        );
    }

    @Override
    public Object visitNamespace_name(final ProtoGenParser.Namespace_nameContext ctx) {
        return new NamespaceNameNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.namespace().stream().map(nn -> (NamespaceNode) visit(nn)).collect(Collectors.toList()),
            (NameNode)visit(ctx.name())
        );
    }

    @Override
    public Object visitGeneric_parameters_with_bounds(final ProtoGenParser.Generic_parameters_with_boundsContext ctx) {
        return new GenericParametersWithBoundsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.generic_parameter_with_bounds().stream().map(gpwb -> (GenericParameterWithBoundsNode) visit(gpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitGeneric_parameters(final ProtoGenParser.Generic_parametersContext ctx) {
        return new GenericParametersNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.field_type().stream().map(gpwb -> (FieldTypeNode) visit(gpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitFields(final ProtoGenParser.FieldsContext ctx) {
        return new FieldsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.field().stream().map(vn -> (FieldNode) visit(vn)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitField(final ProtoGenParser.FieldContext ctx) {
        return new FieldNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (FieldNameNode) visit(ctx.field_name()),
            (FieldTypeNode) visit(ctx.field_type())
        );
    }

    @Override
    public Object visitField_type(final ProtoGenParser.Field_typeContext ctx) {
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
    public Object visitNon_array_field_type(final ProtoGenParser.Non_array_field_typeContext ctx) {

        if(ctx.map() != null) {
            return visit(ctx.map());
        } else if(ctx.set() != null) {
            return visit(ctx.set());
        } else if(ctx.value_or_error() != null) {
            return visit(ctx.value_or_error());
        } else if(ctx.object_field_type() != null) {
            return visit(ctx.object_field_type());
        } else if(ctx.generic_object_field_type() != null) {
            return visit(ctx.generic_object_field_type());
        }

        switch (ctx.children.get(0).getText()) {
            case DOUBLE -> {
                return new DoubleFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            }
            case FLOAT -> {
                return new FloatFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            }
            case INT_32 -> {
                return new Int32FieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            }
            case INT_64 -> {
                return new Int64FieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            }
            case BOOL -> {
                return new BoolFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            }
            case STRING -> {
                return new StringFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            }
            case BYTES -> {
                return new BytesFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            }
            case DECIMAL -> {
                return new DecimalFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            }
            case DATE -> {
                return new DateFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            }
            case DATETIME -> {
                return new DatetimeFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            }
            case LOCALDATETIME -> {
                return new LocalDatetimeFieldTypeNode(
                    sourceFileName,
                    ctx.getStart().getLine(),
                    ctx.getStart().getCharPositionInLine()
                );
            }
            default -> throw new RuntimeException("Unexpected case " + ctx.children.get(0).getText());
        }
    }

    @Override
    public Object visitMap(final ProtoGenParser.MapContext ctx) {
        return new MapFieldTypeNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (FieldTypeNode) visit(ctx.field_type(0)),
            (FieldTypeNode) visit(ctx.field_type(1))
        );
    }

    @Override
    public Object visitSet(ProtoGenParser.SetContext ctx) {
        return new SetFieldTypeNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (FieldTypeNode) visit(ctx.field_type())
        );
    }

    @Override
    public Object visitValue_or_error(final ProtoGenParser.Value_or_errorContext ctx) {
        return new ValueOrErrorFieldTypeNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (FieldTypeNode) visit(ctx.field_type())
        );
    }

    @Override
    public Object visitArray_field_type(final ProtoGenParser.Array_field_typeContext ctx) {

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
    public Object visitObject_field_type(final ProtoGenParser.Object_field_typeContext ctx) {
        return new ObjectFieldTypeNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (NamespaceNameGenericParametersNode) visit(ctx.namespace_name_generic_parameters())
        );
    }

    @Override
    public Object visitGeneric_object_field_type(final ProtoGenParser.Generic_object_field_typeContext ctx) {
        return new GenericObjectFieldTypeNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (GenericParameterNode) visit(ctx.generic_parameter())
        );
    }

    @Override
    public Object visitGeneric_parameter_with_bounds(final ProtoGenParser.Generic_parameter_with_boundsContext ctx) {
        return new GenericParameterWithBoundsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText(),
            ctx.namespace_name_generic_parameters().stream().map(nngpwb -> (NamespaceNameGenericParametersNode) visit(nngpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitGeneric_parameter(final ProtoGenParser.Generic_parameterContext ctx) {
        return new GenericParameterNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

    @Override
    public Object visitNamespace(final ProtoGenParser.NamespaceContext ctx) {
        return new NamespaceNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

    @Override
    public Object visitName(final ProtoGenParser.NameContext ctx) {
        return new NameNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

    @Override
    public Object visitField_name(final ProtoGenParser.Field_nameContext ctx) {
        return new FieldNameNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

    @Override
    public Object visitVersion_number(final ProtoGenParser.Version_numberContext ctx) {
        return new VersionNumberNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            Long.parseLong(ctx.getText())
        );
    }

    @Override
    public Object visitEnum_name(final ProtoGenParser.Enum_nameContext ctx) {
        return new EnumNameNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

}
