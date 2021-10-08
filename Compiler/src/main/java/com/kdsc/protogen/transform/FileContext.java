package com.kdsc.protogen.transform;

import java.util.HashSet;
import java.util.Set;

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