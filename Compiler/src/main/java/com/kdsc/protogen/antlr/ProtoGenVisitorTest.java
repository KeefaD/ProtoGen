package com.kdsc.protogen.antlr;

public class ProtoGenVisitorTest extends com.kdsc.protogen.antlr.ProtoGenBaseVisitor<Object> {

    @Override
    public Object visitFile(ProtoGenParser.FileContext ctx) {

        System.out.println("Visiting File");

        return super.visitFile(ctx);
    }
}
