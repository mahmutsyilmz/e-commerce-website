package com.yilmaz.ECommerce.model.dto.requests.productRequests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DeleteProductRequest {
    private Long id;
}