package com.yilmaz.ECommerce.dto.responses.orderItemResponses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class OrderItemDto {


    private String productName;
    private int quantity;
    private double price;

}
