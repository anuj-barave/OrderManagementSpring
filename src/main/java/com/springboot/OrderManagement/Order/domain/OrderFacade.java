package com.springboot.OrderManagement.Order.domain;

import com.springboot.OrderManagement.Customer.api.request.CustomerRequest;
import com.springboot.OrderManagement.Customer.domain.Customer;
import com.springboot.OrderManagement.Delivery.domain.Delivery;
import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Order.api.request.OrderRequest;
import com.springboot.OrderManagement.Payment.domain.Payment;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderFacade {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(long OrderId) {
        return orderRepository.findById(OrderId).orElse(null);
    }

    public Order addOrder(Order RequestOrder) {
        Order save = orderRepository.save(RequestOrder);
        return save;
    }

    public Order updateOrder(long OrderId, Order order) {
        Order order1 = orderRepository.findById(OrderId).orElse(null);
        assert order1 != null;
        order1.setDate(order.getDate());
        order1.setQuantity(order.getQuantity());
        order1.setFoodList(order.getFoodList());
        order1.setCustomer(order.getCustomer());
        order1.setPayment(order.getPayment());
        Order save = orderRepository.save(order1);
        return save;
    }

    public long deleteOrder(long OrderId) {
        Order order = orderRepository.findById(OrderId).orElse(null);
        orderRepository.deleteById(OrderId);
        return OrderId;
    }

    public Payment findPaymentByOrderId(long OrderId) {
        return orderRepository.findPaymentByOrderId(OrderId);
    }

    public Delivery findDeliveryByOrderId(long OrderId){
        return orderRepository.findDeliveryByOrderId(OrderId);
    }

    public List<Food> findFoodByOrderId(long Orderid){
        return orderRepository.findFoodByOrderId(Orderid);
    }

    public Customer findCustomerByOrderId(long Orderid){
        return orderRepository.findCustomerByOrderId(Orderid);
    }
}
