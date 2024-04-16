package github.sagubr.repositories;

import github.sagubr.entities.core.Order;
import io.micronaut.data.annotation.Repository;

@Repository
public interface OrderRepository extends PatternRepository<Order> {

    boolean existsByKeyAccess(String keyAccess);

    Order findByKeyAccess(String keyAccess);

}
