package com.yilmaz.ECommerce.dto.responses.orderReponses;

import com.yilmaz.ECommerce.dto.requests.orderItemRequests.CreateOrderItemRequest;
import com.yilmaz.ECommerce.model.concretes.OrderItem;
import com.yilmaz.ECommerce.model.concretes.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class GetAllOrdersResponse {
    private Long id;
    private String orderNumber;
    private Date orderDate;
    private String status;
    private String userEmail;
    private List<CreateOrderItemRequest> orderItems;
}
