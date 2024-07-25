package com.yilmaz.ECommerce.service.abstracts;

import com.yilmaz.ECommerce.dto.requests.orderRequests.CreateOrderRequest;
import com.yilmaz.ECommerce.dto.responses.orderReponses.GetAllOrdersResponse;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    ResponseEntity<List<GetAllOrdersResponse>> getAllOrders();
    ResponseEntity<?> createOrder(CreateOrderRequest request);



}
