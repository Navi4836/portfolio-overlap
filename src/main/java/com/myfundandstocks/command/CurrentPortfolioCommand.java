package com.myfundandstocks.command;

import java.util.Arrays;
import java.util.List;

public class CurrentPortfolioCommand extends Command {

    private final String commandName;
    private String command;
    private List<String> fundNames;

    public CurrentPortfolioCommand(String commandName, String command) {
        this.commandName = commandName;
        this.command = command;
        this.parseArguments();
    }

    private void parseArguments() {
        String[] arguments = extractValidArguments(command);
        if (arguments.length == 0) throw new IllegalArgumentException();
        fundNames = Arrays.asList(arguments);
    }

    public List<String> getFundNames() {
        return fundNames;
    }
}
