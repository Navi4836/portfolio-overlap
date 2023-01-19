package com.myfundandstocks.command.handler;

import com.myfundandstocks.command.FundsOverlapCommand;
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
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FundsOverlapCommandHandlerTest {

    private FundsOverlapCommandHandler commandHandler;
    @Mock
    FundsOverlapCommand command;
    @Mock
    FundService fundService;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        commandHandler = new FundsOverlapCommandHandler(command, fundService);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void shouldPrintFundOverlapWithKnownFund() {
        String fundToCompare = "UTI_NIFTY_INDEX";
        when(command.getFundName()).thenReturn(fundToCompare);
        when(fundService.searchFund(fundToCompare))
                .thenReturn(TestUtil.createFund(fundToCompare, "VOLTAS", "OTTO"));
        when(fundService.getCurrentFunds())
                .thenReturn(getCurrentFunds());

        commandHandler.handle();

        assertThat(outputStreamCaptor.toString()).isEqualTo("UTI_NIFTY_INDEX HDFC 40.00%\n" +
                "UTI_NIFTY_INDEX AXIS_MULTICAP 40.00%\n");
    }

    @Test
    public void shouldPrintFundOverlapWithKnownFundZeroOverlap() {
        String fundToCompare = "UTI_NIFTY_INDEX";
        when(command.getFundName()).thenReturn(fundToCompare);
        when(fundService.searchFund(anyString()))
                .thenReturn(TestUtil.createFund(fundToCompare, "AMAZON", "FACEBOOK"));
        when(fundService.getCurrentFunds())
                .thenReturn(getCurrentFunds());

        commandHandler.handle();

        assertThat(outputStreamCaptor.toString()).isEqualTo("");
    }

    @Test
    public void shouldPrintFundOverlapWithKnownAndUnknownFund() {
        String knownFund = "UTI_NIFTY_INDEX";
        String unknownFund = "UTI_NIFTY";
        when(command.getFundName()).thenReturn(knownFund, unknownFund);
        when(fundService.searchFund(anyString()))
                .thenReturn(TestUtil.createFund(knownFund, "VOLTAS", "OTTO"))
                .thenThrow(new FundNotFoundException());
        when(fundService.getCurrentFunds())
                .thenReturn(getCurrentFunds());

        commandHandler.handle();
        commandHandler.handle();

        assertThat(outputStreamCaptor.toString()).isEqualTo(
                "UTI_NIFTY_INDEX HDFC 40.00%\n" +
                        "UTI_NIFTY_INDEX AXIS_MULTICAP 40.00%\n" +
                        "FUND_NOT_FOUND\n"
        );
    }

    @Test
    public void shouldPrintFundOverlapWithUpdatedFunds() {
        String firstKnownFund = "UTI_NIFTY_INDEX";
        String secondKnownFund = "PARAG_PARIKH_FLEXI_CAP";
        when(command.getFundName()).thenReturn(firstKnownFund, secondKnownFund);
        when(fundService.searchFund(anyString()))
                .thenReturn(TestUtil.createFund(firstKnownFund, "VOLTAS", "OTTO"))
                .thenReturn(TestUtil.createFund(secondKnownFund, "GOOGLE", "OTTO"));
        when(fundService.getCurrentFunds())
                .thenReturn(getCurrentFunds())
                .thenReturn(getUpdatedFunds());

        commandHandler.handle();
        commandHandler.handle();

        assertThat(outputStreamCaptor.toString()).isEqualTo(
                "UTI_NIFTY_INDEX HDFC 40.00%\n" +
                        "UTI_NIFTY_INDEX AXIS_MULTICAP 40.00%\n" +
                        "PARAG_PARIKH_FLEXI_CAP AXIS_MULTICAP 80.00%\n"
        );
    }

    private List<Fund> getUpdatedFunds() {
        Fund hdfc = TestUtil.createFund("HDFC", "ADANI", "VOLTAS", "APPLE", "FACEBOOk");
        Fund axis = TestUtil.createFund("AXIS_MULTICAP", "GOOGLE", "OTTO", "GUCCI");
        return Arrays.asList(hdfc, axis);
    }

    private static List<Fund> getCurrentFunds() {
        Fund hdfc = TestUtil.createFund("HDFC", "ADANI", "VOLTAS", "APPLE");
        Fund axis = TestUtil.createFund("AXIS_MULTICAP", "GOOGLE", "OTTO", "GUCCI");
        return Arrays.asList(hdfc, axis);
    }

}