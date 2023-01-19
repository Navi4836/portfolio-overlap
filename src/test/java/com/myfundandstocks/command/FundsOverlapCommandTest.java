package com.myfundandstocks.command;

import org.junit.jupiter.api.Test;

import static com.myfundandstocks.constants.Constants.CALCULATE_OVERLAP_COMMAND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FundsOverlapCommandTest {

    @Test
    public void shouldParseGivenArguments() {
        String command = "CALCULATE_OVERLAP NEW_FUND";
        FundsOverlapCommand fundsOverlapCommand = new FundsOverlapCommand(CALCULATE_OVERLAP_COMMAND, command);

        assertEquals("NEW_FUND", fundsOverlapCommand.getFundName());
    }

    @Test
    public void shouldThrowExceptionWhenArgumentsAreInsufficient() {
        String command = "CALCULATE_OVERLAP";
        assertThrows(IllegalArgumentException.class, () -> new FundsOverlapCommand(CALCULATE_OVERLAP_COMMAND, command));
    }

    @Test
    public void shouldThrowExceptionWhenArgumentsAreMoreThanRequired() {
        String command = "CALCULATE_OVERLAP NEW_FUND_1 NEW_FUND_2";
        assertThrows(IllegalArgumentException.class, () -> new FundsOverlapCommand(CALCULATE_OVERLAP_COMMAND, command));
    }

    @Test
    public void shouldThrowExceptionWhenArgumentsAreEmpty() {
        String command = "CALCULATE_OVERLAP";
        assertThrows(IllegalArgumentException.class, () -> new FundsOverlapCommand(CALCULATE_OVERLAP_COMMAND, command));
    }

    @Test
    public void shouldThrowExceptionWhenArgumentsIsBlank() {
        String command = "CALCULATE_OVERLAP  ";
        assertThrows(IllegalArgumentException.class, () -> new FundsOverlapCommand(CALCULATE_OVERLAP_COMMAND, command));
    }
}