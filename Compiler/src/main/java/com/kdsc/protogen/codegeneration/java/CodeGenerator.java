package com.kdsc.protogen.codegeneration.java;

import com.kdsc.protogen.codegeneration.CodeGeneratorContext;
import com.kdsc.protogen.codegeneration.utils.CodeGenerateUtils;
import com.kdsc.protogen.filegenerationtree.FileNode;
import com.kdsc.protogen.filegenerationtree.java.ClassFileNode;
import com.kdsc.protogen.filegenerationtree.java.EnumCaseNode;
import com.kdsc.protogen.filegenerationtree.java.EnumFileNode;
import com.kdsc.protogen.filegenerationtree.java.JavaFileNode;

import java.util.List;
import java.util.stream.Collectors;

//TODO:KMD This is a total mess at the moment
public class CodeGenerator implements com.kdsc.protogen.codegeneration.CodeGenerator {

    public static final String ENUM_TEMPLATE_CLASSPATH = "/templates/java/Enum.template";
    public static final String TYPE_TEMPLATE_CLASSPATH = "/templates/java/Type.template";

    @Override
    public void generate(final CodeGeneratorContext codeGeneratorContext, final List<FileNode> fileNodes) {
        fileNodes
            .stream()
            .filter(fn -> fn instanceof JavaFileNode)
            .forEach(
                fn -> {
                    switch (fn) {
                        case EnumFileNode enumFileNode -> generateEnumNode(codeGeneratorContext, enumFileNode);
                        case ClassFileNode typeNode -> generateTypeNode(codeGeneratorContext, typeNode);
                        default -> throw new IllegalStateException("Unexpected type: " + fn.getClass().getName());
                    }
                }
            );
    }

    private void generateEnumNode(final CodeGeneratorContext codeGeneratorContext, final EnumFileNode enumFileNode) {
        System.out.println("Writing " + codeGeneratorContext.getJavaOutputDirectory() + enumFileNode.getPathAndFileName());
        var output = CodeGenerateUtils.readTemplateFromClasspath(ENUM_TEMPLATE_CLASSPATH);
        output = CodeGenerateUtils.replace(output, "[NAMESPACE]", enumFileNode.getNamespace());
        output = CodeGenerateUtils.replaceAndCollapseTwo(output, "[IMPORTS]", "");
        output = CodeGenerateUtils.replace(output, "[ENUM_NAME]", enumFileNode.getName());
        output = CodeGenerateUtils.replaceAndCollapse(output, "[ENUM_CASES]", generateEnumCases(codeGeneratorContext, enumFileNode.getEnumCaseNodes()));
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getJavaOutputDirectory() + enumFileNode.getPathAndFileName(), output);
    }

    private void generateTypeNode(final CodeGeneratorContext codeGeneratorContext, final ClassFileNode classFileNode) {
        System.out.println("Writing " + codeGeneratorContext.getJavaOutputDirectory() + classFileNode.getPathAndFileName());
        var output = CodeGenerateUtils.readTemplateFromClasspath(TYPE_TEMPLATE_CLASSPATH);
        output = CodeGenerateUtils.replace(output, "[PACKAGE_NAME]", classFileNode.getPackageName());
        output = CodeGenerateUtils.replaceAndCollapseTwo(output, "[IMPORTS]", "");
        output = CodeGenerateUtils.replace(output, "[TYPE_NAME]", classFileNode.getName());
        output = CodeGenerateUtils.replaceAndCollapse(output, "[CONSTRUCTOR]", "");
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getJavaOutputDirectory() + classFileNode.getPathAndFileName(), output);
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