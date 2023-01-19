package com.myfundandstocks.domain;

import com.myfundandstocks.util.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FundTest {

    @Test
    public void shouldAddStocks() {
        Fund fund = TestUtil.createFund("HDFC", "VOLTAS", "APPLE", "BIRLA");
        Stock newStock = new Stock("New Stock");

        fund.addStock(newStock);

        assertThat(fund.getStocks())
                .hasSize(4)
                .extracting(Stock::getName)
                .contains("New Stock");
    }

    @Test
    public void shouldReturnTrueIfFundsAreEqual() {
        Fund fund = TestUtil.createFund("HDFC", "VOLTAS", "APPLE", "BIRLA");
        Fund otherFund = TestUtil.createFund("HDFC", "VOLTAS", "APPLE", "BIRLA");

        assertEquals(fund, otherFund);
        assertEquals(fund.hashCode(), otherFund.hashCode());
    }

    @Test
    public void shouldReturnFalseIfFundIsNotEqual() {
        Fund fund = TestUtil.createFund("HDFC", "VOLTAS", "APPLE", "BIRLA");
        Fund otherFund = TestUtil.createFund("AXIS", "VOLTAS", "APPLE", "BIRLA");

        assertThat(fund.equals(otherFund)).isFalse();
        assertNotEquals(fund.hashCode(), otherFund.hashCode());
    }

    @Test
    public void shouldReturnFalseIfOtherObjectIsNull() {
        Fund fund = TestUtil.createFund("HDFC", "VOLTAS", "APPLE", "BIRLA");

        assertThat(fund.equals(null)).isFalse();
    }

    @Test
    public void shouldReturnFalseIfOtherObjectIsDifferentClass() {
        Fund fund = TestUtil.createFund("HDFC", "VOLTAS", "APPLE", "BIRLA");
        Stock stock = new Stock("Stock");

        assertThat(fund.equals(stock)).isFalse();
        assertNotEquals(fund.hashCode(), stock.hashCode());
    }

    @Test
    public void shouldReturnTrueIfBothObjectsAreSame() {
        Fund fund = TestUtil.createFund("HDFC", "VOLTAS", "APPLE", "BIRLA");

        assertThat(fund.equals(fund)).isTrue();
        assertEquals(fund.hashCode(), fund.hashCode());
    }
}