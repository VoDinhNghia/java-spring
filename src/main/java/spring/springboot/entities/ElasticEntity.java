package spring.springboot.entities;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Document(indexName = "categoriess")
public class ElasticEntity {
    @NotEmpty
    private String id;

    @NotEmpty
    private String name;

    @Nullable
    private String description;
}
