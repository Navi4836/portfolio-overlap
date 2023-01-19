package com.myfundandstocks.util;

import com.myfundandstocks.domain.Fund;
import com.myfundandstocks.domain.Stock;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FundOverlapCalculator {

    public String calculateOverlap(Fund currentFund, Fund fundToCompare) {
        double totalStocksCount = currentFund.getStocks().size() + fundToCompare.getStocks().size();

        List<Stock> commonStocks = new ArrayList(currentFund.getStocks());
        commonStocks.retainAll(fundToCompare.getStocks());
        double numberOfCommonStocks = commonStocks.size();

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setMinimumFractionDigits(2);

        double overlapPercentage = ((2 * numberOfCommonStocks) / totalStocksCount) * 100;
        return decimalFormat.format(overlapPercentage);
    }
}