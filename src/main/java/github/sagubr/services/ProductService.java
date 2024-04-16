package github.sagubr.services;

import github.sagubr.entities.core.Product;
import github.sagubr.repositories.ProductRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public void save(List<Product> products) {
        productRepository.saveAll(products);
    }

}

