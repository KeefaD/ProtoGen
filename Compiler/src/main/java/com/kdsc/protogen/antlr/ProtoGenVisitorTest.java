package com.kdsc.protogen.antlr;

public class ProtoGenVisitorTest extends com.kdsc.protogen.antlr.ProtoGenBaseVisitor<Object> {

    @Override
    public Object visitFile(ProtoGenParser.FileContext ctx) {
        System.out.println("Visiting file");
        return super.visitFile(ctx);
    }

    @Override
    public Object visitType(ProtoGenParser.TypeContext ctx) {
        System.out.println("Visiting type");
        return super.visitType(ctx);
    }

    @Override
    public Object visitNamespace_name_generic_parameters(ProtoGenParser.Namespace_name_generic_parametersContext ctx) {
        System.out.println("Visiting namespace_name_generic_parameters");
        return super.visitNamespace_name_generic_parameters(ctx);
    }

    @Override
    public Object visitType_version(ProtoGenParser.Type_versionContext ctx) {
        System.out.println("Visiting version");
        return super.visitType_version(ctx);
    }

    @Override
    public Object visitType_fields(ProtoGenParser.Type_fieldsContext ctx) {
        System.out.println("Visiting fields");
        return super.visitType_fields(ctx);
    }

    @Override
    public Object visitType_field(ProtoGenParser.Type_fieldContext ctx) {
        System.out.println("Visiting field");
        return super.visitType_field(ctx);
    }

    @Override
    public Object visitNamespace_name(ProtoGenParser.Namespace_nameContext ctx) {
        System.out.println("Visiting namespace_name");
        return super.visitNamespace_name(ctx);
    }

    @Override
    public Object visitField(ProtoGenParser.FieldContext ctx) {
        System.out.println("Visiting field");
        return super.visitField(ctx);
    }

    @Override
    public Object visitGeneric_parameters(ProtoGenParser.Generic_parametersContext ctx) {
        System.out.println("Visiting generic parameters");
        return super.visitGeneric_parameters(ctx);
    }

    @Override
    public Object visitGeneric_parameter(ProtoGenParser.Generic_parameterContext ctx) {
        System.out.println("Visiting generic parameter");
        return super.visitGeneric_parameter(ctx);
    }

    @Override
    public Object visitName(ProtoGenParser.NameContext ctx) {
        System.out.println("Visiting name");
        return super.visitName(ctx);
    }

    @Override
    public Object visitNamespace(ProtoGenParser.NamespaceContext ctx) {
        System.out.println("Visiting namespace");
        return super.visitNamespace(ctx);
    }

    @Override
    public Object visitType_field_type(ProtoGenParser.Type_field_typeContext ctx) {
        System.out.println("Visiting field_type");
        return super.visitType_field_type(ctx);
    }

    @Override
    public Object visitType_versions(ProtoGenParser.Type_versionsContext ctx) {
        System.out.println("Visiting type_versions");
        return super.visitType_versions(ctx);
    }

    @Override
    public Object visitImplements_list(ProtoGenParser.Implements_listContext ctx) {
        System.out.println("Visiting implements_list");
        return super.visitImplements_list(ctx);
    }

}
