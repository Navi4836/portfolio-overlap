package com.myfundandstocks.command.handler;

import com.myfundandstocks.command.AddStockCommand;
import com.myfundandstocks.domain.Fund;
import com.myfundandstocks.repository.FundNotFoundException;
import com.myfundandstocks.service.FundService;
import com.myfundandstocks.util.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddStockCommandHandlerTest {

    private AddStockCommandHandler commandHandler;
    @Mock
    FundService fundService;
    @Mock
    AddStockCommand command;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        commandHandler = new AddStockCommandHandler(command, fundService);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void shouldHandleAddStockCommand() {
        when(command.getFundName()).thenReturn("Fund");
        when(command.getStockName()).thenReturn("Stock");

        when(fundService.addStocks(command.getFundName(), command.getStockName()))
                .thenReturn(TestUtil.createFund("Test", "Stock"));

        commandHandler.handle();

        verify(fundService).addStocks("Fund", "Stock");
    }

    @Test
    public void shouldLogFundNotFoundWhenUnknownFundIsGiven() {
        when(command.getFundName()).thenReturn("Fund");
        when(command.getStockName()).thenReturn("Stock");

        RuntimeException exception = new FundNotFoundException();
        when(fundService.addStocks(command.getFundName(), command.getStockName()))
                .thenThrow(exception);

        commandHandler.handle();

        assertThat(outputStreamCaptor.toString()).isEqualTo("FUND_NOT_FOUND\n");
    }

}