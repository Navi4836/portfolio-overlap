package com.myfundandstocks.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @Test
    public void shouldReturnTrueIfStocksAreEqual() {
        Stock stock = new Stock("ADANI");
        Stock otherStock = new Stock("ADANI");

        assertThat(stock.equals(otherStock)).isTrue();
    }

    @Test
    public void shouldReturnTrueIfOtherObjectIsAlsoSame() {
        Stock stock = new Stock("ATUL");

        assertThat(stock.equals(stock)).isTrue();
    }

    @Test
    public void shouldReturnFalseIfStocksAreNotEqual() {
        Stock stock = new Stock("ATUL");
        Stock otherStock = new Stock("ADANI");

        assertThat(stock.equals(otherStock)).isFalse();
    }

    @Test
    public void shouldReturnFalseIfOtherStockObjectIsNull() {
        Stock stock = new Stock("ATUL");

        assertThat(stock.equals(null)).isFalse();
    }

    @Test
    public void shouldReturnFalseIfOtherObjectsIsDifferentClass() {
        Fund fund = new Fund("ICICI", null);
        Stock stock = new Stock("ATUL");

        assertThat(stock.equals(fund)).isFalse();
    }
}