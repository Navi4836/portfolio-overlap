package com.myfundandstocks.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class JsonDataReader {

    public <T> T get(String dataUrl, Class<T> type) throws IOException {
        URL url = new URL(dataUrl);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(url, type);
    }
}
