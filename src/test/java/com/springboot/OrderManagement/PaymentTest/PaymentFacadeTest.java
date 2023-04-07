package com.springboot.OrderManagement.PaymentTest;

import com.springboot.OrderManagement.Customer.domain.Customer;
import com.springboot.OrderManagement.Customer.domain.CustomerFacade;
import com.springboot.OrderManagement.Food.FoodNotFoundException;
import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Payment.api.response.PaymentResponse;
import com.springboot.OrderManagement.Payment.domain.Payment;
import com.springboot.OrderManagement.Payment.domain.PaymentFacade;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@RunWith(SpringRunner.class)
public class PaymentFacadeTest {

    @Autowired
    PaymentFacade paymentFacade;

    @Test
    public void givenPaymentFacadeRequest_toAddNewPayment_shouldAddNewPayment(){
        //given
//        PaymentResponse paymentResponse = new PaymentResponse(14,100.0,"Online","Paid",new Date(1970-01-01));
        Payment payment = new Payment(100.0,"Online","Paid",new Date(1970-03-07));
        Customer customer= new Customer("Vrushaket","Jalgoan","9766984161");
        customer.setId(2);
        payment.setCustomer(customer);
        //when
        Payment actualPaymentResponse = paymentFacade.addPayment(payment);
        //then
        Assertions.assertThat(payment.getId()).isEqualTo(actualPaymentResponse.getId());
    }
//
//    @Test(expected = FoodNotFoundException.class)
//    public void givenUnKnownFoodId_toDeleteFood_shouldThrowFoodNotFoundExpection(){
//        //given
//        long FoodId = 1000;
//        //when
//        foodFacade.deleteFood(FoodId);
//    }

    @Test
    public void givenRequest_toDeletePayment_shouldDeletePayment(){
        //given
        long PaymentID = 14L;
        Payment expectedpaymentresponse = new Payment(100.0,"Online","paid",new Date(1970-01-01));
        expectedpaymentresponse.setId(14);
//        {"id":14,"amount":100.0,"method":"Online","status":"Paid","date":"1970-01-01"}
        //when
        Payment actualPaymentResponse = paymentFacade.deletePayment(PaymentID);
        //then
//        Assertions.assertThat(expectedpaymentresponse.getId()).isEqualTo(actualPaymentResponse.getId());
    }

    @Test
    public void givenRequest_toGetPayment_shouldGetPayment(){
        //given
        long PaymentID = 14L;
        Payment expectedpaymentresponse = new Payment(100.0,"Online","Paid",new Date(1970-01-01));
        expectedpaymentresponse.setId(14);
        //when
        Payment actualPaymentResponse = paymentFacade.getPaymentById(PaymentID);
        //then
        Assertions.assertThat(expectedpaymentresponse.toString()).isEqualTo(actualPaymentResponse.toString());
    }
}
