package com.myfundandstocks;

import com.myfundandstocks.command.processor.CommandProcessor;

public class MyFundsAndStockApp {

    private final CommandProcessor commandProcessor;

    public MyFundsAndStockApp(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    public void processRequests(String commandFilePath) {
        commandProcessor.process(commandFilePath);
    }
}
