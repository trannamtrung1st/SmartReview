/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.helper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author TNT
 */
public class FileHelper {

    public static void removeAllTempFileInFolderPath(String path) throws IOException {
        Stream<Path> streamTmpFiles = Files.find(new File(path).toPath(), 1, (p, a) -> {
            return p.toString().endsWith(".tmp");
        }, new FileVisitOption[]{});
        List<Path> list = streamTmpFiles.collect(Collectors.toList());
        for (Path p : list) {
            Files.delete(p);
        }
    }

    public static String randomFileName(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    public static String saveStreamToFile(InputStream stream, String file) throws IOException {
        File targetFile = new File(file);
        Path absPath = targetFile.toPath().toAbsolutePath();
        java.nio.file.Files.copy(
                stream,
                absPath,
                StandardCopyOption.REPLACE_EXISTING);
        return absPath.toString();
    }

    public static List<String> readAllLines(String dataPath) throws IOException {
        return Files.readAllLines(new File(dataPath).toPath());
    }

    public static String readContent(String dataPath) throws IOException {
        List<String> lines = readAllLines(dataPath);
        return String.join("\n", lines);
    }

    public static boolean delete(String path) throws IOException {
        return Files.deleteIfExists(new File(path).toPath());
    }

    public static void writeToFile(String content, String path) throws IOException {
        Files.write(new File(path).toPath(), content.getBytes(), new OpenOption[]{});
    }

}
