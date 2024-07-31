package com.yilmaz.ECommerce.dto.responses.orderReponses;

import com.yilmaz.ECommerce.dto.responses.orderItemResponses.OrderItemDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class GetAllOrdersByUserIdResponse {
    private Long id;
    private String orderNumber;
    private Date orderDate;
    private String status;
    private String userEmail;
    private List<OrderItemDto> orderItems;
    private double totalPrice;


}
