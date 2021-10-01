package com.kdsc.protogen.codegeneration;

import java.io.File;

//TODO:KMD Stick with my style, it works
public class CodeGeneratorContext {

    private final String javaOutputDirectory;
    private final String protoOutputDirectory;

    public CodeGeneratorContext(final String javaOutputDirectory, final String protoOutputDirectory) {

        //TODO:KMD Extract into private method, or put in CodeGeneratorUtils
        var normalisedJavaOutputDirectory = javaOutputDirectory.endsWith(File.separator)
            ? javaOutputDirectory
            : javaOutputDirectory + File.separator;
        var normalisedProtoOutputDirectory = protoOutputDirectory.endsWith(File.separator)
            ? protoOutputDirectory
            : protoOutputDirectory + File.separator;
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