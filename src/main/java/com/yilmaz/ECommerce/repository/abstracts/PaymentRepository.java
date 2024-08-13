package com.yilmaz.ECommerce.repository.abstracts;

import com.yilmaz.ECommerce.model.concretes.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    //delete by order id
    void deleteByOrderId(Long orderId);
}
