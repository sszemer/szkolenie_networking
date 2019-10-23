package nbp;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WalutyMain {

    public static void main(String[] args) {
    String address = "http://api.nbp.pl/api/exchangerates/tables/B/?format=json";
        try {
            URL url = new URL(address);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            String output = new String(bufferedInputStream.readAllBytes());
            output = output.substring(1, output.length()-1);
            ObjectMapper objectMapper = new ObjectMapper();
            Tables tables = objectMapper.readValue(output, Tables.class);
            System.out.println(tables.getRates().get(0).getCurrency());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
