package com.myfundandstocks.dto;

import java.util.List;

public class FundDto {

    private String name;
    private List<String> stocks;

    public FundDto() {}

    public FundDto(String name, List<String> stocks) {
        this.name = name;
        this.stocks = stocks;
    }

    public String getName(){
        return name;
    }

    public List<String> getStocks(){
        return stocks;
    }
}

