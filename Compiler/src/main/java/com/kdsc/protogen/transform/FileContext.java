package com.kdsc.protogen.transform;

import java.util.HashSet;
import java.util.Set;

//TODO:KMD Have a think about file contexts and proto and java, we have to share them or come up with something clever because of the shared FieldTypeNodes
public class FileContext {

    private final Set<String> javaImportStatements = new HashSet<>();
    private final Set<String> protoImportStatements = new HashSet<>();

    public void addJavaImport(String importStatement) {
        javaImportStatements.add(importStatement);
    }

    public Set<String> getJavaImportStatements() {
        return javaImportStatements;
    }

    public void addProtoImport(String importStatement) {
        protoImportStatements.add(importStatement);
    }

    public Set<String> getProtoImportStatements() {
        return protoImportStatements;
    }

}