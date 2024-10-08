package github.sagubr.services;

import github.sagubr.entities.core.Issuer;
import github.sagubr.entities.core.Order;
import github.sagubr.parser.DocumentJsoupParser;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;

import java.util.logging.Logger;

@Singleton
public class DocumentService {

    @Inject
    RequestJsoupService requestJsoupService;

    @Inject
    ProductService productService;

    @Inject
    IssuerService issuerService;

    @Inject
    OrderService orderService;

    public void getDocument(String url) throws HttpStatusException {

        Document document = requestJsoupService.requestAddress(url);
        DocumentJsoupParser documentJsoupParser = new DocumentJsoupParser(document);

        if (orderService.existsByKeyAccess(documentJsoupParser.getOrder().getKeyAccess())) {
            throw new RuntimeException("""
                    Chave de acesso já cadastrada: %s
                    """);
        }

        if (issuerService.existsByEnrolment(documentJsoupParser.getIssuer().getEnrolment())) {
            Issuer issuer = issuerService.findByEnrolment(documentJsoupParser.getIssuer().getEnrolment());
            documentJsoupParser.getOrder().setIssuer(issuer);
        } else {
            issuerService.save(documentJsoupParser.getIssuer());
        }
        Order order = documentJsoupParser.getOrder();
        order.setUrl(url);
        orderService.save(order);
        productService.save(documentJsoupParser.getProducts());

    }

}
