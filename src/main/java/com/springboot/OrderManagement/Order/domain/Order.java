package com.springboot.OrderManagement.Order.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.OrderManagement.Customer.domain.Customer;
import com.springboot.OrderManagement.Delivery.domain.Delivery;
import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Payment.domain.Payment;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long quantity;

    private Date date;
    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonIgnore
    Customer customer;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="payment_id")
    @JsonIgnore
    Payment payment;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="delivery_id")
    @JsonIgnore
    Delivery delivery;
    @ManyToMany
    @JoinTable(name = "order_foods",
            joinColumns = @JoinColumn (name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> foodList;

}
