package github.sagubr.repositories;

import github.sagubr.entities.core.Product;
import io.micronaut.data.annotation.Repository;

@Repository
public interface ProductRepository extends PatternRepository<Product> {
}
