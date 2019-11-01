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

    public static void main(String[] args) throws IOException {
        List<Rate> merge=null;

        Tables[] tablesA  = callWS("A");
        Tables[] tablesB  = callWS("B");

        boolean found=true;
        for (Tables tables : tablesA) {
            tables.getRates().forEach(rate -> {
                for (Tables tables2 : tablesB) {
                    tables2.getRates().forEach(rate1 -> {
                        if (!rate.getCode().equalsIgnoreCase(rate1.getCode())) {
                            //jak sie nie uda to skopiowac do b
                            found = false;
                        }
                        //albo jak znajdzie to usunac z A a potem merge
                            tables2.getRates().add(rate);
                    });
                }
            });
        }
//            tableNames = tableNames.substring(1, tableNames.length()-1);
//            Tables tables = objectMapper.readValue(tableNames, Tables.class);
//            System.out.println(tables.getRates().get(0).getCurrency());
//            System.out.println(tables.getRates().get(0).getCode());
//            System.out.println(tables.getRates().get(0).getMid());
//            BigDecimal pln = BigDecimal.valueOf(100.00);
//            System.out.println(objectMapper.writeValueAsString(tableNames));
//            System.out.println(pln.divide(tables.getRates().get(0).getMid(), RoundingMode.HALF_EVEN));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
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
