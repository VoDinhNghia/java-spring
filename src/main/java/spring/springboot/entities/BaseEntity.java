package spring.springboot.entities;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @PrimaryKeyJoinColumn()
    protected UUID id;

    @Column(updatable = false)
    @CreationTimestamp
    protected Date createdAt;

    @Column()
    @UpdateTimestamp
    protected Date updatedAt;

    @Column(nullable = true)
    protected Date deletedAt;
}
