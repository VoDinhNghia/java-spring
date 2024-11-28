package spring.springboot.entities;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Document(indexName = "products")
public class EsProductEntity {
    @NotEmpty
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private int quantity;

    @NotEmpty
    private Double price;
}
