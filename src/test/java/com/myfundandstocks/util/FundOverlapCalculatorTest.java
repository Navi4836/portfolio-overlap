package com.myfundandstocks.util;

import com.myfundandstocks.domain.Fund;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FundOverlapCalculatorTest {

    FundOverlapCalculator fundOverlapCalculator = new FundOverlapCalculator();

    @Test
    public void shouldReturnOverlapPercentageFor3StocksEachAndOneCommonStock() {
        Fund existingFund = TestUtil.createFund("HDFC", "INDRAPRASTHA", "COLGATE", "ADANI");
        Fund newFund = TestUtil.createFund("AXIS_MIDCAP", "ADITYA BIRLA", "COLGATE", "ADANI");

        String overlap = fundOverlapCalculator.calculateOverlap(existingFund, newFund);

        assertEquals("66.67", overlap);
    }

    @Test
    public void shouldReturnOverlapPercentageForOneCommonStock() {
        Fund existingFund = TestUtil.createFund("HDFC", "INDRAPRASTHA", "COLGATE", "ADANI", "SUPREME");
        Fund newFund = TestUtil.createFund("AXIS_MIDCAP", "ADITYA BIRLA", "COLGATE", "ATUL");

        String overlap = fundOverlapCalculator.calculateOverlap(existingFund, newFund);

        assertEquals("28.57", overlap);
    }

    @Test
    public void shouldReturnOverlapPercentageForNoCommonFunds() {
        Fund existingFund = TestUtil.createFund("HDFC", "INDRAPRASTHA", "COLGATE", "ADANI", "SUPREME");
        Fund newFund = TestUtil.createFund("AXIS_MIDCAP", "ADITYA BIRLA", "VOLTAS", "ATUL");

        String overlap = fundOverlapCalculator.calculateOverlap(existingFund, newFund);

        assertEquals("0.00", overlap);
    }

    @Test
    public void shouldReturnOverlapPercentageForAllCommonFunds() {
        Fund existingFund = TestUtil.createFund("HDFC", "INDRAPRASTHA", "COLGATE", "ADANI");
        Fund newFund = TestUtil.createFund("AXIS_MIDCAP", "INDRAPRASTHA", "COLGATE", "ADANI");

        String overlap = fundOverlapCalculator.calculateOverlap(existingFund, newFund);

        assertEquals("100.00", overlap);
    }
}