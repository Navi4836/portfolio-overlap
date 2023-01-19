package com.myfundandstocks.command.handler;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class UnknownCommandHandlerTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void shouldPrintExpectedLogWhenExecuting() {
        UnknownCommandHandler unknownCommandHandler = new UnknownCommandHandler();
        unknownCommandHandler.handle();

        assertThat(outputStreamCaptor.toString().trim()).contains("Command not found");
    }
}