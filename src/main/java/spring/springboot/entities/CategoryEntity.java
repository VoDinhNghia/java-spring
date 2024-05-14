package spring.springboot.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity()
@Table(name = "categories")
@Getter()
@Setter()
public class CategoryEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = true, length = 2000)
    private String description;

    @OneToMany(mappedBy = "categories", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("categories")
    private List<ProductEntity> products = new ArrayList<>();

}
