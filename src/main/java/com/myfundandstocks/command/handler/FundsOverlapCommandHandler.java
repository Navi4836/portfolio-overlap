package com.myfundandstocks.command.handler;

import com.myfundandstocks.command.FundsOverlapCommand;
import com.myfundandstocks.domain.Fund;
import com.myfundandstocks.repository.FundNotFoundException;
import com.myfundandstocks.service.FundService;
import com.myfundandstocks.util.FundOverlapCalculator;

public class FundsOverlapCommandHandler implements CommandHandler {

    private final FundsOverlapCommand command;
    private final FundService fundService;

    private final FundOverlapCalculator fundOverlapCalculator = new FundOverlapCalculator();

    public FundsOverlapCommandHandler(FundsOverlapCommand fundsOverlapCommand, FundService fundService) {
        this.command = fundsOverlapCommand;
        this.fundService = fundService;
    }

    @Override
    public void handle() {
        String newFundToCompare = command.getFundName();
        try {
            Fund newFund = fundService.searchFund(newFundToCompare);
            fundService.getCurrentFunds().forEach(
                    existingFund -> {
                        String overlapPercentage = fundOverlapCalculator.calculateOverlap(existingFund, newFund);
                        if (Double.parseDouble(overlapPercentage) > 0D) {
                            System.out.println(newFund.getName() + " " + existingFund.getName() + " " + overlapPercentage + "%");
                        }
                    });
        } catch (FundNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
