package github.sagubr.services;

import github.sagubr.entities.Address;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jsoup.HttpStatusException;

@Singleton
public class InspectorService {

    @Inject
    private DocumentService documentService;

    public HttpResponse<String> processUrl(Address address) {
        try {
            documentService.getDocument(
                    address.getUrl().replace("|", "%7C")
            );

            return HttpResponse.status(HttpStatus.ACCEPTED).body("""
        URL processada com sucesso:\s
        """ + address.getUrl());
        } catch (HttpStatusException ex) {

            if (ex.getStatusCode() == HttpStatus.NOT_FOUND.getCode()) {
                return HttpResponse.status(HttpStatus.NOT_FOUND).body(
                        """
                                URL não encontrada:\s
                                """ + address.getUrl());
            } else {
                return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body("""
                        Erro ao processar a URL:\s
                        """ + ex.getMessage());
            }
        } catch (Exception e) {
            return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body("""
                    Erro interno ao processar a URL:\s
                    """ + e.getMessage());
        }
    }
}
