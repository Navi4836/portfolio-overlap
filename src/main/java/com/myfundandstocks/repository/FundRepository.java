
package com.myfundandstocks.repository;

import com.myfundandstocks.datastore.DataStore;
import com.myfundandstocks.domain.Fund;

import java.util.List;
import java.util.Optional;

public class FundRepository {

    private final DataStore dataStore
            = new DataStore("https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json");

    public boolean saveCurrentFunds(List<Fund> funds) {
        return dataStore.saveCurrentFunds(funds);
    }

    public List<Fund> findAll() {
        return dataStore.getFunds();
    }

    public Fund findByName(String fundName) throws FundNotFoundException {
        Optional<Fund> filterFund = dataStore.getFunds().stream().filter(fund -> fund.getName().equals(fundName)).findFirst();
        if (!filterFund.isPresent()) throw new FundNotFoundException();
        return filterFund.get();
    }

    public List<Fund> getCurrentFunds() {
        return dataStore.getCurrentFunds();
    }
}

