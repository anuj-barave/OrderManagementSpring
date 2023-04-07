package com.springboot.OrderManagement.Order.api.response;

import com.springboot.OrderManagement.Customer.api.response.CustomerResponse;
import com.springboot.OrderManagement.Customer.domain.Customer;
import com.springboot.OrderManagement.Delivery.api.request.DeliveryRequest;
import com.springboot.OrderManagement.Delivery.api.response.DeliveryResponse;
import com.springboot.OrderManagement.Delivery.domain.Delivery;
import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Payment.api.response.PaymentResponse;
import com.springboot.OrderManagement.Payment.domain.Payment;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private long id;
    private long quantity;
    private Date date;
    private CustomerResponse customer;
    private List<Food> foodList;
    private DeliveryResponse delivery;
    private Payment payment;

    public OrderResponse(long id, long quantity, Date date, CustomerResponse customer, List<Food> foodList, DeliveryResponse delivery, Payment payment) {
        this.id = id;
        this.quantity = quantity;
        this.date = date;
        this.customer = customer;
        this.foodList = foodList;
        this.delivery = delivery;
        this.payment = payment;
    }
}
