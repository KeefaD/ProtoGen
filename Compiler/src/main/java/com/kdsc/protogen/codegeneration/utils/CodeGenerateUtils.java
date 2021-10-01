package com.kdsc.protogen.codegeneration.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//TODO:KMD This needs a close look at
//TODO:KMD Need to think about charsets, perhaps write a program in a funny charset, it has to be totally reliable
public class CodeGenerateUtils {

    public static void writeStringToPath(final String pathAndFileName, final String textToWrite) {
        var path = Paths.get(pathAndFileName);

        var parentFile = path.getParent().toFile();

        if(!parentFile.exists()) {

            //TODO:KMD Why am I getting a warning here in the code analysis
            parentFile.mkdirs();
        }

        try {
            Files.writeString(path, textToWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}