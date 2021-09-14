package com.kdsc.protogen.antlr;

import com.kdsc.protogen.parsetree.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Optional;
import java.util.stream.Collectors;

public class ProtoGenVisitorImplementation extends com.kdsc.protogen.antlr.ProtoGenBaseVisitor<Object> {

    private static final String INTERFACE = "interface";

    private final String sourceFileName;

    public ProtoGenVisitorImplementation(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    @Override
    public Object visitFile(ProtoGenParser.FileContext ctx) {
        return new FileNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.protogen_type().stream().map(t -> (ProtoGenTypeNode) visit(t)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitEnum_cases(ProtoGenParser.Enum_casesContext ctx) {
        return super.visitEnum_cases(ctx);
    }

    @Override
    public Object visitEnum_name(ProtoGenParser.Enum_nameContext ctx) {
        return super.visitEnum_name(ctx);
    }

    @Override
    public Object visitProtogen_enum(ProtoGenParser.Protogen_enumContext ctx) {
        return super.visitProtogen_enum(ctx);
    }

    @Override
    public Object visitProtogen_type(ProtoGenParser.Protogen_typeContext ctx) {
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
    public Object visitNamespace_name(ProtoGenParser.Namespace_nameContext ctx) {
        return new NamespaceNameNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.namespace().stream().map(nn -> (NamespaceNode) visit(nn)).collect(Collectors.toList()),
            (NameNode)visit(ctx.name())
        );
    }

    @Override
    public Object visitField_name(ProtoGenParser.Field_nameContext ctx) {
        return super.visitField_name(ctx);
    }

    @Override
    public Object visitNon_array_field_type(ProtoGenParser.Non_array_field_typeContext ctx) {
        return super.visitNon_array_field_type(ctx);
    }

    @Override
    public Object visitName(ProtoGenParser.NameContext ctx) {
        return new NameNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

    @Override
    public Object visitNamespace(ProtoGenParser.NamespaceContext ctx) {
        return new NamespaceNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

    @Override
    public Object visitField_type(ProtoGenParser.Field_typeContext ctx) {
        return super.visitField_type(ctx);
    }

    @Override
    public Object visitImplements_list(ProtoGenParser.Implements_listContext ctx) {
        return new ImplementsListNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.namespace_name_generic_parameters_without_bounds().stream().map(nngpwb -> (NamespaceNameGenericParametersWithoutBoundsNode) visit(nngpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitEnum_version(ProtoGenParser.Enum_versionContext ctx) {
        return super.visitEnum_version(ctx);
    }

    @Override
    public Object visitEnum_versions(ProtoGenParser.Enum_versionsContext ctx) {
        return super.visitEnum_versions(ctx);
    }

    @Override
    public Object visitVersion_number(ProtoGenParser.Version_numberContext ctx) {
        return new VersionNumberNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            Long.parseLong(ctx.getText())
        );
    }

    @Override
    public Object visitGeneric_parameter_with_bounds(ProtoGenParser.Generic_parameter_with_boundsContext ctx) {
        return new GenericParameterWithBoundsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText(),
            ctx.namespace_name_generic_parameters_without_bounds().stream().map(nngpwb -> (NamespaceNameGenericParametersWithoutBoundsNode) visit(nngpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitGeneric_parameter_without_bounds(ProtoGenParser.Generic_parameter_without_boundsContext ctx) {
        return new GenericParameterWithoutBoundsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

    @Override
    public Object visitGeneric_parameters_with_bounds(ProtoGenParser.Generic_parameters_with_boundsContext ctx) {
        return new GenericParametersWithBoundsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.generic_parameter_with_bounds().stream().map(gpwb -> (GenericParameterWithBoundsNode) visit(gpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitGeneric_parameters_without_bounds(ProtoGenParser.Generic_parameters_without_boundsContext ctx) {
        return new GenericParametersWithoutBoundsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.generic_parameter_without_bounds().stream().map(gpwb -> (GenericParameterWithoutBoundsNode) visit(gpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitNamespace_name_generic_parameters_with_bounds(ProtoGenParser.Namespace_name_generic_parameters_with_boundsContext ctx) {
        return new NamespaceNameGenericParametersWithBoundsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (NamespaceNameNode) visit(ctx.namespace_name()),
            ctx.generic_parameters_with_bounds() == null ? Optional.empty() : Optional.of((GenericParametersWithBoundsNode) visit(ctx.generic_parameters_with_bounds()))
        );
    }

    @Override
    public Object visitNamespace_name_generic_parameters_without_bounds(ProtoGenParser.Namespace_name_generic_parameters_without_boundsContext ctx) {
        return new NamespaceNameGenericParametersWithoutBoundsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (NamespaceNameNode) visit(ctx.namespace_name()),
            ctx.generic_parameters_without_bounds() == null ? Optional.empty() : Optional.of((GenericParametersWithoutBoundsNode) visit(ctx.generic_parameters_without_bounds()))
        );
    }

    @Override
    public Object visitFields(ProtoGenParser.FieldsContext ctx) {
        return new FieldsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.field() == null ? Optional.empty() : Optional.of(ctx.field().stream().map(vn -> (FieldNode) visit(vn)).collect(Collectors.toList()))
        );
    }

    @Override
    public Object visitField(ProtoGenParser.FieldContext ctx) {
        return new FieldNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine()
        );
    }

    @Override
    public Object visitProtogen_key(ProtoGenParser.Protogen_keyContext ctx) {
        return super.visitProtogen_key(ctx);
    }

    @Override
    public Object visitVersions(ProtoGenParser.VersionsContext ctx) {
        return new VersionsNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.version() == null ? Optional.empty() : Optional.of(ctx.version().stream().map(vn -> (VersionNode) visit(vn)).collect(Collectors.toList()))
        );
    }

    @Override
    public Object visitVersion(ProtoGenParser.VersionContext ctx) {
        return new VersionNode(
            sourceFileName,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (VersionNumberNode) visit(ctx.version_number()),
            ctx.fields() == null ? Optional.empty() : Optional.of((FieldsNode) visit(ctx.fields()))
        );
    }

    @Override
    public Object visitArray(ProtoGenParser.ArrayContext ctx) {
        return super.visitArray(ctx);
    }

    @Override
    public Object visitMap(ProtoGenParser.MapContext ctx) {
        return super.visitMap(ctx);
    }

    @Override
    public Object visitSet(ProtoGenParser.SetContext ctx) {
        return super.visitSet(ctx);
    }

    @Override
    public Object visitValue_or_error(ProtoGenParser.Value_or_errorContext ctx) {
        return super.visitValue_or_error(ctx);
    }
}
