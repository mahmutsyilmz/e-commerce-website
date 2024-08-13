package com.yilmaz.ECommerce.service.abstracts;

import com.yilmaz.ECommerce.model.dto.requests.orderRequests.CreateOrderRequest;
import com.yilmaz.ECommerce.model.dto.requests.orderRequests.UpdateOrderStatusRequest;
import com.yilmaz.ECommerce.model.dto.responses.orderReponses.GetAllOrdersResponse;
import com.yilmaz.ECommerce.model.concretes.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<GetAllOrdersResponse> getAllOrders();
     Order createOrder(CreateOrderRequest request);

     ResponseEntity<?> deleteOrder(Long id);


    ResponseEntity<?> updateOrderStatus(Long id, UpdateOrderStatusRequest request);

    List<GetAllOrdersResponse> getOrdersByUserId(Long id);

    Order findOrderWithOrderItems(Long id);

}
