package com.yilmaz.ECommerce.repository.abstracts;

import com.yilmaz.ECommerce.model.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //findByName,orElse
    List<Product> findByName(String name);
    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByNameContaining(String name);


}
