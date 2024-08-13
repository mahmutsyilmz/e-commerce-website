package com.yilmaz.ECommerce.model.dto.responses.productResponses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GetProductByIdResponse {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Long categoryId;
    private int stock;
}
