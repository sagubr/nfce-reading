package github.sagubr.repositories;

import github.sagubr.entities.core.Issuer;
import io.micronaut.data.annotation.Repository;

@Repository
public interface IssuerRepository extends PatternRepository<Issuer> {

    Issuer findByEnrolment(String enrolment);

    boolean existsByEnrolment(String enrolment);
}
