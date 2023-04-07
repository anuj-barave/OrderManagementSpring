package com.springboot.OrderManagement.Food.controller;

import com.springboot.OrderManagement.Food.api.request.FoodRequest;
import com.springboot.OrderManagement.Food.api.response.FoodResponse;
import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @GetMapping
    public ResponseEntity<List<FoodResponse>> getAllFood()
    {
        List<Food> foodList = foodService.getAllFoodItem();
        List<FoodResponse> foodResponses = new ArrayList<>();
        for (Food food:foodList) {
            foodResponses.add(new FoodResponse(food.getId(),food.getName(),food.getPrice()));
        }
        return ResponseEntity.ok(foodResponses);
    }

    @GetMapping("{foodId}")
    public ResponseEntity<FoodResponse> getFoodById(@PathVariable long foodId)
    {
        Food food = foodService.getFoodItemById(foodId);
        if(food != null)
            return ResponseEntity.notFound().build();
        FoodResponse foodResponse = new FoodResponse(food.getId(), food.getName(), food.getPrice());
        return ResponseEntity.ok(foodResponse);
    }

    @PostMapping
    public ResponseEntity<FoodResponse> addFoodItem(@RequestBody FoodRequest foodRequest)
    {
        Food food = foodService.addFoodItem(foodRequest);
        FoodResponse foodResponse = new FoodResponse(food.getId(), food.getName(), food.getPrice());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{foodId}").buildAndExpand(foodResponse.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{FoodId}")
    public ResponseEntity<FoodResponse> updateFood(@PathVariable long FoodId,@RequestBody FoodRequest foodRequest)
    {
        Food food = foodService.updateFoodItem(FoodId, foodRequest);
        FoodResponse foodResponse = new FoodResponse(food.getId(), food.getName(), food.getPrice());
        return ResponseEntity.ok(foodResponse);
    }

    @DeleteMapping("{FoodId}")
    public ResponseEntity<Object> deleteFood(@PathVariable long FoodId)
    {
        foodService.deleteFoodItem(FoodId);
        return ResponseEntity.noContent().build();
    }
}
