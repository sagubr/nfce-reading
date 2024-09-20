package github.sagubr.services;

import github.sagubr.entities.Address;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jsoup.HttpStatusException;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class InspectorService {

    @Inject
    private DocumentService documentService;

    public List<HttpResponse<String>> processUrl(Address address) {
        List<HttpResponse<String>> status = new ArrayList<>();

        try {
            address.getUrl().forEach(url -> {
                try {
                    documentService.getDocument(url.replace("|", "%7C"));

                    status.add(HttpResponse.status(HttpStatus.ACCEPTED).body("""
                            URL processada com sucesso:\s
                            """ + url));
                } catch (HttpStatusException ex) {
                    if (ex.getStatusCode() == HttpStatus.NOT_FOUND.getCode()) {
                        status.add(HttpResponse.status(HttpStatus.NOT_FOUND).body(
                                """
                                        URL não encontrada:\s
                                        """ + url));

                    } else {
                        status.add(HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body("""
                                Erro ao processar a URL:\s
                                """ + ex.getMessage()));
                    }
                }
            });
            return status;
        } catch (Exception e) {
            status.add(HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body("""
                    Erro interno ao processar a URL:\s
                    """ + e.getMessage()));
            return status;
        }
    }
}
