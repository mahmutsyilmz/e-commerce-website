package com.yilmaz.ECommerce.service.concretes;

import com.yilmaz.ECommerce.model.dto.requests.orderItemRequests.CreateOrderItemRequest;
import com.yilmaz.ECommerce.core.mapper.ModelMapperService;
import com.yilmaz.ECommerce.model.concretes.Order;
import com.yilmaz.ECommerce.model.concretes.OrderItem;
import com.yilmaz.ECommerce.model.concretes.Product;
import com.yilmaz.ECommerce.repository.abstracts.OrderItemRepository;
import com.yilmaz.ECommerce.repository.abstracts.OrderRepository;
import com.yilmaz.ECommerce.repository.abstracts.ProductRepository;
import com.yilmaz.ECommerce.service.abstracts.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemManager implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final ModelMapperService modelMapperService;
    private final OrderRepository orderRepository;

    public OrderItemManager(OrderItemRepository orderItemRepository, ProductRepository productRepository, ModelMapperService modelMapperService, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.modelMapperService = modelMapperService;
        this.orderRepository = orderRepository;
    }


    @Override
    public ResponseEntity<?> createOrderItem(CreateOrderItemRequest request) {

        OrderItem orderItem = modelMapperService.forRequest().map(request, OrderItem.class);
        Optional<Product> product = productRepository.findById(request.getProductId());

        double price = product.get().getPrice() * request.getQuantity();
        // yuvarla ve ondalıktan sonra 2 basamak gözüksün
        price = Math.round(price * 100.0) / 100.0;
        orderItem.setPrice(price);

        // Order nesnesini bul
        Optional<Order> order = orderRepository.findById(request.getOrderId());

        if (order.isPresent()) {
            // Order nesnesini OrderItem nesnesine ayarla
            orderItem.setOrder(order.get());
            // Ürün stok miktarını güncelle
            if (product.get().getStock() < request.getQuantity()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock is not enough");
            }else {
                product.get().setStock(product.get().getStock() - request.getQuantity());
            }

            // OrderItem nesnesini kaydet
            orderItemRepository.save(orderItem);

            return ResponseEntity.ok(request);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order not found");
        }
    }

    @Override
    public ResponseEntity<Void> deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
