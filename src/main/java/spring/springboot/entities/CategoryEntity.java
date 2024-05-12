package spring.springboot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

}
