package com.springboot.OrderManagement.Order.controller;

import com.springboot.OrderManagement.Customer.domain.Customer;
import com.springboot.OrderManagement.Delivery.domain.Delivery;
import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Order.api.request.OrderRequest;
import com.springboot.OrderManagement.Order.api.request.UpdateOrderRequest;
import com.springboot.OrderManagement.Order.api.response.OrderResponse;
import com.springboot.OrderManagement.Order.service.OrderService;
import com.springboot.OrderManagement.Payment.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllDelivery() {
        List<OrderResponse> orderResponses = orderService.getallOrders();
        return ResponseEntity.ok(orderResponses);
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderResponse> getDeliveryById(@PathVariable long orderId) {
        final OrderResponse orderById = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderById);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> addDelivery(@RequestBody OrderRequest orderRequest) {
        final OrderResponse orderResponse = orderService.addOrder(orderRequest);
        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("{orderId}")
    public ResponseEntity<OrderResponse> updateDelivery(@PathVariable long orderId,@RequestBody UpdateOrderRequest UpdateOrderRequest) {
        OrderResponse orderResponse = orderService.UpdateOrder(orderId, UpdateOrderRequest);
        return ResponseEntity.ok(orderResponse);
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity<Long> deleteDelivery(@PathVariable long orderId) {
        System.out.println(orderId);
        final long DeleteOrderId = orderService.DeleteOrder(orderId);
        return ResponseEntity.ok(DeleteOrderId);
    }

    @GetMapping("/{orderId}/payment")
    public ResponseEntity<Payment> findPaymentByOrderId(@PathVariable long orderId){
        Payment payment = orderService.findPaymentByOrderId(orderId);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/{orderId}/delivery")
    public ResponseEntity<Delivery> findDeliveryByOrderId(@PathVariable long orderId){
        Delivery delivery = orderService.findDeliveryByOrderId(orderId);
        return ResponseEntity.ok(delivery);
    }

    @GetMapping("/{orderId}/food")
        public ResponseEntity<List<Food>> findFoodByOrderId(@PathVariable long orderId){
         List<Food> foodByOrderId = orderService.findFoodByOrderId(orderId);
         return ResponseEntity.ok(foodByOrderId);
        }

    @GetMapping("/{orderId}/customer")
    public ResponseEntity<Customer> findCustomerByOrderId(@PathVariable long orderId){
        Customer customer = orderService.findCustomerByOrderId(orderId);
        return ResponseEntity.ok(customer);
    }

}
