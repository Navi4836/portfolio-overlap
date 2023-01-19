package com.myfundandstocks.command;

public class FundsOverlapCommand extends Command {

    private final String commandName;
    private String command;
    private String fundName;

    public FundsOverlapCommand(String commandName, String command) {
        this.commandName = commandName;
        this.command = command;
        this.parseArguments();
    }

    private void parseArguments() {
        String[] arguments = extractValidArguments(command);
        if (arguments.length != 1) throw new IllegalArgumentException();
        fundName = arguments[0];
    }

    public String getFundName() {
        return fundName;
    }
}
