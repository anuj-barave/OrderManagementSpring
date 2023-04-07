package com.springboot.OrderManagement.PaymentTest;

import com.springboot.OrderManagement.Food.FoodNotFoundException;
import com.springboot.OrderManagement.Food.controller.FoodController;
import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Food.service.FoodService;
import com.springboot.OrderManagement.Payment.api.response.PaymentResponse;
import com.springboot.OrderManagement.Payment.controller.PaymentController;
import com.springboot.OrderManagement.Payment.domain.Payment;
import com.springboot.OrderManagement.Payment.service.PaymentService;
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    private static String GENERIC_ORDERS_URL = "http://localhost:8000/payment";
    private static String SPECIFIC_ORDER_URL = "http://localhost:8000/payment/14";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

//    @Test
//    public void givenUnknownOrderId_willThrowNotFoundStatus() throws Exception {
//        //given
//        String FoodId = "id :"+411;
//        BDDMockito.given(foodService.getFoodItemById(anyLong())).willThrow(new FoodNotFoundException());
//        //when
//        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8000/food/411")).andExpect(status().isNotFound());
//    }

    @Test
    void givenPaymentId_shouldReturnPaymentDetails() throws Exception {
        //given
        long PaymentId = 14;
        String expectedResponse = """
                 {"id":14,"amount":100.0,"method":"Online","status":"Paid","date":"1970-01-01"}
                """;
        PaymentResponse payment = new PaymentResponse(14,100.0,"Online","Paid",new Date(3902-03-28));
        BDDMockito.given(paymentService.getPaymentById(PaymentId)).willReturn(payment);
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(SPECIFIC_ORDER_URL)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //then
        System.out.println(result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expectedResponse,result.getResponse().getContentAsString(),false);
    }

    @Test
    void methodShouldReturnListOfPayment() throws Exception {
        String expectedResponse = """
                [{"id":14,"amount":100.0,"method":"Online","status":"Paid","date":"1970-01-01"}]
                """;
        PaymentResponse payment = new PaymentResponse(14,100.0,"Online","Paid",new Date(3902-03-28));
        List<PaymentResponse> payments = new ArrayList<>();
        payments.add(payment);
        BDDMockito.given(paymentService.getAllPayments()).willReturn(payments);
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
    void givenOrder_shouldAddNewPayment_ReturnCreatedStatus() throws Exception {
        //given
        String inputOrder = """
                    {
                            "id": 14,
                            "amount": 100.0,
                            "method": "Online",
                            "status": "Paid",
                            "date": "3902-03-28"
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
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
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
