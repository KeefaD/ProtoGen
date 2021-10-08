package com.kdsc.protogen.codegeneration.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

//TODO:KMD This needs a close look at
public final class CodeGenerateUtils {

    public static void writeStringToPath(final String pathAndFileName, final String textToWrite) {
        var path = Paths.get(pathAndFileName);

        var parentFile = path.getParent().toFile();

        if(!parentFile.exists()) {

            //noinspection ResultOfMethodCallIgnored
            parentFile.mkdirs();
        }

        //TODO:KMD Make this nicer
        var stringToWrite = textToWrite.replace("\t", "    ");

        try {
            Files.writeString(path, stringToWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readTemplateFromClasspath(String pathToTemplate) {
        var clazz = CodeGenerateUtils.class;
        var inputStream = clazz.getResourceAsStream(pathToTemplate);
        if(inputStream == null) throw new RuntimeException("getResourceAsStream with parameter " + pathToTemplate + " returned null unexpectedly");
        try {
            var bytes = inputStream.readAllBytes();
            if(bytes == null) {
                throw new RuntimeException("Got null for inputStream.readAllBytes unexpectedly");
            }
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    public static String replace(String template, String stringToReplace, String replaceWithString) {
        return template.replace(stringToReplace, replaceWithString);
    }

    public static String replaceAndCollapse(String template, String stringToReplace, String replaceWithString) {
        return template.replace(stringToReplace + "\n", replaceWithString);
    }

    public static String replaceAndCollapseTwo(String template, String stringToReplace, String replaceWithString) {
        return template.replace(stringToReplace + "\n\n", replaceWithString);
    }

}