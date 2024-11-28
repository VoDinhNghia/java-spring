package spring.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.springboot.entities.ProductEntity;
import spring.springboot.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepo;

    public ProductEntity createProduct(ProductEntity entity) {
        ProductEntity result = productRepo.save(entity);
        return result;
    }
}
