package com.yilmaz.ECommerce.service.concretes;

import com.yilmaz.ECommerce.dto.requests.orderRequests.CreateOrderRequest;
import com.yilmaz.ECommerce.dto.requests.orderRequests.UpdateOrderStatusRequest;
import com.yilmaz.ECommerce.dto.responses.orderReponses.GetAllOrdersByUserIdResponse;
import com.yilmaz.ECommerce.dto.responses.orderReponses.GetAllOrdersResponse;
import com.yilmaz.ECommerce.mapper.ModelMapperService;
import com.yilmaz.ECommerce.model.concretes.Order;
import com.yilmaz.ECommerce.model.concretes.OrderStatus;
import com.yilmaz.ECommerce.model.concretes.User;
import com.yilmaz.ECommerce.repository.abstracts.OrderRepository;
import com.yilmaz.ECommerce.repository.abstracts.UserRepository;
import com.yilmaz.ECommerce.service.abstracts.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderManager implements OrderService {

        private final OrderRepository orderRepository;
        private final ModelMapperService modelMapperService;
        private final UserRepository userRepository;

    public OrderManager(OrderRepository orderRepository, ModelMapperService modelMapperService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.modelMapperService = modelMapperService;
        this.userRepository = userRepository;
    }


    public ResponseEntity<List<GetAllOrdersResponse>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<GetAllOrdersResponse> responses = orders.stream()
                .map(order -> modelMapperService.forResponse().map(order, GetAllOrdersResponse.class))
                .collect(Collectors.toList());
        calculateTotalPrice(responses);
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<?> createOrder(CreateOrderRequest request) {
        request.init();
        Order order = new Order();
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        order.setUser(user);
        order.setOrderNumber(request.getOrderNumber());
        order.setOrderDate(request.getOrderDate());
        order.setStatus(OrderStatus.valueOf(request.getStatus()));

        orderRepository.save(order);
        return ResponseEntity.ok(request);
    }

    @Override
    public ResponseEntity<?> updateOrderStatus(Long id, UpdateOrderStatusRequest request) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(OrderStatus.valueOf(request.getStatus()));
        orderRepository.save(order);
        return ResponseEntity.ok(request);
    }

    @Override
    public ResponseEntity<List<GetAllOrdersResponse>> getOrdersByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Order> orders = orderRepository.findAllByUserId(user.getId());
        List<GetAllOrdersResponse> responses = orders.stream()
                .map(order -> modelMapperService.forResponse().map(order, GetAllOrdersResponse.class))
                .collect(Collectors.toList());

        calculateTotalPrice(responses);
        return ResponseEntity.ok(responses);
    }

    private void calculateTotalPrice(List<GetAllOrdersResponse> responses) {
        //GetAllOrdersByUserIdResponse sınıfında totalPrice var. onu hesaplamak istiyorum
        //hesaplama işlemini yapmak için OrderItem sınıfına ihtiyacım var. OrderItem sınıfı Order sınıfı ile ilişkili
        //OrderItem sınıfı Order sınıfı ile ilişkili olduğu için Order sınıfında orderItems listesini çekiyorum
        //OrderItem sınıfında price ve quantity var. Bu iki değeri çekip çarparak totalPrice hesaplıyorum
        //Hesaplanan totalPrice değerini response nesnesine set ediyorum
        responses.forEach(response -> {
            double totalPrice = response.getOrderItems().stream()
                    .map(orderItem -> orderItem.getPrice())
                    .reduce(0.0, Double::sum);
            response.setTotalPrice(totalPrice);
        });
    }


}
