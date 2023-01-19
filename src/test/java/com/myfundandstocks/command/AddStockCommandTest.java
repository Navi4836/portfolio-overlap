package com.myfundandstocks.command;

import org.junit.jupiter.api.Test;

import static com.myfundandstocks.constants.Constants.ADD_STOCK_COMMAND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddStockCommandTest {

    @Test
    public void shouldParseGivenArguments() {
        String command = "ADD_STOCK FUND STOCK";
        AddStockCommand addStockCommand = new AddStockCommand(ADD_STOCK_COMMAND, command);

        assertEquals("FUND", addStockCommand.getFundName());
        assertEquals("STOCK", addStockCommand.getStockName());
    }

    @Test
    public void shouldParseArgumentsWhenStockNameHaveSpaces() {
        String command = "ADD_STOCK PARAG_PARIKH_FLEXI_CAP SHEELA FOAM LIMITED";

        AddStockCommand addStockCommand = new AddStockCommand("ADD_STOCK", command);

        assertEquals("PARAG_PARIKH_FLEXI_CAP", addStockCommand.getFundName());
        assertEquals("SHEELA FOAM LIMITED", addStockCommand.getStockName());
    }

    @Test
    public void shouldParseArgumentsWhenSpacesAreGivenBetweenArguments() {
        String command = "ADD_STOCK PARAG_PARIKH_FLEXI_CAP     SHEELA FOAM LIMITED";

        AddStockCommand addStockCommand = new AddStockCommand(ADD_STOCK_COMMAND, command);

        assertEquals("PARAG_PARIKH_FLEXI_CAP", addStockCommand.getFundName());
        assertEquals("SHEELA FOAM LIMITED", addStockCommand.getStockName());
    }

    @Test
    public void shouldThrowExceptionWhenArgumentsAreBlank() {
        String command = "ADD_STOCK     ";

        assertThrows(IllegalArgumentException.class, () -> new AddStockCommand(ADD_STOCK_COMMAND, command));
    }

    @Test
    public void shouldThrowExceptionWhenArgumentsAreEmpty() {
        String command = "ADD_STOCK";

        assertThrows(IllegalArgumentException.class, () -> new AddStockCommand(ADD_STOCK_COMMAND, command));
    }

    @Test
    public void shouldThrowExceptionWhenArgumentsAreInsufficient() {
        String command = "ADD_STOCK FUND";
        assertThrows(IllegalArgumentException.class, () -> new AddStockCommand(ADD_STOCK_COMMAND, command));
    }
}