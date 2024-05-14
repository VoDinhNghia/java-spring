package spring.springboot.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProductDto {
    private String id;

    @NotEmpty
    private String categoryId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String origin;

    @NotEmpty
    private int quantity;

    @NotEmpty
    private double price;

    @Nullable
    private String description;
}
