package com.yilmaz.ECommerce.model.dto.responses.orderItemResponses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class OrderItemDto {

    private Long id;
    private String productName;
    private int quantity;
    private double price;

}
