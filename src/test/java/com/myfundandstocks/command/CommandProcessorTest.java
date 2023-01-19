package com.myfundandstocks.command;

import com.myfundandstocks.command.handler.UnknownCommandHandler;
import com.myfundandstocks.command.handler.factory.CommandHandlerFactory;
import com.myfundandstocks.command.processor.CommandProcessor;
import com.myfundandstocks.util.InputFileParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommandProcessorTest {

    @Mock
    private CommandHandlerFactory commandHandlerFactory;
    private InputFileParser inputFileParser = new InputFileParser();
    private CommandProcessor commandProcessor;
    String commandFilePath;
    List<String> writtenLines;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp(@TempDir Path tempDirectory) throws IOException {
        commandProcessor = new CommandProcessor(inputFileParser, commandHandlerFactory);
        Path testFilePath = tempDirectory.resolve("test.txt");
        writtenLines = Arrays.asList("COMMAND1", "COMMAND2", "COMMAND3");
        Files.write(testFilePath, writtenLines);
        commandFilePath = testFilePath.toFile().getAbsolutePath();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void shouldProcessGivenRequests() {
        final ArgumentCaptor<String> commandLineCaptor = ArgumentCaptor.forClass(String.class);

        when(commandHandlerFactory.createHandler(anyString()))
                .thenReturn(new UnknownCommandHandler());

        commandProcessor.process(commandFilePath);

        assertAll(
                () -> verify(commandHandlerFactory, times(writtenLines.size()))
                        .createHandler(commandLineCaptor.capture()),
                () -> assertEquals(writtenLines, commandLineCaptor.getAllValues())
        );
    }

    @Test
    public void shouldLogWhenSomeExceptionHappens() {
        Exception illegalArgumentException = mock(IllegalArgumentException.class);
        when(commandHandlerFactory.createHandler(anyString()))
                .thenThrow(illegalArgumentException);

        commandProcessor.process(commandFilePath);

       verify(illegalArgumentException).printStackTrace();
    }
}