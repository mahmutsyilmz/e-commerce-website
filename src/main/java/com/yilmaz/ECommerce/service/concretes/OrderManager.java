package com.yilmaz.ECommerce.service.concretes;

import com.yilmaz.ECommerce.dto.requests.orderRequests.CreateOrderRequest;
import com.yilmaz.ECommerce.dto.responses.orderReponses.GetAllOrdersResponse;
import com.yilmaz.ECommerce.mapper.ModelMapperService;
import com.yilmaz.ECommerce.model.concretes.Order;
import com.yilmaz.ECommerce.repository.abstracts.OrderRepository;
import com.yilmaz.ECommerce.service.abstracts.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderManager implements OrderService {

        private final OrderRepository orderRepository;
        private final ModelMapperService modelMapperService;

    public OrderManager(OrderRepository orderRepository, ModelMapperService modelMapperService) {
        this.orderRepository = orderRepository;
        this.modelMapperService = modelMapperService;
    }


    public ResponseEntity<List<GetAllOrdersResponse>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<GetAllOrdersResponse> responses = orders.stream()
                .map(order -> modelMapperService.forResponse().map(order, GetAllOrdersResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<?> createOrder(CreateOrderRequest request) {
        Order order = modelMapperService.forRequest().map(request, Order.class);
        orderRepository.save(order);
        return ResponseEntity.ok(request);
    }
}
