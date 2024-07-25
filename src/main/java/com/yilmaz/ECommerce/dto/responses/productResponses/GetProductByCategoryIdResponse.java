package com.yilmaz.ECommerce.dto.responses.productResponses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GetProductByCategoryIdResponse {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Long categoryId;
    private int stock;
}
