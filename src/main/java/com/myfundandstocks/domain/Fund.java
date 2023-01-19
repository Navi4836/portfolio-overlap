package com.myfundandstocks.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fund {

    private String name;
    private List<Stock> stocks;

    public Fund(String name, List<Stock> stocks) {
        this.name = name;
        this.stocks = stocks;
    }

    public boolean addStock(Stock stock) {
        return stocks.add(stock);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fund fund = (Fund) o;
        return Objects.equals(name, fund.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public List<Stock> getStocks() {
        return stocks;
    }
}
