package github.sagubr.services;

import jakarta.inject.Singleton;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Singleton
public class RequestJsoupService {

    private static final int TIMEOUT_MILLIS = 10000;

    public Document requestAddress(String url) throws HttpStatusException {
        try {
            Connection connection = Jsoup.connect(url);
            connection.userAgent("Mozilla");
            connection.timeout(TIMEOUT_MILLIS);

            return connection.get();
        } catch (HttpStatusException ex) {
            throw ex;
        } catch (IOException e) {
            throw new RuntimeException("Erro durante a solicitação HTTP", e);
        }
    }
}

