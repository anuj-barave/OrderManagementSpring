package com.springboot.OrderManagement.Payment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.OrderManagement.Customer.domain.Customer;
import com.springboot.OrderManagement.Order.domain.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;

@NoArgsConstructor
@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double amount;

    private String method;

    private String status;

    private Date date;
    @JsonIgnore
    @OneToOne(mappedBy = "payment")
    private Order order;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Payment(double amount,String method,String status,Date date) {
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }
}
