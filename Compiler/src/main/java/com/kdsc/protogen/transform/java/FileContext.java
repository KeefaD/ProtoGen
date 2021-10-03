package com.kdsc.protogen.transform.java;

import java.util.HashSet;
import java.util.Set;

//TODO:KMD Should we have more than one implementation of FileContext
public class FileContext implements com.kdsc.protogen.transform.FileContext {

    private final Set<String> importStatements = new HashSet<>();

    public void addImport(String importStatement) {
        importStatements.add(importStatement);
    }

    public Set<String> getImportStatements() {
        return importStatements;
    }

}