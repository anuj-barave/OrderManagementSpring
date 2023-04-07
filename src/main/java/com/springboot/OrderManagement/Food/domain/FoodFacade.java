package com.springboot.OrderManagement.Food.domain;

import com.springboot.OrderManagement.Food.FoodNotFoundException;
import com.springboot.OrderManagement.Food.api.request.FoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodFacade {

    @Autowired
    private  FoodRepository foodRepository;

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Food getFoodById(long id) {
        Food food =  foodRepository.findById(id).orElse(null);
        if(food==null) {
            String message = "id :"+id;
            throw new FoodNotFoundException(message);
        }
        return food;
    }

    public Food addFood(FoodRequest foodRequest) {
        Food food = new Food(foodRequest.getName(),foodRequest.getPrice());
        foodRepository.save(food);
        return food;
    }

    public Food updateFood(long FoodId, FoodRequest foodRequest) {
        Food food = foodRepository.findById(FoodId).orElse(null);
        assert food != null;
        food.setName(foodRequest.getName());
        food.setPrice(foodRequest.getPrice());
        foodRepository.save(food);
        return food;
    }

    public Food deleteFood(long foodId) {
        Food food = foodRepository.findById(foodId).orElse(null);
        if(food==null) {
            String message = "id :"+foodId;
            throw new FoodNotFoundException(message);
        }
        foodRepository.deleteById(foodId);
        return food;
    }
}
