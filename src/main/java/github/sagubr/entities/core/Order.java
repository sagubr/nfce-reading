package github.sagubr.entities.core;

import github.sagubr.entities.EntityPattern;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order extends EntityPattern {

    @Column
    private LocalDateTime date;

    @ManyToOne
    private Issuer issuer;

    @Column(unique = true)
    private String keyAccess;

    public Order(LocalDateTime date, Issuer issuer, String keyAccess) {
        this.date = date;
        this.issuer = issuer;
        this.keyAccess = keyAccess;
    }

    public Order() {
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }

    public String getKeyAccess() {
        return keyAccess;
    }
}
