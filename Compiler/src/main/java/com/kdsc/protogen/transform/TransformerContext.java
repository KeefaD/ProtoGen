package com.kdsc.protogen.transform;

public class TransformerContext {

    //TODO:KMD These are a bit weird and inconsistent
    public static final String protoBaseJavaNamespace = "proto";
    public static final String protoFileExtension = ".proto";
    public static final String javaBasePackage = "";
    public static final String javaFileExtension = ".java";

    private final String baseNamespace;

    public TransformerContext(final String baseNamespace) {
        this.baseNamespace = baseNamespace;
    }

    public String getBaseNamespace() {
        return baseNamespace;
    }

}