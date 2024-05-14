package spring.springboot.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity()
@Table(name = "products")
@Getter()
@Setter()
public class ProductEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 500)
    private String origin;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = true, length = 2000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories.id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIgnoreProperties("products")
    private CategoryEntity categories;

}
