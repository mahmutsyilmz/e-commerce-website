package com.yilmaz.ECommerce.model.dto.requests.paymentRequests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class PaymentRequest {
    private Long orderId;
    private String paymentType;
    private double amount;
}
