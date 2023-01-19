package com.myfundandstocks.service;

import com.myfundandstocks.domain.Fund;
import com.myfundandstocks.domain.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FundServiceTest {

    private FundService fundService;

    @BeforeEach
    void setUp() {
        fundService = FundService.getInstance();
    }

    @Test
    public void shouldSetCurrentPortfolioFunds() {
        List<String> fundNames = Arrays.asList("UTI_NIFTY_INDEX", "AXIS_MIDCAP", "PARAG_PARIKH_CONSERVATIVE_HYBRID");

        boolean result = fundService.setCurrentPortfolioFunds(fundNames);

        assertTrue(result);
        assertThat(fundService.getCurrentFunds())
                .hasSize(3)
                .extracting(Fund::getName)
                .contains("UTI_NIFTY_INDEX", "AXIS_MIDCAP", "PARAG_PARIKH_CONSERVATIVE_HYBRID");

    }

    @Test
    public void shouldReturnFundWhenSearchedUsingName() {

        String fundName = "MIRAE_ASSET_EMERGING_BLUECHIP";

        Fund resultFund = fundService.searchFund(fundName);

        assertThat(resultFund)
                .extracting(Fund::getName)
                .isEqualTo("MIRAE_ASSET_EMERGING_BLUECHIP");

    }

    @Test
    public void shouldAddStockToFund() {
        String fundName = "MIRAE_ASSET_EMERGING_BLUECHIP";
        String stockName = "New Stock";

        Fund resultFund = fundService.addStocks(fundName, stockName);

        assertThat(resultFund.getStocks())
                .hasSize(60)
                .extracting(Stock::getName)
                .containsOnlyOnce(stockName);
    }

    @Test
    public void shouldReturnAllFunds() {
        List<Fund> allFunds = fundService.getAllFunds();

        assertThat(allFunds).hasSize(10);
    }

    @Test
    public void shouldProvideSingleInstance() {
        FundService firstInstance = FundService.getInstance();
        FundService secondInstance = FundService.getInstance();

        assertEquals(firstInstance, secondInstance);
    }

}