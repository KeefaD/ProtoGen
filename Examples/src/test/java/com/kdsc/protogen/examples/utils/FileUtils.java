package com.kdsc.protogen.examples.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class FileUtils {

    public static void deleteDirectoryRecursively(String pathToDirectory) {
        var path = Paths.get(pathToDirectory);
        var file = path.toFile();
        if(!file.exists()) {
            return;
        }
        try {
            Files.walk(path)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                //TODO:KMD Sort out this warning
                .forEach(File::delete);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    public static String readFileContents(File file) {
        if(!file.exists()) {
            throw new RuntimeException("Cannot read contents of file because it doesn't exist" + file.toPath());
        }
        try {
            return Files.readString(file.toPath());
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

}