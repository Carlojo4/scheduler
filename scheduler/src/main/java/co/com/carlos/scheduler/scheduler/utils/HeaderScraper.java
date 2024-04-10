package co.com.carlos.scheduler.scheduler.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HeaderScraper {

    static final int MAX_CHARS = 1000;

    public static String scrapeHeaders(String url) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new Exception("Unsuccessful request: " + statusCode);
            }

            // Get headers as a map
            StringBuilder headers = new StringBuilder();
            for (org.apache.http.Header header : response.getAllHeaders()) {
                headers.append(header.getName()).append(": ").append(header.getValue()).append("\n");
            }

            String scrapedHeaders = headers.toString();
            if (scrapedHeaders.length() > MAX_CHARS) {
                return scrapedHeaders.substring(0, MAX_CHARS);
            }
            return scrapedHeaders;
        } catch (Exception e) {
            throw new RuntimeException("Error trying to scrap headers for "
                    .concat(url)
                    .concat(" error: ")
                    .concat(e.getMessage()));
        }
    }
}