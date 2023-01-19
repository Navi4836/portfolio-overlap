package com.myfundandstocks.command.handler;

import com.myfundandstocks.command.CurrentPortfolioCommand;
import com.myfundandstocks.service.FundService;

public class CurrentPortfolioCommandHandler implements CommandHandler {

    private final CurrentPortfolioCommand command;
    private final FundService fundService;

    public CurrentPortfolioCommandHandler(CurrentPortfolioCommand command, FundService fundService) {
        this.command = command;
        this.fundService = fundService;
    }

    @Override
    public void handle() {
        fundService.setCurrentPortfolioFunds(command.getFundNames());
    }
}
