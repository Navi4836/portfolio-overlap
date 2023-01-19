package com.myfundandstocks.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class InputFileParser {

    public List<String> readLines(String fileNameWithPath) throws IOException {
        Path path = Paths.get(fileNameWithPath);
        return Files.readAllLines(path);
    }
}
