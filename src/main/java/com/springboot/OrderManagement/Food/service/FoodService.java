package com.springboot.OrderManagement.Food.service;

import com.springboot.OrderManagement.Food.api.request.FoodRequest;
import com.springboot.OrderManagement.Food.api.response.FoodResponse;
import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Food.domain.FoodFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

    @Autowired
    FoodFacade foodFacade;

    public FoodService(FoodFacade foodFacade) {
        this.foodFacade = foodFacade;
    }

    public List<Food> getAllFoodItem() {
        return foodFacade.getAllFoods();
    }

    public Food getFoodItemById(long FoodId) {
        return foodFacade.getFoodById(FoodId);
    }

    public Food addFoodItem(FoodRequest foodRequest) {
        return foodFacade.addFood(foodRequest);
    }

    public Food updateFoodItem(long FoodId, FoodRequest foodRequest) {
         return foodFacade.updateFood(FoodId, foodRequest);
    }

    public Food deleteFoodItem(long FoodId) {
        return foodFacade.deleteFood(FoodId);
    }
}
