package spring.springboot.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDto {
    @NotEmpty
    private String name;

    @Nullable
    private String description;
}
