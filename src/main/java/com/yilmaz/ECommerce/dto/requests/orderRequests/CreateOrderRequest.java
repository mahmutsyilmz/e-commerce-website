package com.yilmaz.ECommerce.dto.requests.orderRequests;

import com.yilmaz.ECommerce.model.concretes.Order;
import com.yilmaz.ECommerce.model.concretes.OrderItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
public class CreateOrderRequest {
    private String orderNumber;
    private Date orderDate;
    private String status;
    private Long userId;
    private List<OrderItem> orderItems;
}
