package spring.springboot.entities;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Document(indexName = "products")
public class EsProductEntity {
    @NotEmpty
    private String id;

    @NotEmpty
    private String name;

    @NotNull
    private int quantity;

    @NotNull
    private Double price;
}
