package github.sagubr.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class EntityPattern {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Version
    @Column(nullable = true)
    Integer version;

    @DateCreated
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime createIn;

    @DateUpdated
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime updateIn;

    @PrePersist
    public void prePersist() {
        createIn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updateIn = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }
}
