package com.springboot.OrderManagement.Customer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springboot.OrderManagement.Order.domain.Order;
import com.springboot.OrderManagement.Payment.domain.Payment;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String address;

    private String phone;
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    List<Order> orders;
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    List<Payment> payments;

    public Customer(String name,String address,String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
