package github.sagubr.services;

import github.sagubr.entities.core.Order;
import github.sagubr.repositories.OrderRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    public void save(Order order){
        orderRepository.save(order);
    }

    public boolean existsByKeyAccess(String keyAccess){
        return orderRepository.existsByKeyAccess(keyAccess);
    }

    public Order findByKeyAccess(String keyAccess){
        return orderRepository.findByKeyAccess(keyAccess);
    }

}
