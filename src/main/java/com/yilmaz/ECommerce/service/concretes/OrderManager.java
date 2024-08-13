package com.yilmaz.ECommerce.service.concretes;

import com.yilmaz.ECommerce.model.dto.requests.orderRequests.CreateOrderRequest;
import com.yilmaz.ECommerce.model.dto.requests.orderRequests.UpdateOrderStatusRequest;
import com.yilmaz.ECommerce.model.dto.responses.orderReponses.GetAllOrdersResponse;
import com.yilmaz.ECommerce.core.mapper.ModelMapperService;
import com.yilmaz.ECommerce.model.concretes.Order;
import com.yilmaz.ECommerce.model.concretes.OrderItem;
import com.yilmaz.ECommerce.model.concretes.OrderStatus;
import com.yilmaz.ECommerce.model.concretes.User;
import com.yilmaz.ECommerce.repository.abstracts.OrderItemRepository;
import com.yilmaz.ECommerce.repository.abstracts.OrderRepository;
import com.yilmaz.ECommerce.repository.abstracts.PaymentRepository;
import com.yilmaz.ECommerce.repository.abstracts.UserRepository;
import com.yilmaz.ECommerce.service.abstracts.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderManager implements OrderService {

        private final OrderRepository orderRepository;
        private final ModelMapperService modelMapperService;
        private final UserRepository userRepository;
        private final OrderItemRepository orderItemRepository;
        private final PaymentRepository paymentRepository;

    public OrderManager(OrderRepository orderRepository, ModelMapperService modelMapperService, UserRepository userRepository, OrderItemRepository orderItemRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.modelMapperService = modelMapperService;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
        this.paymentRepository = paymentRepository;
    }


    @Override
    public List<GetAllOrdersResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<GetAllOrdersResponse> responses = orders.stream()
                .map(order -> {
                    GetAllOrdersResponse response = modelMapperService.forResponse().map(order, GetAllOrdersResponse.class);
                    User user = userRepository.findById(order.getUser().getId()).orElse(null);
                    if (user != null) {
                        response.setAddress(user.getAddress());
                    }
                    return response;
                })
                .collect(Collectors.toList());
        calculateTotalPrice(responses);

        return responses;
    }





    @Override
    public Order createOrder(CreateOrderRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        if (checkIfOrderIsPending(user.getId())) {
            Order pendingOrder = findPendingOrder(user.getId());
            return pendingOrder;
        }
        request.init();
        Order order = new Order();
        order.setUser(user);
        order.setOrderNumber(request.getOrderNumber());
        order.setOrderDate(request.getOrderDate());
        order.setStatus(OrderStatus.valueOf(request.getStatus()));


        orderRepository.save(order);
        return order;
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteOrder(Long id) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(id);

        // Sipariş öğelerini sil
        orderItemRepository.deleteAll(orderItems);
        //ödeme yöntemini sil
        paymentRepository.deleteByOrderId(id);
        orderRepository.deleteById(id);
        return ResponseEntity.ok("Order deleted");
    }


    @Override
    public ResponseEntity<?> updateOrderStatus(Long id, UpdateOrderStatusRequest request) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(OrderStatus.valueOf(request.getStatus()));
        orderRepository.save(order);
        return ResponseEntity.ok(request);
    }


    @Override
    public Order findOrderWithOrderItems(Long id) {
        return orderRepository.findOrderWithOrderItems(id);
    }



    @Override
    public List<GetAllOrdersResponse> getOrdersByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Order> orders = orderRepository.findAllByUserId(user.getId());
        List<GetAllOrdersResponse> responses = orders.stream()
                .map(order -> modelMapperService.forResponse().map(order, GetAllOrdersResponse.class))
                .collect(Collectors.toList());

        calculateTotalPrice(responses);
        responses.forEach(response -> response.setPaymentType(response.getPaymentType()));
        return responses;
    }

    public void calculateTotalPrice(List<GetAllOrdersResponse> responses) {
        //GetAllOrdersByUserIdResponse sınıfında totalPrice var. onu hesaplamak istiyorum
        //hesaplama işlemini yapmak için OrderItem sınıfına ihtiyacım var. OrderItem sınıfı Order sınıfı ile ilişkili
        //OrderItem sınıfı Order sınıfı ile ilişkili olduğu için Order sınıfında orderItems listesini çekiyorum
        //OrderItem sınıfında price ve quantity var. Bu iki değeri çekip çarparak totalPrice hesaplıyorum
        //Hesaplanan totalPrice değerini response nesnesine set ediyorum
        responses.forEach(response -> {
            double totalPrice = response.getOrderItems().stream()
                    .map(orderItem -> orderItem.getPrice())
                    .reduce(0.0, Double::sum);
            //ondalıktan sonra 2 rakama yuvarla, math round ile
            totalPrice = Math.round(totalPrice * 100.0) / 100.0;
            response.setTotalPrice(totalPrice);
        });
    }

    private boolean checkIfOrderIsPending(Long userId) {
        List<Order> existingOrders = orderRepository.findAllByUserId(userId);
        for (Order existingOrder : existingOrders) {
            if (existingOrder.getStatus().equals(OrderStatus.PENDING)) {
                return true;
            }
        }
        return false;
    }

    private Order findPendingOrder(Long userId) {
        List<Order> existingOrders = orderRepository.findAllByUserId(userId);
        for (Order existingOrder : existingOrders) {
            if (existingOrder.getStatus().equals(OrderStatus.PENDING)) {
                return existingOrder;
            }
        }
        return null;
    }


}
