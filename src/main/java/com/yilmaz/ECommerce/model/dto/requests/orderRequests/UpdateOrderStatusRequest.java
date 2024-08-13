package com.yilmaz.ECommerce.model.dto.requests.orderRequests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UpdateOrderStatusRequest {
    private String status;
}
