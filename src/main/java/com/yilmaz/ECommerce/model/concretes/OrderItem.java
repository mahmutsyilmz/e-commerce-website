package com.yilmaz.ECommerce.model.concretes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Table(name = "order_items")
@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private double price;


}
