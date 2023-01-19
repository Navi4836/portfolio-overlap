package com.myfundandstocks.repository;

public class FundNotFoundException extends RuntimeException {
    public FundNotFoundException() {
        super("FUND_NOT_FOUND");
    }
}
