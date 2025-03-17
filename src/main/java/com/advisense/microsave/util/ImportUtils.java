package com.advisense.microsave.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import java.nio.file.Path;



import org.springframework.core.io.ClassPathResource;

public class ImportUtils {
    private static final String RESOURCE = "customers.json";

    public static List<String> lines(String json) {
        String[] split = json.split("[\r\n]+");
        return Arrays.asList(split);
    }

    public static List<String> lines(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    public static String linesFromResource() throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(RESOURCE)));
        List<String> list = in.lines().toList();
        return String.join("\n", list);
    }

}
