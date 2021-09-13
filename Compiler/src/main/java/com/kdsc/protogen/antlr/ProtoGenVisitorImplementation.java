package com.kdsc.protogen.antlr;

import com.kdsc.protogen.parsetree.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Optional;
import java.util.stream.Collectors;

public class ProtoGenVisitorImplementation extends com.kdsc.protogen.antlr.ProtoGenBaseVisitor<Object> {

    private static final String INTERFACE = "interface";

    @Override
    public Object visitFile(ProtoGenParser.FileContext ctx) {
        return new FileNode(
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
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.children.stream().map(ParseTree::getText).collect(Collectors.toList()).contains(INTERFACE),
            (NamespaceNameGenericParametersWithBoundsNode) visit(ctx.namespace_name_generic_parameters_with_bounds()),
            ctx.implements_list() == null ? Optional.empty() : Optional.of((ImplementsListNode) visit(ctx.implements_list()))
        );
    }

    @Override
    public Object visitNamespace_name(ProtoGenParser.Namespace_nameContext ctx) {
        return new NamespaceNameNode(
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
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

    @Override
    public Object visitNamespace(ProtoGenParser.NamespaceContext ctx) {
        return new NamespaceNode(
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
        return super.visitVersion_number(ctx);
    }

    @Override
    public Object visitGeneric_parameter_with_bounds(ProtoGenParser.Generic_parameter_with_boundsContext ctx) {
        return new GenericParameterWithBoundsNode(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText(),
            ctx.namespace_name_generic_parameters_without_bounds().stream().map(nngpwb -> (NamespaceNameGenericParametersWithoutBoundsNode) visit(nngpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitGeneric_parameter_without_bounds(ProtoGenParser.Generic_parameter_without_boundsContext ctx) {
        return new GenericParameterWithoutBoundsNode(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.IDENTIFIER().getText()
        );
    }

    @Override
    public Object visitGeneric_parameters_with_bounds(ProtoGenParser.Generic_parameters_with_boundsContext ctx) {
        return new GenericParametersWithBoundsNode(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.generic_parameter_with_bounds().stream().map(gpwb -> (GenericParameterWithBoundsNode) visit(gpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitGeneric_parameters_without_bounds(ProtoGenParser.Generic_parameters_without_boundsContext ctx) {
        return new GenericParametersWithoutBoundsNode(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            ctx.generic_parameter_without_bounds().stream().map(gpwb -> (GenericParameterWithoutBoundsNode) visit(gpwb)).collect(Collectors.toList())
        );
    }

    @Override
    public Object visitNamespace_name_generic_parameters_with_bounds(ProtoGenParser.Namespace_name_generic_parameters_with_boundsContext ctx) {
        return new NamespaceNameGenericParametersWithBoundsNode(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (NamespaceNameNode) visit(ctx.namespace_name()),
            ctx.generic_parameters_with_bounds() == null ? Optional.empty() : Optional.of((GenericParametersWithBoundsNode) visit(ctx.generic_parameters_with_bounds()))
        );
    }

    @Override
    public Object visitNamespace_name_generic_parameters_without_bounds(ProtoGenParser.Namespace_name_generic_parameters_without_boundsContext ctx) {
        return new NamespaceNameGenericParametersWithoutBoundsNode(
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine(),
            (NamespaceNameNode) visit(ctx.namespace_name()),
            ctx.generic_parameters_without_bounds() == null ? Optional.empty() : Optional.of((GenericParametersWithoutBoundsNode) visit(ctx.generic_parameters_without_bounds()))
        );
    }

    @Override
    public Object visitField(ProtoGenParser.FieldContext ctx) {
        return super.visitField(ctx);
    }

    @Override
    public Object visitFields(ProtoGenParser.FieldsContext ctx) {
        return super.visitFields(ctx);
    }

    @Override
    public Object visitProtogen_key(ProtoGenParser.Protogen_keyContext ctx) {
        return super.visitProtogen_key(ctx);
    }

    @Override
    public Object visitVersion(ProtoGenParser.VersionContext ctx) {
        return super.visitVersion(ctx);
    }

    @Override
    public Object visitVersions(ProtoGenParser.VersionsContext ctx) {
        return super.visitVersions(ctx);
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
