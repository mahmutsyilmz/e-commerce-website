package com.yilmaz.ECommerce.service.concretes;

import com.yilmaz.ECommerce.model.dto.requests.paymentRequests.PaymentRequest;
import com.yilmaz.ECommerce.core.mapper.ModelMapperService;
import com.yilmaz.ECommerce.model.concretes.Order;
import com.yilmaz.ECommerce.model.concretes.OrderStatus;
import com.yilmaz.ECommerce.model.concretes.Payment;
import com.yilmaz.ECommerce.repository.abstracts.OrderRepository;
import com.yilmaz.ECommerce.repository.abstracts.PaymentRepository;
import com.yilmaz.ECommerce.service.abstracts.OrderService;
import com.yilmaz.ECommerce.service.abstracts.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentManager implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapperService modelMapperService;
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public PaymentManager(PaymentRepository paymentRepository, ModelMapperService modelMapperService, OrderService orderService, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.modelMapperService = modelMapperService;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }


    @Override
    public ResponseEntity<?> pay(PaymentRequest request) {
        Payment payment = modelMapperService.forRequest().map(request, Payment.class);
        Order order = orderRepository.findById(request.getOrderId()).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(OrderStatus.PAID);
        //order.setPayment(request.getPaymentType());
        request.setAmount(calculateTotalPrice(order));
        orderRepository.save(order);
        paymentRepository.save(payment);


        return ResponseEntity.ok("Order paid successfully");
    }

    private double calculateTotalPrice(Order order) {
        return order.getOrderItems().stream()
                .mapToDouble(orderItem -> orderItem.getProduct().getPrice() * orderItem.getQuantity())
                .sum();
    }







}
