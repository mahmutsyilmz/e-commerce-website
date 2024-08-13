package com.yilmaz.ECommerce.service.abstracts;

import com.yilmaz.ECommerce.model.dto.requests.orderItemRequests.CreateOrderItemRequest;
import org.springframework.http.ResponseEntity;


public interface OrderItemService {

    ResponseEntity<?> createOrderItem(CreateOrderItemRequest request);
    ResponseEntity<Void> deleteOrderItem(Long id);


}
