package spring.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.springboot.entities.EsProductEntity;
import spring.springboot.entities.ProductEntity;
import spring.springboot.repositories.EsProductRepository;

@Service
public class EsProductService {
    @Autowired
    EsProductRepository esProductRepo;

    EsProductEntity esProductEntity = new EsProductEntity();

    public void createDocProduct(ProductEntity productDto) {
        esProductEntity.setId(productDto.getId().toString());
        esProductEntity.setName(productDto.getName());
        esProductEntity.setPrice(productDto.getPrice());
        esProductEntity.setQuantity(productDto.getQuantity());
        esProductRepo.save(esProductEntity);
    }

    public Iterable<EsProductEntity> findAllProduct() {
        Iterable<EsProductEntity> results = esProductRepo.findAll();
        return results;
    }
}
