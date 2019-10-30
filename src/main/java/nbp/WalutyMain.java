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
import java.util.List;

public class WalutyMain {

    public static void main(String[] args) throws IOException {
        String[] tableNames = {"A","B"};
        List<Rate> merge=null;
//        try {
            ObjectMapper objectMapper = new ObjectMapper();
            for (String tableName : tableNames) {
                String output = callWS(tableName);
                Tables[] tables1 = objectMapper.readValue(output, Tables[].class);
                //polaczyc obie tabele w jedna bez powtorzen
                }
//            }
//            for (Tables tables : tables1) {
//                System.out.println(tables.getRates());
//            }
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


    private static String callWS(String table) throws IOException {
        String address = "http://api.nbp.pl/api/exchangerates/tables/"+table+"/?format=json";
        URL url = new URL(address);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        return new String(bufferedInputStream.readAllBytes());
    }
}
