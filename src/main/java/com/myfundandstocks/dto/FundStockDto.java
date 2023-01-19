package com.myfundandstocks.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myfundandstocks.domain.Fund;
import com.myfundandstocks.domain.Stock;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FundStockDto {

    private List<FundDto> funds;

    @JsonIgnore
    public List<Fund> convertToFunds() {
        if (Objects.isNull(funds)) return emptyList();
        return funds.stream().map(
                fundDto -> new Fund(fundDto.getName(), getStocks(fundDto.getStocks()))
        ).collect(Collectors.toList());
    }

    @JsonIgnore
    private List<Stock> getStocks(List<String> stockData) {
        return stockData.stream().map(Stock::new).collect(Collectors.toList());
    }

    public void setFunds(List<FundDto> funds) {
        this.funds = funds;
    }

    public List<FundDto> getFunds() {
        return funds;
    }
}
