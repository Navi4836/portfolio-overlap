package com.myfundandstocks.command.processor;

import com.myfundandstocks.command.handler.CommandHandler;
import com.myfundandstocks.command.handler.factory.CommandHandlerFactory;
import com.myfundandstocks.util.InputFileParser;

import java.util.List;
import java.util.stream.Collectors;

public class CommandProcessor {

    private final InputFileParser inputFileParser;

    private final CommandHandlerFactory commandHandlerFactory;

    public CommandProcessor(InputFileParser inputFileParser, CommandHandlerFactory commandHandlerFactory) {
        this.inputFileParser = inputFileParser;
        this.commandHandlerFactory = commandHandlerFactory;
    }

    public void process(String commandFilePath) {
        try {
            List<String> commandLine = inputFileParser.readLines(commandFilePath);
            List<CommandHandler> handlers = commandLine.stream().map(commandHandlerFactory::createHandler).collect(Collectors.toList());
            handlers.forEach(CommandHandler::handle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
