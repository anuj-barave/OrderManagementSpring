package com.springboot.OrderManagement.Food;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException(String message) {
        super(message);
    }
}


