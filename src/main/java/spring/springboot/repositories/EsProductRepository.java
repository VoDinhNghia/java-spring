package spring.springboot.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import spring.springboot.entities.EsProductEntity;

public interface EsProductRepository extends ElasticsearchRepository<EsProductEntity, String> {
}
