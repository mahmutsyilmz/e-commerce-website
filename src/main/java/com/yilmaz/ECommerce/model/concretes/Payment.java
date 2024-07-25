package com.yilmaz.ECommerce.model.concretes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;


}
