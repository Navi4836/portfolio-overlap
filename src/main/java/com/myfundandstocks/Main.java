package com.myfundandstocks;

import com.myfundandstocks.command.handler.factory.CommandHandlerFactory;
import com.myfundandstocks.command.processor.CommandProcessor;
import com.myfundandstocks.util.InputFileParser;

public class Main {
    public static void main(String[] args) {

        InputFileParser inputFileParser = new InputFileParser();
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
        CommandProcessor commandProcessor = new CommandProcessor(inputFileParser, commandHandlerFactory);

        MyFundsAndStockApp myFundsAndStockApp = new MyFundsAndStockApp(commandProcessor);
        myFundsAndStockApp.processRequests(args[0]);
    }
}