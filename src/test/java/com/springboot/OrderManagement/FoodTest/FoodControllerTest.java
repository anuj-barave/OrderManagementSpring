package com.springboot.OrderManagement.FoodTest;


import com.springboot.OrderManagement.Food.FoodNotFoundException;
import com.springboot.OrderManagement.Food.controller.FoodController;
import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Food.service.FoodService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FoodController.class)
public class FoodControllerTest {
    private static String GENERIC_ORDERS_URL = "http://localhost:8000/food";
    private static String SPECIFIC_ORDER_URL = "http://localhost:8000/food/3";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodService foodService;

    @Test
    public void givenUnknownOrderId_willThrowNotFoundStatus() throws Exception {
        //given
        String FoodId = "id :"+411;
        BDDMockito.given(foodService.getFoodItemById(anyLong())).willThrow(new FoodNotFoundException());
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8000/food/411")).andExpect(status().isNotFound());
    }
    @Test
    void givenOrderId_shouldReturnFoodDetails() throws Exception {
        //given
        long FoodId = 3;
        String expectedResponse = """
                {
                    "id": 3,
                    "name": "PavBhaji",
                    "price": 80.0
                }
                """;
        Food food = new Food(3,"PavBhaji",80.0);
        BDDMockito.given(foodService.getFoodItemById(FoodId)).willReturn(food);
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(SPECIFIC_ORDER_URL)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //then
        JSONAssert.assertEquals(expectedResponse,result.getResponse().getContentAsString(),false);
    }

    @Test
    void methodShouldReturnListOfFood() throws Exception {
        String expectedResponse = """
                [
                {
                    "id": 3,
                   
                    "name": "PavBhaji",
                    "price": 80.0
                    }
                 {
                        "id": 6,
                        "name": "Spring Roll",
                        "price": 120.0
                    }
                ]
                """;
        Food food = new Food(3,"PavBhaji",80.0);
        Food food1 = new Food(6,"Spring Roll",120.0);
        List<Food> foodList = new ArrayList<>();
        foodList.add(food1);
        foodList.add(food);
        BDDMockito.given(foodService.getAllFoodItem()).willReturn(foodList);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get(GENERIC_ORDERS_URL))
                .andExpect(status().isOk())
                .andReturn();
        //then
        System.out.println(mvcResult.getResponse().getContentAsString());
        JSONAssert.assertEquals(expectedResponse,mvcResult.getResponse().getContentAsString(),false);
    }

    @Test
    void givenOrder_shouldAddNewOrder() throws Exception {
        //given
        String inputOrder = """
                    {
                        "name": "Panner Roll",
                        "price": 120.0
                    }
                """;
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(GENERIC_ORDERS_URL)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputOrder)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //then
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(201);
    }
//
//    @Test
//    void givenOrderIdAndOrder_shouldUpdateOrderForGivenOrderId() throws Exception {
//        //given
//        long orderId = 5;
//        String expectedOrderResponse = """
//                {"id":0,"date":"3923-04-13","quantity":1,"foods":[{"id":0,"name":"Fries","price":80}],"payment":{"id":0,"customerId":3,"amount":200,"method":"Cash","status":"success","date":"3923-04-12"},"customer":{"id":0,"name":"Abhijeet","address":"Mumbai","phone":"9944561278"},"delivery":{"id":0,"status":"waiting"}}
//                """;
//
//        List<Food> foods = new ArrayList<>();
//        foods.add(new Food("Fries",80));
//        Payment payment = new Payment(3l,200,"Cash","success",new Date(2023,03,12));
//        Delivery delivery = new Delivery("waiting");
//        Customer customer= new Customer("Abhijeet","Mumbai","9944561278");
//        OrderRequest orderRequest = new OrderRequest(3l,0l,0l,new Date(2023,03,12),1,foods);
//        OrderResponse orderResponse = new OrderResponse(0l, new Date(2023,03,13),1,foods,payment,customer,delivery);
//        BDDMockito.given(orderService.updateOrder(orderId,orderRequest)).willReturn(orderResponse);
//
//        //when
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
//                        .put(SPECIFIC_ORDER_URL)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(expectedOrderResponse))
//                .andExpect(status().isOk())
//                .andReturn();
//        //then
//        System.out.println("res: "+mvcResult.getResponse().getContentAsString());
//        Assertions.assertThat(expectedOrderResponse.trim()).isEqualTo(mvcResult.getResponse().getContentAsString());
//    }
    @Test
    void givenOrderId_shouldDeleteFood() throws Exception {
        //given

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(SPECIFIC_ORDER_URL)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //then
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(204);
    }

}
