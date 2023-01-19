package com.myfundandstocks.command.handler;

import com.myfundandstocks.command.CurrentPortfolioCommand;
import com.myfundandstocks.service.FundService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrentPortfolioCommandHandlerTest {

    private CurrentPortfolioCommandHandler commandHandler;
    @Mock
    FundService fundService;
    @Mock
    CurrentPortfolioCommand command;

    @BeforeEach
    void setUp() {
       commandHandler  = new CurrentPortfolioCommandHandler(command, fundService);
    }

    @Test
    public void shouldHandleCurrentPortfolioCommand() {
        when(command.getFundNames())
                .thenReturn(Arrays.asList("Fund1", "Fund2"));
        when(fundService.setCurrentPortfolioFunds(command.getFundNames()))
                .thenReturn(true);
        commandHandler.handle();
        verify(fundService).setCurrentPortfolioFunds(command.getFundNames());
    }

}