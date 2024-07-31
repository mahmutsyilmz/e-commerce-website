package com.yilmaz.ECommerce.service.concretes;

import com.yilmaz.ECommerce.dto.requests.orderItemRequests.CreateOrderItemRequest;
import com.yilmaz.ECommerce.mapper.ModelMapperService;
import com.yilmaz.ECommerce.model.concretes.OrderItem;
import com.yilmaz.ECommerce.model.concretes.Product;
import com.yilmaz.ECommerce.repository.abstracts.OrderItemRepository;
import com.yilmaz.ECommerce.repository.abstracts.ProductRepository;
import com.yilmaz.ECommerce.service.abstracts.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemManager implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final ModelMapperService modelMapperService;

    public OrderItemManager(OrderItemRepository orderItemRepository, ProductRepository productRepository, ModelMapperService modelMapperService) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public ResponseEntity<?> createOrderItem(CreateOrderItemRequest request) {
        OrderItem orderItem = modelMapperService.forRequest().map(request, OrderItem.class);
        Optional<Product> product = productRepository.findById(request.getProductId());

        double price = product.get().getPrice() * request.getQuantity();
        orderItem.setPrice(price);
        orderItemRepository.save(orderItem);
        return ResponseEntity.ok(request);

    }
}
