package github.sagubr.parser;

import github.sagubr.Application;
import github.sagubr.entities.core.Issuer;
import github.sagubr.entities.core.Order;
import github.sagubr.entities.core.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class DocumentJsoupParser {

    private static final Logger log = Logger.getLogger("DocumentJsoupParser");

    private final Document document;
    private final Order order;
    private final Issuer issuer;
    private List<Product> products = new ArrayList<>();

    public DocumentJsoupParser(Document document) {
        this.document = Jsoup.parse(String.valueOf(document));
        this.issuer = createIssuer();
        this.order = createOrder();
        this.products = createProducts();
    }

    private Issuer createIssuer() {

        log.info("CRIANDO FORNECEDOR...");
        Elements tables = this.document.select("table");
        Elements rows = tables.get(6).select("tbody > tr");
        Elements columns = rows.get(0).select("td");

        String name = columns.get(0).text();
        String enrolment = columns.get(1).text();

        return new Issuer(name, enrolment);

    }

    private Order createOrder() {

        log.info("CRIANDO ORDEM...");
        Elements tables = this.document.select("table");
        Elements td = tables.get(4).select("td");

        String keyAccess = String.join("", Objects.requireNonNull(td.first())
                .text()
                .split("\\D+"));

        Elements rows = tables.get(8).select("tbody > tr");
        Elements column = rows.get(0).select("td");

        String date = column.get(3)
                .text()
                .trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return new Order(
                LocalDateTime.parse(date, formatter),
                this.issuer,
                keyAccess
        );

    }

    private List<Product> createProducts() {

        log.info("CRIANDO PRODUTO(S)...");

        Elements tables = this.document.select("table");
        Elements rows = tables.get(1).select("tbody > tr");

        for (Element row : rows) {

            Elements column = row.select("td");

            String description = column.get(0)
                    .text()
                    .trim();

            String quantity = column.get(1)
                    .text()
                    .split(":\\s*", 2)[1]
                    .trim();

            String unitOfMeasure = column.get(2)
                    .text()
                    .split(":\\s*", 2)[1]
                    .trim();

            String priceBeforeConvert = column.get(3)
                    .text()
                    .split(":\\s*")[1]
                    .replaceAll("[^\\d,]*", "")
                    .replace(",", ".").trim();

            double price = Double.parseDouble(priceBeforeConvert);

            products.add(new Product(
                    this.order,
                    description,
                    Double.valueOf(quantity),
                    unitOfMeasure,
                    BigDecimal.valueOf(price)
            ));

        }

        return products;
    }

    public Order getOrder() {
        return order;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public List<Product> getProducts() {
        return products;
    }
}
