package com.yilmaz.ECommerce.service.concretes;

import com.yilmaz.ECommerce.dto.requests.orderItemRequests.CreateOrderItemRequest;
import com.yilmaz.ECommerce.mapper.ModelMapperService;
import com.yilmaz.ECommerce.model.concretes.OrderItem;
import com.yilmaz.ECommerce.repository.abstracts.OrderItemRepository;
import com.yilmaz.ECommerce.service.abstracts.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderItemManager implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    private final ModelMapperService modelMapperService;

    public OrderItemManager(OrderItemRepository orderItemRepository, ModelMapperService modelMapperService) {
        this.orderItemRepository = orderItemRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public ResponseEntity<?> createOrderItem(CreateOrderItemRequest request) {
        OrderItem orderItem = modelMapperService.forRequest().map(request, OrderItem.class);
        orderItemRepository.save(orderItem);
        return ResponseEntity.ok(request);

    }
}
