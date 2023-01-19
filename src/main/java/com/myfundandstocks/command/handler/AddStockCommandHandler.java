package com.myfundandstocks.command.handler;

import com.myfundandstocks.command.AddStockCommand;
import com.myfundandstocks.repository.FundNotFoundException;
import com.myfundandstocks.service.FundService;

public class AddStockCommandHandler implements CommandHandler {

    private final AddStockCommand command;
    private final FundService fundService;

    public AddStockCommandHandler(AddStockCommand command, FundService fundService) {
        this.command = command;
        this.fundService = fundService;
    }

    @Override
    public void handle() {
        try {
            fundService.addStocks(command.getFundName(), command.getStockName());
        } catch (FundNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
