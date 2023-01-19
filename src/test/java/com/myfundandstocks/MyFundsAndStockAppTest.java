package com.myfundandstocks;

import com.myfundandstocks.command.processor.CommandProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyFundsAndStockAppTest {

    @Mock
    private CommandProcessor commandProcessor;

    private MyFundsAndStockApp fundsAndStockApp;

    @BeforeEach
    void setUp() {
        fundsAndStockApp = new MyFundsAndStockApp(commandProcessor);
    }

    @Test
    public void shouldProcessGivenRequests() {
        String commandFilePath = "somePath/command.txt";

        fundsAndStockApp.processRequests(commandFilePath);

        verify(commandProcessor).process(commandFilePath);
    }
}