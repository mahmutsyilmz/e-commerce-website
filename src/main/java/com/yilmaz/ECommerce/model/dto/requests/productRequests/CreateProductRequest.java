package com.yilmaz.ECommerce.model.dto.requests.productRequests;

import com.yilmaz.ECommerce.model.concretes.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateProductRequest {
    private String name;
    private String description;
    private double price;
    private int stock;
    private Long categoryId;
}
