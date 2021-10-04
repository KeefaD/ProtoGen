package com.kdsc.protogen.transform;

import com.kdsc.protogen.parsetree.ProtoGenEnumNode;
import com.kdsc.protogen.parsetree.ProtoGenKeyNode;
import com.kdsc.protogen.parsetree.ProtoGenTypeNode;

import java.util.Map;

public class TransformerContext {

    //TODO:KMD These are a bit weird and inconsistent
    public static final String protoBaseJavaNamespace = "proto";
    public static final String protoFileExtension = ".proto";
    public static final String javaBasePackage = "";
    public static final String javaFileExtension = ".java";

    private final String baseNamespace;
    private final Map<String, ProtoGenEnumNode> enums;
    private final Map<String, ProtoGenTypeNode> typeInterfaces;
    private final Map<String, ProtoGenTypeNode> types;
    private final Map<String, ProtoGenKeyNode> keyInterfaces;
    private final Map<String, ProtoGenKeyNode> keys;


    public TransformerContext(
        final String baseNamespace,
        Map<String, ProtoGenEnumNode> enums,
        Map<String, ProtoGenTypeNode> typeInterfaces,
        Map<String, ProtoGenTypeNode> types,
        Map<String, ProtoGenKeyNode> keyInterfaces,
        Map<String, ProtoGenKeyNode> keys
    ) {
        this.baseNamespace = baseNamespace;
        //TODO:KMD Pre conditions
        this.enums = enums;
        this.typeInterfaces = typeInterfaces;
        this.types = types;
        this.keyInterfaces = keyInterfaces;
        this.keys = keys;
    }

    public Map<String, ProtoGenEnumNode> getEnums() {
        return enums;
    }

    public Map<String, ProtoGenTypeNode> getTypeInterfaces() {
        return typeInterfaces;
    }

    public Map<String, ProtoGenTypeNode> getTypes() {
        return types;
    }

    public Map<String, ProtoGenKeyNode> getKeyInterfaces() {
        return keyInterfaces;
    }

    public Map<String, ProtoGenKeyNode> getKeys() {
        return keys;
    }

    public String getBaseNamespace() {
        return baseNamespace;
    }

}