package com.myfundandstocks.command;

import java.util.Arrays;
import static com.myfundandstocks.constants.Constants.COMMAND_DELIMITER;

public class AddStockCommand extends Command {

    private final String commandName;
    private String command;

    private String stockName;
    private String fundName;

    public AddStockCommand(String commandName, String command) {
        this.commandName = commandName;
        this.command = command;
        this.parseArguments();
    }

    private void parseArguments() {
        String[] arguments = extractValidArguments(command);
        if (arguments.length <= 1) throw new IllegalArgumentException();

        fundName = arguments[0];
        stockName = String.join(COMMAND_DELIMITER, Arrays.copyOfRange(arguments, 1, arguments.length));
    }

    public String getStockName() {
        return stockName;
    }

    public String getFundName() {
        return fundName;
    }
}
