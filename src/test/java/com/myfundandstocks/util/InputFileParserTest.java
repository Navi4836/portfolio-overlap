package com.myfundandstocks.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class InputFileParserTest {

    InputFileParser inputFileParser = new InputFileParser();

    @Test
    void shouldReturnLinesFromFile(@TempDir Path tempDirectory)
            throws IOException {
        Path testFilePath = tempDirectory.resolve("test.txt");
        List<String> writtenLines = Arrays.asList("COMMAND1", "COMMAND2", "COMMAND3");
        Files.write(testFilePath, writtenLines);

        List<String> readLines = inputFileParser.readLines(testFilePath.toFile().getAbsolutePath());

        assertLinesMatch(writtenLines, readLines);
    }
}
