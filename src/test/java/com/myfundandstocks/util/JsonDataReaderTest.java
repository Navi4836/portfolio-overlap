package com.myfundandstocks.util;

import com.myfundandstocks.dto.FundStockDto;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonDataReaderTest {

    String stockUrl = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json";
    String albumUrl = "https://jsonplaceholder.typicode.com/albums";

    JsonDataReader jsonDataReader = new JsonDataReader();

    @Test
    public void shouldReturnSpecifiedClassType() throws IOException {
        ArrayList<Album> albumList = jsonDataReader.get(albumUrl, ArrayList.class);

        assertAll(
                () -> assertThat(jsonDataReader.get(stockUrl, FundStockDto.class)).isInstanceOf(FundStockDto.class),
                () -> assertThat(albumList.size()).isEqualTo(100)
        );
    }

    @Test
    public void shouldThrowExceptionWhenNotAbleToConnectUrl() throws IOException {
        String badStockUrl = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_dat.json";
        String badAlbumUrl = "https://jsonplaceholder.typicode.com/albus";

        assertThrows(IOException.class, () -> jsonDataReader.get(badStockUrl, FundStockDto.class));
        assertThrows(IOException.class, () -> jsonDataReader.get(badAlbumUrl, ArrayList.class));
    }

    @Test
    public void shouldThrowExceptionWhenNotAbleToParseIntoGivenClass() {
        assertThrows(IOException.class, () -> jsonDataReader.get(albumUrl, AlbumList.class));
    }
}
