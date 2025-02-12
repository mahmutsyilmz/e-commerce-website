package com.yilmaz.ECommerce.repository.abstracts;

import com.yilmaz.ECommerce.model.concretes.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserId(Long userId);


    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems WHERE o.id = :id")
    Order findOrderWithOrderItems(Long id);

}
