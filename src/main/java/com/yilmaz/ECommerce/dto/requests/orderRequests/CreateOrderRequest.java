package com.yilmaz.ECommerce.dto.requests.orderRequests;

import com.yilmaz.ECommerce.model.concretes.Order;
import com.yilmaz.ECommerce.model.concretes.OrderItem;
import com.yilmaz.ECommerce.model.concretes.OrderStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;


@Setter
@Getter
public class CreateOrderRequest {

    private Long orderNumber;
    private Date orderDate;
    private Long userId;
    private String status;



    public CreateOrderRequest(Long orderNumber, Long userId) {
        this.orderDate = new Date();
        this.userId = userId;
        this.status = OrderStatus.PENDING.name();
        this.orderNumber = orderNumber;
    }


    // Manuel init methodu
    public void init() {
        if (this.orderDate == null) {
            this.orderDate = new Date(); // Set current date and time
        }
        if (this.status == null) {
            this.status = OrderStatus.PENDING.name(); // Default status
        }
    }

}
