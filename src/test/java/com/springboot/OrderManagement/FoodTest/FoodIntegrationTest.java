package com.springboot.OrderManagement.FoodTest;

import com.springboot.OrderManagement.Food.controller.FoodController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class FoodIntegrationTest {

    private static String GENERIC_FOOD_URL = "http://localhost:8000/food";
    private static String SPECIFIC_FOOD_URL = "http://localhost:8000/food/2";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FoodController foodResource;

    @Test
    void methodShouldReturnListOfFood() throws Exception {

        String expectedResponse = """
                [
                    {
                        "id": 3,
                        "name": "PavBhaji",
                        "price": 80.0
                    },
                    {
                        "id": 6,
                        "name": "Spring Roll",
                        "price": 120.0
                    },
                ]
                """;
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(GENERIC_FOOD_URL)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //then
        //Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(expectedResponse.trim());
        JSONAssert.assertEquals(expectedResponse,result.getResponse().getContentAsString(),false);
    }

//    @TestS
//    void givenFoodId_shouldReturn404() throws Exception {
//        //given
//        String UNKNOWN_FOOD_URL = "http://localhost:8000/foods/1001";
//
//        //when
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get(UNKNOWN_FOOD_URL)
//                .accept(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//        //then
//        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(404);
//    }

    @Test
    void givenFoodId_shouldReturnFoodDetails() throws Exception {
        //given
        String expectedResponse = """
                {
                        "id": 2,
                        "name": "Misal",
                        "price": 65.0
                }
                """;
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(SPECIFIC_FOOD_URL)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //then
        //Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(expectedResponse.trim());
        JSONAssert.assertEquals(expectedResponse,result.getResponse().getContentAsString(),true);
    }
//
    @Test
    void givenFood_shouldAddNewFood() throws Exception {
        //given
        String inputFood = """
                {"name":"Veg Roll","price":120}
                """;
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(GENERIC_FOOD_URL)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputFood)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //then
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(201);
    }

    @Test
    void givenFoodId_shouldDeleteFood() throws Exception {
        //given
        String SPECIFIC_FOOD_URL = "http://localhost:8000/food/5";
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(SPECIFIC_FOOD_URL)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //then
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(204);
    }

    @Test
    void givenFoodIdAndFood_shouldUpdateFoodForGivenFoodId() throws Exception {
        //given
        String SPECIFIC_FOOD_URL = "http://localhost:8000/food/4";
        String inputFood = """
                {
                    "name": "Fries",
                    "price": 50
                }
                """;
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(SPECIFIC_FOOD_URL)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputFood)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //then
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

}
