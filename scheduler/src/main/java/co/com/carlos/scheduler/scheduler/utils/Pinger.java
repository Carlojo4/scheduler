package co.com.carlos.scheduler.scheduler.utils;

import co.com.carlos.scheduler.scheduler.exception.InvalidUrlException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Pinger {
    private static final Logger logger = LogManager.getLogger(Pinger.class);
    public static String ping(String url) throws IOException {

        try {
            final URL urlObj = new URL(url);
            final HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000); // Set timeout in milliseconds
            connection.connect();
            return connection.getResponseMessage();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InvalidUrlException("Error trying to ping "
                    .concat(url)
                    .concat(" error: ")
                    .concat(e.getMessage()));
        }
    }

}
