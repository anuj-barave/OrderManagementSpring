package com.springboot.OrderManagement.Order.service;


import com.springboot.OrderManagement.Customer.api.response.CustomerResponse;
import com.springboot.OrderManagement.Customer.domain.Customer;
import com.springboot.OrderManagement.Customer.domain.CustomerFacade;
import com.springboot.OrderManagement.Customer.service.CustomerService;
import com.springboot.OrderManagement.Delivery.api.response.DeliveryResponse;
import com.springboot.OrderManagement.Delivery.domain.Delivery;
import com.springboot.OrderManagement.Delivery.domain.DeliveryFacade;
import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Food.domain.FoodFacade;
import com.springboot.OrderManagement.Order.api.request.OrderRequest;
import com.springboot.OrderManagement.Order.api.request.UpdateOrderRequest;
import com.springboot.OrderManagement.Order.api.response.OrderResponse;
import com.springboot.OrderManagement.Order.domain.Order;
import com.springboot.OrderManagement.Order.domain.OrderFacade;
import com.springboot.OrderManagement.Payment.domain.Payment;
import com.springboot.OrderManagement.Payment.domain.PaymentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderFacade orderFacade;
    @Autowired
    CustomerFacade customerFacade;
    @Autowired
    FoodFacade foodFacade;
    @Autowired
    DeliveryFacade deliveryFacade;
    @Autowired
    PaymentFacade paymentFacade;

    public List<OrderResponse> getallOrders() {
        List<Order> allOrders = orderFacade.getAllOrders();
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order:allOrders) {
            orderResponses.add(new OrderResponse(order.getId(),order.getQuantity(),order.getDate(),
                    new CustomerResponse(order.getCustomer().getId(),order.getCustomer().getName(),order.getCustomer().getAddress(),order.getCustomer().getPhone()),
                    order.getFoodList(),
                    new DeliveryResponse(order.getDelivery().getId(),order.getDelivery().getStatus()),order.getPayment()));
        }
        return orderResponses;
    }

    public OrderResponse getOrderById(long orderId) {
        Order order = orderFacade.getOrderById(orderId);
        return new OrderResponse(order.getId(),order.getQuantity(),order.getDate(),
                new CustomerResponse(order.getCustomer().getId(),order.getCustomer().getName(),order.getCustomer().getAddress(),order.getCustomer().getPhone()),
                order.getFoodList(),new DeliveryResponse(order.getDelivery().getId(),order.getDelivery().getStatus()),order.getPayment());
    }

    public OrderResponse addOrder(OrderRequest orderRequest) {
        Customer customer = customerFacade.getCustomerById(orderRequest.getCustomerId());
        System.out.println(customer.getName());
        System.out.println(orderRequest.getCustomerId());
        Order order = new Order();
        order.setQuantity(orderRequest.getQuantity());
        order.setDate(orderRequest.getDate());
        order.setCustomer(customer);
        List<Food> foods = new ArrayList<>();
        for (Long foodById:orderRequest.getFoodids()) {
            foods.add(foodFacade.getFoodById(foodById));
        }
        order.setFoodList(foods);
        Payment payment = new Payment();
        payment.setMethod("Offline");
        payment.setStatus("Unpaid");
        payment.setAmount(2001);
        payment.setCustomer(customer);
        payment.setDate(new Date(2002, 02, 28));
        order.setPayment(payment);
        order.setDelivery(new Delivery("pending"));
        order.setDate(new Date(2023,14,03));
        order.setQuantity(foods.size());
        Order addedOrder = orderFacade.addOrder(order);
        return new OrderResponse(addedOrder.getId(),addedOrder.getQuantity(),addedOrder.getDate(),
                new CustomerResponse(addedOrder.getCustomer().getId(),addedOrder.getCustomer().getName(),addedOrder.getCustomer().getAddress(),addedOrder.getCustomer().getPhone()),
                addedOrder.getFoodList(),new DeliveryResponse(addedOrder.getDelivery().getId(),addedOrder.getDelivery().getStatus()),addedOrder.getPayment());
    }

    public OrderResponse UpdateOrder(long orderId, UpdateOrderRequest updateOrderRequest) {
        Customer customer = customerFacade.getCustomerById(updateOrderRequest.getCustomerId());
        Order order = new Order();
        order.setQuantity(updateOrderRequest.getQuantity());
        order.setDate(updateOrderRequest.getDate());
        order.setCustomer(customer);
        List<Food> foods = new ArrayList<>();
        for (Long id:updateOrderRequest.getFoodids()) {
            Food OrderFood = foodFacade.getFoodById(id);
            foods.add(OrderFood);
        }
        order.setFoodList(foods);
        order.setDelivery(deliveryFacade.getDeliveryById(updateOrderRequest.getDeliveryId()));
        order.setPayment(paymentFacade.getPaymentById(updateOrderRequest.getPaymentId()));
        Order updatedOrder = orderFacade.updateOrder(orderId,order);
        return new OrderResponse(updatedOrder.getId(),updatedOrder.getQuantity(),updatedOrder.getDate(),
                new CustomerResponse(updatedOrder.getCustomer().getId(),updatedOrder.getCustomer().getName(),updatedOrder.getCustomer().getAddress(),updatedOrder.getCustomer().getPhone()),
                order.getFoodList(),new DeliveryResponse(updatedOrder.getDelivery().getId(),updatedOrder.getDelivery().getStatus()),updatedOrder.getPayment());
    }

    public long DeleteOrder(long orderId) {
        return orderFacade.deleteOrder(orderId);
    }

    public Payment findPaymentByOrderId(long OrderId) {
        return orderFacade.findPaymentByOrderId(OrderId);
    }

    public Delivery findDeliveryByOrderId(long OrderId){
        return orderFacade.findDeliveryByOrderId(OrderId);
    }

    public List<Food> findFoodByOrderId(long OrderId){
        return orderFacade.findFoodByOrderId(OrderId);
    }

    public Customer findCustomerByOrderId(long OrderId){
        return orderFacade.findCustomerByOrderId(OrderId);
    }
}
