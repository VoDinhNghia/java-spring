package spring.springboot.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import spring.springboot.entities.ElasticEntity;

public interface ElasticRepository extends ElasticsearchRepository<ElasticEntity, String> {
}
