package com.springboot.OrderManagement.Food.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoodResponse {

    private long id;

    private String name;

    private double price;

    @JsonCreator
    public FoodResponse(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
