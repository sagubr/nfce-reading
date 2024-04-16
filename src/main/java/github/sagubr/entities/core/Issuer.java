package github.sagubr.entities.core;

import github.sagubr.entities.EntityPattern;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "issuers")
public class Issuer extends EntityPattern {

    @Column
    private String name;

    @Column(unique = true)
    private String enrolment;

    public Issuer(String name, String enrolment) {
        this.name = name;
        this.enrolment = enrolment;
    }

    public Issuer() {
    }

    public String getName() {
        return name;
    }

    public String getEnrolment() {
        return enrolment;
    }

}
