package com.myfundandstocks.datastore;

import com.myfundandstocks.domain.Fund;
import com.myfundandstocks.dto.FundStockDto;
import com.myfundandstocks.util.JsonDataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private final String url;
    private final JsonDataReader jsonDataReader = new JsonDataReader();
    private List<Fund> allFunds = new ArrayList<>();
    private List<Fund> currentFunds = new ArrayList<>();

    public DataStore(String url) {
        this.url = url;
        this.init();
    }

    private void init() {
        try {
            FundStockDto fundStockDto = jsonDataReader.get(url, FundStockDto.class);
            this.allFunds = fundStockDto.convertToFunds();
        } catch (IOException e) {
            System.out.println("Exception while fetching funds " + e.getMessage());
        }
    }

    public List<Fund> getFunds() {
        return allFunds;
    }

    public boolean saveCurrentFunds(List<Fund> currentFunds) {
        this.currentFunds = currentFunds;
        return true;
    }

    public List<Fund> getCurrentFunds() {
        return currentFunds;
    }
}
