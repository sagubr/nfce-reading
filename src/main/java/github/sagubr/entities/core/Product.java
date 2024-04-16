package github.sagubr.entities.core;

import github.sagubr.entities.EntityPattern;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Serdeable
public class Product extends EntityPattern {

    @ManyToOne
    private Order order;

    @Column
    private String description;

    @Column
    private Double quantity;

    @Column
    private String unitOfMeasure;

    @Column
    private BigDecimal price;

    public Product(Order order, String description, Double quantity, String unitOfMeasure, BigDecimal price) {
        this.order = order;
        this.description = description;
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
        this.price = price;
    }

    public Product() {

    }

    public String getDescription() {
        return description;
    }

    public Double getQuantity() {
        return quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
