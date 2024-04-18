package github.sagubr.services;

import jakarta.inject.Singleton;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Logger;

@Singleton
public class RequestJsoupService {

    private static final int TIMEOUT_MILLIS = 10000;
    private static final Logger log = Logger.getLogger("");

    public Document requestAddress(String url) throws HttpStatusException {
        try {
            log.info("TENTANDO CONEXÃO: " + url);
            Connection connection = Jsoup.connect(url);
            connection.userAgent("Mozilla");
            connection.timeout(TIMEOUT_MILLIS);

            return connection.get();
        } catch (HttpStatusException ex) {
            throw ex;
        } catch (IOException e) {
            log.warning("FALHA NA SOLICITAÇÃO: " + url);
            throw new RuntimeException("Erro durante a solicitação HTTP", e);
        }
    }
}

