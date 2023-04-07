package com.springboot.OrderManagement.Food.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodRequest {

    private String name;

    private double price;

    @JsonCreator
    public FoodRequest(String name, double price) {
        this.name = name;
        this.price = price;
    }

}
