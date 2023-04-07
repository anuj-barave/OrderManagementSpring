package com.springboot.OrderManagement.Delivery.domain;

import com.springboot.OrderManagement.Order.domain.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String status;
    @OneToOne(mappedBy = "delivery")
    Order order;
    public Delivery(String status) {
        this.status = status;
    }

}
