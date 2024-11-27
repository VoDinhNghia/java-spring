package spring.springboot.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.RefreshPolicy;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = { "spring.springboot.repositories" })
public class ElasticsearchConfig extends ElasticsearchConfiguration {
      @Override
      public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder().connectedTo("localhost:9200").build();
      }

      @Override
        protected RefreshPolicy refreshPolicy() {
          return RefreshPolicy.NONE;
      }
}
