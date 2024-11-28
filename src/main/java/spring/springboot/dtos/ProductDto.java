package spring.springboot.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {
    @NotEmpty
    private String categoryId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String origin;

    @NotNull
    private int quantity;

    @NotNull
    private double price;

    @Nullable
    private String description;
}
