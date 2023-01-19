package com.myfundandstocks.command.handler.factory;

import com.myfundandstocks.command.AddStockCommand;
import com.myfundandstocks.command.CurrentPortfolioCommand;
import com.myfundandstocks.command.FundsOverlapCommand;
import com.myfundandstocks.command.handler.*;
import com.myfundandstocks.service.FundService;

import java.util.Objects;

import static com.myfundandstocks.constants.Constants.*;

public class CommandHandlerFactory {

    public CommandHandler createHandler(String command) {
        if (Objects.isNull(command) || command.trim().isEmpty()) return createUnknownCommandHandler();

        String trimmedCommand = command.trim();
        String[] commandWithArguments = trimmedCommand.split(COMMAND_DELIMITER);
        String commandName = commandWithArguments[0];

        switch (commandName) {
            case CURRENT_PORTFOLIO_COMMAND:
                return createCurrentPortfolioCommandHandler(commandName, trimmedCommand);
            case CALCULATE_OVERLAP_COMMAND:
                return createFundsOverlapCommandHandler(commandName, trimmedCommand);
            case ADD_STOCK_COMMAND:
                return createAddStockCommandHandler(commandName, trimmedCommand);
            default:
                return createUnknownCommandHandler();
        }
    }

    private CurrentPortfolioCommandHandler createCurrentPortfolioCommandHandler(String name, String command) {
        CurrentPortfolioCommand currentPortfolioCommand = new CurrentPortfolioCommand(name, command);
        return new CurrentPortfolioCommandHandler(currentPortfolioCommand, FundService.getInstance());
    }

    private AddStockCommandHandler createAddStockCommandHandler(String name, String command) {
        AddStockCommand addStockCommand = new AddStockCommand(name, command);
        return new AddStockCommandHandler(addStockCommand, FundService.getInstance());
    }

    public FundsOverlapCommandHandler createFundsOverlapCommandHandler(String name, String command) {
        FundsOverlapCommand fundsOverlapCommand = new FundsOverlapCommand(name, command);
        return new FundsOverlapCommandHandler(fundsOverlapCommand, FundService.getInstance());
    }

    public UnknownCommandHandler createUnknownCommandHandler() {
        return new UnknownCommandHandler();
    }
}
