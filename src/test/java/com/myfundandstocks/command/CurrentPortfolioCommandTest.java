package com.myfundandstocks.command;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.myfundandstocks.constants.Constants.CURRENT_PORTFOLIO_COMMAND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CurrentPortfolioCommandTest {

    @Test
    public void shouldParseGivenArguments() {
        String command = "CURRENT_PROFILE FUND1 FUND2";

        CurrentPortfolioCommand currentPortfolioCommand =
                new CurrentPortfolioCommand(CURRENT_PORTFOLIO_COMMAND, command);

        String[] expectedFunds = new String[]{"FUND1", "FUND2"};
        assertEquals(Arrays.asList(expectedFunds), currentPortfolioCommand.getFundNames());
    }

    @Test
    public void shouldParseGivenArgumentsWhenInputHaveExtraSpacesAtStartAndEnd() {
        String command = "CURRENT_PROFILE   FUND1   FUND2 ";

        CurrentPortfolioCommand currentPortfolioCommand =
                new CurrentPortfolioCommand(CURRENT_PORTFOLIO_COMMAND, command);

        String[] expected = new String[]{"FUND1", "FUND2"};
        assertEquals(Arrays.asList(expected), currentPortfolioCommand.getFundNames());
    }

    @Test
    public void shouldParseGivenArgumentsWhenOneOfTheArgumentsIsBlank() {
        String command = "CURRENT_PROFILE FUND1 FUND2  ";

        CurrentPortfolioCommand currentPortfolioCommand =
                new CurrentPortfolioCommand(CURRENT_PORTFOLIO_COMMAND, command);

        String[] expected = new String[]{"FUND1", "FUND2"};
        assertEquals(Arrays.asList(expected), currentPortfolioCommand.getFundNames());
    }

    @Test
    public void shouldThrowExceptionWhenNoArgumentsAreGiven() {
        String command = "CURRENT_PROFILE";
        assertThrows(IllegalArgumentException.class,
                () -> new CurrentPortfolioCommand(CURRENT_PORTFOLIO_COMMAND, command));
    }

    @Test
    public void shouldThrowExceptionWhenArgumentIsBlank() {
        String command = "CURRENT_PROFILE  ";
        assertThrows(IllegalArgumentException.class,
                () -> new CurrentPortfolioCommand(CURRENT_PORTFOLIO_COMMAND, command));
    }
}