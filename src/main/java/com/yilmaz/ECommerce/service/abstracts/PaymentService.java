package com.yilmaz.ECommerce.service.abstracts;

import com.yilmaz.ECommerce.model.dto.requests.paymentRequests.PaymentRequest;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<?> pay(PaymentRequest request);



}
