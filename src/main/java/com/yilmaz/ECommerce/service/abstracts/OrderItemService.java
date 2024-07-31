package com.yilmaz.ECommerce.service.abstracts;

import com.yilmaz.ECommerce.dto.requests.orderItemRequests.CreateOrderItemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface OrderItemService {

    ResponseEntity<?> createOrderItem(CreateOrderItemRequest request);


}
