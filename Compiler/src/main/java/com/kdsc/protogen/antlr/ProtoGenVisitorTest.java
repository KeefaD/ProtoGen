package com.kdsc.protogen.antlr;

public class ProtoGenVisitorTest extends com.kdsc.protogen.antlr.ProtoGenBaseVisitor<Object> {

    @Override
    public Object visitFile(ProtoGenParser.FileContext ctx) {
        System.out.println("Visiting file");
        return super.visitFile(ctx);
    }

    @Override
    public Object visitEnum_cases(ProtoGenParser.Enum_casesContext ctx) {
        System.out.println("Visiting enum_cases");
        return super.visitEnum_cases(ctx);
    }

    @Override
    public Object visitEnum_name(ProtoGenParser.Enum_nameContext ctx) {
        System.out.println("Visiting enum_name");
        return super.visitEnum_name(ctx);
    }

    @Override
    public Object visitProtogen_enum(ProtoGenParser.Protogen_enumContext ctx) {
        System.out.println("Visiting protogen_enum");
        return super.visitProtogen_enum(ctx);
    }

    @Override
    public Object visitProtogen_type(ProtoGenParser.Protogen_typeContext ctx) {
        System.out.println("Visiting protogen_type");
        return super.visitProtogen_type(ctx);
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
    public Object visitField_name(ProtoGenParser.Field_nameContext ctx) {
        System.out.println("Visiting field_name");
        return super.visitField_name(ctx);
    }

    @Override
    public Object visitNon_array_field_type(ProtoGenParser.Non_array_field_typeContext ctx) {
        System.out.println("Visiting non_array_field_type");
        return super.visitNon_array_field_type(ctx);
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
    public Object visitField_type(ProtoGenParser.Field_typeContext ctx) {
        System.out.println("Visiting field_type");
        return super.visitField_type(ctx);
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

    @Override
    public Object visitMap_field_type(ProtoGenParser.Map_field_typeContext ctx) {
        System.out.println("Visiting map_field_type");
        return super.visitMap_field_type(ctx);
    }

    @Override
    public Object visitSet_field_type(ProtoGenParser.Set_field_typeContext ctx) {
        System.out.println("Visiting set_field_type");
        return super.visitSet_field_type(ctx);
    }

    @Override
    public Object visitArray_field_type(ProtoGenParser.Array_field_typeContext ctx) {
        System.out.println("Visiting array_field_type");
        return super.visitArray_field_type(ctx);
    }

    @Override
    public Object visitEnum_version(ProtoGenParser.Enum_versionContext ctx) {
        System.out.println("Visiting enum_version");
        return super.visitEnum_version(ctx);
    }

    @Override
    public Object visitEnum_versions(ProtoGenParser.Enum_versionsContext ctx) {
        System.out.println("Visiting enum_versions");
        return super.visitEnum_versions(ctx);
    }

    @Override
    public Object visitVersion_number(ProtoGenParser.Version_numberContext ctx) {
        System.out.println("Visiting version_number");
        return super.visitVersion_number(ctx);
    }

}
