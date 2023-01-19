package com.myfundandstocks.service;

import com.myfundandstocks.domain.Fund;
import com.myfundandstocks.domain.Stock;
import com.myfundandstocks.repository.FundRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FundService {

    private final FundRepository fundRepository;
    private static FundService instance;

    private FundService(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    public static FundService getInstance() {
        if (instance == null) {
            instance = new FundService(new FundRepository());
        }
        return instance;
    }

    public boolean setCurrentPortfolioFunds(List<String> fundNames) {
        List<Fund> funds = fundNames.stream().map(fundRepository::findByName).collect(Collectors.toList());
        return fundRepository.saveCurrentFunds(funds);
    }

    public Fund addStocks(String fundName, String stockName) {
        Fund fund = fundRepository.findByName(fundName);
        Stock stock = new Stock(stockName);
        fund.addStock(stock);
        return fund;
    }

    public Fund searchFund(String fundName) {
        return fundRepository.findByName(fundName);
    }

    public List<Fund> getAllFunds() {
        return fundRepository.findAll();
    }

    public List<Fund> getCurrentFunds() {
        return fundRepository.getCurrentFunds();
    }
}
