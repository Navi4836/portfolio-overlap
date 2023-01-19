package com.myfundandstocks.repository;

import com.myfundandstocks.domain.Fund;
import com.myfundandstocks.service.FundService;
import com.myfundandstocks.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FundRepositoryTest {
    private FundRepository fundRepository;

    @BeforeEach
    void setUp() {
        fundRepository = new FundRepository();
    }

    @Test
    public void shouldReturnAllFunds() {
        assertEquals(10, fundRepository.findAll().size());
    }

    @Test
    public void shouldReturnFundWhenFundNameMatches() {
        Fund utiNiftyIndex = fundRepository.findByName("UTI_NIFTY_INDEX");
        assertAll(
                () -> assertThat(utiNiftyIndex.getStocks().size()).isEqualTo(57),
                () -> assertTrue(utiNiftyIndex.getStocks().stream().anyMatch(
                        stock -> stock.getName().equals("DABUR INDIA LIMITED")
                ))
        );
    }

    @Test
    public void shouldThrowFundNotFoundExceptionWhenFundNotMatches() {
        assertThrows(FundNotFoundException.class, () -> fundRepository.findByName("UNKNOWN_MUTUAL_FUND"));
    }

    @Test
    public void shouldSaveCurrentFunds() {
        Fund hdfc = TestUtil.createFund("HDFC", "ADANI", "VOLTAS", "APPLE");
        Fund axis = TestUtil.createFund("AXIS MULTICAP", "GOOGLE", "OTTO", "GUCCI");
        List<Fund> funds = Arrays.asList(hdfc, axis);

        fundRepository.saveCurrentFunds(funds);

        assertEquals(funds, fundRepository.getCurrentFunds());
    }
}