package com.kdsc.protogen.codegeneration.java;

import com.kdsc.protogen.codegeneration.CodeGeneratorContext;
import com.kdsc.protogen.codegeneration.utils.CodeGenerateUtils;
import com.kdsc.protogen.filegenerationtree.FileNode;
import com.kdsc.protogen.filegenerationtree.java.EnumCaseNode;
import com.kdsc.protogen.filegenerationtree.java.EnumFileNode;
import com.kdsc.protogen.filegenerationtree.java.JavaFileNode;

import java.util.List;
import java.util.stream.Collectors;

public class CodeGenerator implements com.kdsc.protogen.codegeneration.CodeGenerator {

    public static final String ENUM_TEMPLATE_CLASSPATH = "/templates/java/Enum.template";
    public static final String MESSAGE_TEMPLATE_CLASSPATH = "/templates/java/Type.template";

    @Override
    public void generate(final CodeGeneratorContext codeGeneratorContext, final List<FileNode> fileNodes) {
        fileNodes
            .stream()
            .filter(fn -> fn instanceof JavaFileNode)
            .forEach(
                fn -> {
                    switch (fn) {
                        case EnumFileNode enumFileNode -> generateEnumNode(codeGeneratorContext, enumFileNode);
//                        case ProtoGenTypeNode typeNode -> generateTypeNode(codeGeneratorContext, typeNode);
                        default -> throw new IllegalStateException("Unexpected type: " + fn.getClass().getName());
                    }
                }
            );
    }

    private void generateEnumNode(final CodeGeneratorContext codeGeneratorContext, final EnumFileNode enumFileNode) {
        System.out.println("Writing " + codeGeneratorContext.getJavaOutputDirectory() + enumFileNode.getPathAndFileName());
        var template = CodeGenerateUtils.readTemplateFromClasspath(ENUM_TEMPLATE_CLASSPATH);
        template = CodeGenerateUtils.replace(template, "[NAMESPACE]", enumFileNode.getNamespace());
        template = CodeGenerateUtils.replaceAndCollapseTwo(template, "[IMPORTS]", "");
        template = CodeGenerateUtils.replace(template, "[ENUM_NAME]", enumFileNode.getName());
        template = CodeGenerateUtils.replaceAndCollapse(template, "[ENUM_CASES]", generateEnumCases(codeGeneratorContext, enumFileNode.getEnumCaseNodes()));
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getJavaOutputDirectory() + enumFileNode.getPathAndFileName(), template);
    }

    private String generateEnumCases(final CodeGeneratorContext codeGeneratorContext, final List<EnumCaseNode> enumCaseNodes) {
        if(enumCaseNodes.size() == 0) return "";
        var stringBuilder = new StringBuilder();
        var enumCaseNodesString = enumCaseNodes
            .stream()
            .map(ecn -> "\t" + ecn.getName())
            .collect(Collectors.joining(",\n")) + "\n";
        stringBuilder.append(enumCaseNodesString);
        return stringBuilder.toString();
    }

}