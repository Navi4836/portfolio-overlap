package com.myfundandstocks.command;

import com.myfundandstocks.constants.Constants;
import java.util.Arrays;

public abstract class Command {
    public String[] extractValidArguments(String command) {
        String[] commandWithArguments = command.split(Constants.COMMAND_DELIMITER);
        String[] arguments = Arrays.copyOfRange(commandWithArguments, 1, commandWithArguments.length);
        String[] trimmedArguments = Arrays.stream(arguments).map(String::trim).toArray(String[]::new);
        return Arrays.stream(trimmedArguments).filter(arg -> !arg.isEmpty()).toArray(String[]::new);
    }

}
