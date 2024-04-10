package co.com.carlos.scheduler.scheduler.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Pinger {

    public static String ping(String url) throws IOException {
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000); // Set timeout in milliseconds
            connection.connect();
            return connection.getResponseMessage();
        } catch (Exception e) {
            throw new RuntimeException("Error trying to ping "
                    .concat(url)
                    .concat(" error: ")
                    .concat(e.getMessage()));
        }
    }

}
