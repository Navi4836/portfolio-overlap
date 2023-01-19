package com.myfundandstocks.util;

import com.myfundandstocks.domain.Fund;
import com.myfundandstocks.domain.Stock;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestUtil {

    public static Fund createFund(String fundName, String... stocks) {
        List<Stock> stockList = Stream.of(stocks).map(Stock::new).collect(Collectors.toList());
        return new Fund(fundName, stockList);
    }
}
