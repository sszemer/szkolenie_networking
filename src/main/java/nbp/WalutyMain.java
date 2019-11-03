package nbp;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class WalutyMain {

    private static boolean found;

    public static void main(String[] args) throws IOException {

        Tables[] tablesA  = callWS("A");
        Tables[] tablesB  = callWS("B");

        System.out.println(tablesA[0].getRates().size());
        System.out.println(tablesB[0].getRates().size());

        for (Tables tables : tablesA) {
            tables.getRates().forEach(rate -> {
                for (Tables tables2 : tablesB) {
                    found=true;
                    tables2.getRates().forEach(rate1 -> {
                        //jak nie znajdzie to skopiowac do b //albo jak znajdzie to usunac z A a potem merge
                        if (!rate.getCode().equalsIgnoreCase(rate1.getCode())) {
                            found = false;
                        }
                    });
                    tables2.getRates().add(rate);
                }
            });
        }
        System.out.println(tablesB[0].getRates().size());
        tablesB[0].getRates().stream().forEach(rate -> {
            System.out.println(rate.getCode());
        });
    }


    private static Tables[] callWS(String table) throws IOException {
        String address = "http://api.nbp.pl/api/exchangerates/tables/"+table+"/?format=json";
        URL url = new URL(address);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        String output = new String(bufferedInputStream.readAllBytes());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(output, Tables[].class);
    }
}
