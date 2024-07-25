package com.yilmaz.ECommerce.model.concretes;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

;
import java.util.List;

@Table(name = "categories")
@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
