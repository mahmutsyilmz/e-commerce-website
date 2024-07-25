package com.yilmaz.ECommerce.dto.requests.orderRequests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class UpdateOrderRequest {
    private String orderNumber;
    private String status;
    private List<Long> productIds;
}
