package com.myfundandstocks.command.handler.builder;

import com.myfundandstocks.command.handler.factory.CommandHandlerFactory;
import com.myfundandstocks.command.handler.AddStockCommandHandler;
import com.myfundandstocks.command.handler.CurrentPortfolioCommandHandler;
import com.myfundandstocks.command.handler.FundsOverlapCommandHandler;
import com.myfundandstocks.command.handler.UnknownCommandHandler;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandHandlerFactoryTest {

    CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();

    @Test
    public void shouldCreateAppropriateHandlers() {
    }

    @Test
    public void shouldCreateCurrentPortfolioCommandHandler() {
        String command = "CURRENT_PORTFOLIO HDFC";
        assertThat(commandHandlerFactory.createHandler(command))
                .isInstanceOf(CurrentPortfolioCommandHandler.class);
    }

    @Test
    public void shouldCreateAddStockCommandHandler() {
        String command = "ADD_STOCK HDFC VOLTAS";
        assertThat(commandHandlerFactory.createHandler(command))
                .isInstanceOf(AddStockCommandHandler.class);
    }

    @Test
    public void shouldCreateFundsOverlapCommandHandler() {
        String command = "CALCULATE_OVERLAP NEW_FUND";
        assertThat(commandHandlerFactory.createHandler(command))
                .isInstanceOf(FundsOverlapCommandHandler.class);
    }

    @Test
    public void shouldCreateUnknownCommandHandler() {
        String command = "ADD_FUND NEW_FUND";
        assertThat(commandHandlerFactory.createHandler(command))
                .isInstanceOf(UnknownCommandHandler.class);
    }

    @Test
    public void shouldReturnUnknownCommandHandlerWhenCommandIsNull() {
        assertThat(commandHandlerFactory.createHandler(null))
                .isInstanceOf(UnknownCommandHandler.class);
    }

    @Test
    public void shouldReturnUnknownCommandHandlerWhenCommandIsEmpty() {
        assertThat(commandHandlerFactory.createHandler(""))
                .isInstanceOf(UnknownCommandHandler.class);
    }

    @Test
    public void shouldReturnUnknownCommandHandlerWhenCommandIsBlank() {
        assertThat(commandHandlerFactory.createHandler(" "))
                .isInstanceOf(UnknownCommandHandler.class);
    }

    @Test
    public void shouldParseCommandAndArgumentsIfSpacesAreAvailableAtEndOrStart() {
        String command = " CURRENT_PORTFOLIO NEW_FUND ";
        assertThat(commandHandlerFactory.createHandler(command))
                .isInstanceOf(CurrentPortfolioCommandHandler.class);
    }

    @Test
    public void shouldParseCommandAndArgumentsIfSpacesAreAvailableMiddle() {
        String command = "CURRENT_PORTFOLIO      NEW_FUND";
        assertThat(commandHandlerFactory.createHandler(command))
                .isInstanceOf(CurrentPortfolioCommandHandler.class);
    }

    @Test
    public void shouldThrowExceptionWhenJustCommandIsGiven() {
        String command = "CURRENT_PORTFOLIO";
        assertThrows(IllegalArgumentException.class,
                () -> commandHandlerFactory.createHandler(command));
    }
}