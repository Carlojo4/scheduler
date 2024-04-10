package co.com.carlos.scheduler.scheduler.utils;

import co.com.carlos.scheduler.scheduler.controller.SchedulerController;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HeaderScraper {

    private static final Logger logger = LogManager.getLogger(HeaderScraper.class);

    static final int MAX_CHARS = 1000;

    public static String scrapeHeaders(final String url) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpGet request = new HttpGet(url);
            final CloseableHttpResponse response = httpClient.execute(request);

            final int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new Exception("Unsuccessful request: " + statusCode);
            }

            // Get headers as a map
            final StringBuilder headers = new StringBuilder();
            for (org.apache.http.Header header : response.getAllHeaders()) {
                headers.append(header.getName()).append(": ").append(header.getValue()).append("\n");
            }

            final String scrapedHeaders = headers.toString();
            if (scrapedHeaders.length() > MAX_CHARS) {
                return scrapedHeaders.substring(0, MAX_CHARS);
            }
            return scrapedHeaders;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Error trying to scrap headers for "
                    .concat(url)
                    .concat(" error: ")
                    .concat(e.getMessage()));
        }
    }
}