package com.kdsc.protogen.codegeneration;

import static com.kdsc.protogen.codegeneration.utils.CodeGenerateUtils.getNormalisedDirectoryPath;

public final class CodeGeneratorContext {

    private final String javaOutputDirectory;
    private final String protoOutputDirectory;

    public CodeGeneratorContext(final String javaOutputDirectory, final String protoOutputDirectory) {
        //TODO:KMD Pre-conditions
        String normalisedJavaOutputDirectory = getNormalisedDirectoryPath(javaOutputDirectory);
        String normalisedProtoOutputDirectory = getNormalisedDirectoryPath(protoOutputDirectory);
        this.javaOutputDirectory = normalisedJavaOutputDirectory;
        this.protoOutputDirectory = normalisedProtoOutputDirectory;
    }

    public String getJavaOutputDirectory() {
        return javaOutputDirectory;
    }

    public String getProtoOutputDirectory() {
        return protoOutputDirectory;
    }
}