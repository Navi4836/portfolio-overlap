package com.myfundandstocks.datastore;

import com.myfundandstocks.repository.FundRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DataStoreTest {

    String stockUrl = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json";
    private DataStore dataStore;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        dataStore = new DataStore(stockUrl);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void shouldFetchFundsOnInit() {
        assertEquals(10, dataStore.getFunds().size());
        assertEquals(0, dataStore.getCurrentFunds().size());
    }

    @Test
    public void shouldLogExceptionWhileFetchingFundsOnInit() {
        String wrongUrl = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_dat.json";
        new DataStore(wrongUrl);
        assertThat(outputStreamCaptor.toString().trim()).contains("Exception while fetching funds");
    }
}