package com.springboot.OrderManagement.Food.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.OrderManagement.Order.domain.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;

    public Food()
    {

    }

    public Food(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "foodList")
    List<Order> orders;

    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
