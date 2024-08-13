package com.yilmaz.ECommerce.model.dto.responses.orderReponses;

import com.yilmaz.ECommerce.model.dto.responses.orderItemResponses.OrderItemDto;
import com.yilmaz.ECommerce.model.concretes.PaymentType;
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
    private List<OrderItemDto> orderItems;
    private double totalPrice;
    private PaymentType paymentType;
    private String address;

}
