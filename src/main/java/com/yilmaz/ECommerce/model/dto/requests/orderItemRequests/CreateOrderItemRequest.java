package com.yilmaz.ECommerce.model.dto.requests.orderItemRequests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class CreateOrderItemRequest {

    private Long orderId;
    private Long productId;
    private int quantity;
    private Long userId;

}
