package com.springboot.OrderManagement.FoodTest;


import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Food.domain.FoodFacade;
import com.springboot.OrderManagement.Food.service.FoodService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {

    @InjectMocks
    FoodService foodService;

    @Mock
    FoodFacade foodFacade;

    @Captor
    ArgumentCaptor<Long> captor;


    @Test
    public void retrieveSpecificFoodByIdReturnFoodResponseObject() {
        Food food = new Food(2,"Burger",50.00);
        when(foodFacade.getFoodById(2)).thenReturn(food);
        assertEquals(food,foodService.getFoodItemById(2));
        verify(foodFacade,atLeastOnce()).getFoodById(2);
    }

    @Test
    public void ArgumentCaptorForretrieveSpecificStudentByIdReturnStudentObject() {
        Food food = new Food(2,"Burger",50.00);
        foodService.getFoodItemById(2);
        verify(foodFacade).getFoodById((captor.capture()));
        long l = captor.getValue();
        assertEquals(l,2L);
    }

    @Test
    public void retrieveAllFoodReturnFoodList() {
        Food food = new Food(2,"Burger",50.00);
        Food food1 = new Food(2,"Pizza",100.00);
        List<Food> foodList = new ArrayList<>();
        foodList.add(food1);
        foodList.add(food);
        when(foodFacade.getAllFoods()).thenReturn(foodList);
        assertEquals(foodList,foodService.getAllFoodItem());
        verify(foodFacade,atLeastOnce()).getAllFoods();
    }

    @Test
    public void deleteSpecificFoodByIdReturnDeletedItem()
    {
        Food food = new Food(2,"Burger",50.00);
        when(foodFacade.deleteFood(2)).thenReturn(food);
        assertEquals(food,foodService.deleteFoodItem(2));
        verify(foodFacade,atLeastOnce()).deleteFood(2);
    }



}
