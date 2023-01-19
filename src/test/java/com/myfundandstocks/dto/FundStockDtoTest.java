package com.myfundandstocks.dto;

import com.myfundandstocks.domain.Fund;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

public class FundStockDtoTest {

    @Test
    public void shouldReturnFundsWhenFundsDtoListIsNotEmpty() {
        FundStockDto fundStockDto = new FundStockDto();
        fundStockDto.setFunds(getFundDtoList());
        List<Fund> funds = fundStockDto.convertToFunds();
        assertThat(funds.size()).isEqualTo(fundStockDto.getFunds().size());
        assertThat(funds.get(0).getName()).isEqualTo(fundStockDto.getFunds().get(0).getName());
    }

    @Test
    public void shouldReturnEmptyFundsWhenFundsDtoListIsEmpty() {
        FundStockDto fundStockDto = new FundStockDto();
        fundStockDto.setFunds(emptyList());
        List<Fund> funds = fundStockDto.convertToFunds();
        assertThat(funds).isEmpty();
    }

    @Test
    public void shouldReturnEmptyFundsWhenFundsDtoListIsNull() {
        FundStockDto fundStockDto = new FundStockDto();
        List<Fund> funds = fundStockDto.convertToFunds();
        assertThat(funds).isEmpty();
    }

    private List<FundDto> getFundDtoList() {
        FundDto fundDto1 = new FundDto("UTI_NIFTY_INDEX", Arrays.asList("Stock1", "Stock2"));
        FundDto fundDto2 = new FundDto("AXIS_MIDCAP", Arrays.asList("Stock3", "Stock4"));
        return Arrays.asList(fundDto1, fundDto2);
    }

}
