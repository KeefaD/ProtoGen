package com.kdsc.protogen.transform;

public record TransformerContext(
    String baseNamespace
) {

    //TODO:KMD These are a bit weird and inconsistent
    public static final String protoBaseJavaNamespace = "proto";
    public static final String protoFileExtension = ".proto";
    public static final String javaBasePackage = "";
    public static final String javaFileExtension = ".java";

    public String getBaseNamespace() {
        return baseNamespace;
    }

}