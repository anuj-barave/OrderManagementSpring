package com.springboot.OrderManagement.FoodTest;

import com.springboot.OrderManagement.Food.FoodNotFoundException;
import com.springboot.OrderManagement.Food.api.request.FoodRequest;
import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Food.domain.FoodFacade;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@RunWith(SpringRunner.class)
public class FoodFacdeTest {

    @Autowired
    private FoodFacade foodFacade;

//    @Test(expected = CustomerNotFoundException.class)
//    public void givenUnknownCustomerId_toRetrieveCustomer_shouldThrowCustomerNotFoundException(){
//        //given
//        long customerId = 111l;
//        //when
//        customerFacade.retrieveSpecificCustomer(customerId);
//    }

    @Test
    public void givenPaymentId_toRetrievePayment_shouldReturnPaymentResponse(){
        //given
        long customerId = 1l;
        Food expectedFoodResponse = new Food(3,"PavBhaji",80.0);
        //when
        Food food = foodFacade.getFoodById(3);
        //then
        Assert.assertEquals(expectedFoodResponse.getId(),food.getId());
    }

//    @Test
//    public void givenRequestToRetrieveAllCustomer_shouldReturnAllCustomersResponse() throws JSONException {
//        //given
//        Customer customer1 = new Customer("Vrushaket","Pune","9595068833");
//        customer1.setId(1l);
//        Customer customer2 = new Customer("Abhijeet","Mumbai","9944561278");
//        customer2.setId(2);
//        Customer customer3 = new Customer("Chetan","Jalgaon","7588612345");
//        customer3.setId(3l);
//        Customer customer5 = new Customer("Anuj","Alandi","8623951209");
//        customer5.setId(5l);
//        Customer customer6 = new Customer("Om","Pune","8623951210");
//        customer6.setId(6l);
//        Customer customer7 = new Customer("Raj","Pune","1234567890");
//        customer7.setId(7l);
//
//        List<Customer> expectedCustomerList = new ArrayList<>();
//        expectedCustomerList.add(customer1);
//        expectedCustomerList.add(customer2);
//        expectedCustomerList.add(customer3);
//        expectedCustomerList.add(customer5);
//        expectedCustomerList.add(customer6);
//        expectedCustomerList.add(customer7);
//        //when
//        List<Customer> actualCustomerList = customerFacade.retrieveAllCustomer();
//        //then
//        System.out.println(expectedCustomerList);
//        Assertions.assertThat(actualCustomerList.toString()).isEqualTo(expectedCustomerList.toString());
//    }

    @Test
    public void givenFoodRequest_toAddNewFood_shouldAddNewFood(){
        //given
        FoodRequest foodRequest = new FoodRequest("Rice",40);
        Food food = new Food("Rice",40);
        //when
        Food actualFoodResponse = foodFacade.addFood(foodRequest);
        food.setId(actualFoodResponse.getId());
        //then
        Assertions.assertThat(food.getId()).isEqualTo(actualFoodResponse.getId());
    }

    @Test(expected = FoodNotFoundException.class)
    public void givenUnKnownFoodId_toDeleteFood_shouldThrowFoodNotFoundExpection(){
        //given
        long FoodId = 1000;
        //when
        foodFacade.deleteFood(FoodId);
    }

    @Test
    public void givenCustomerRequest_toDeleteCustomer_shouldDeleteCustomer(){
        //given
        long FoodId = 11L;
        Food expectedCustomerResponse = new Food(11,"Spring Roll",120);
        //when
        Food actualFoodResponse = foodFacade.deleteFood(FoodId);
        //then
        Assertions.assertThat(expectedCustomerResponse.getId()).isEqualTo(actualFoodResponse.getId());
    }

//    @Test(expected = CustomerNotFoundException.class)
//    public void givenInvalidCustomerId_toUpdateCustomer_shouldThrowCustomerNotFoundException(){
//        //given
//        long customerId = 1000;
//        CustomerRequest customerRequest = new CustomerRequest("Raj","Pune","1234567890");
//        //when
//        customerFacade.updateCustomer(customerId,customerRequest);
//    }
//
//    @Test
//    public void givenCustomerId_toUpdateCustomer_shouldUpdateCustomer(){
//        //given
//        long customerId = 7l;
//        Customer expectedCustomerResponse = new Customer("Raj","Pune","9876543210");
//        expectedCustomerResponse.setId(customerId);
//        CustomerRequest customerRequest = new CustomerRequest("Raj","Pune","9876543210");
//        //when
//        Customer actualCustomerResponse = customerFacade.updateCustomer(customerId,customerRequest);
//        //then
//        Assertions.assertThat(expectedCustomerResponse.toString()).isEqualTo(actualCustomerResponse.toString());
//    }
//
//    @Test
//    public void givenCustomerId_toRetrieveCustomerOrders_shouldReturnCustomerOrders(){
//        //given
//        long customerId = 2l;
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 2023);
//        cal.set(Calendar.MONTH, Calendar.MARCH);
//        cal.set(Calendar.DAY_OF_MONTH, 13);
//        long dateInMillis = cal.getTimeInMillis();
//        Order order = new Order(2l,2l,2l, new java.sql.Date(dateInMillis) ,2,null);
//        order.setId(4l);
//        List<Order> expectedCustomerOrders = new ArrayList<>();
//        expectedCustomerOrders.add(order);
//        //when
//        List<Order> actualCustomerOrders = customerFacade.retrieveCustomerOrders(customerId);
//        //then
//        System.out.println(actualCustomerOrders.toString());
//        Assertions.assertThat(expectedCustomerOrders.toString()).isEqualTo(actualCustomerOrders.toString());
//    }
//
//    @Test
//    public void givenCustomerPhone_toRetrieveCustomerOrders_shouldReturnCustomerOrders(){
//        //given
//        String phone = "9944561278";
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 2023);
//        cal.set(Calendar.MONTH, Calendar.MARCH);
//        cal.set(Calendar.DAY_OF_MONTH, 13);
//        long dateInMillis = cal.getTimeInMillis();
//        Order order = new Order(2l,2l,2l, new java.sql.Date(dateInMillis) ,2,null);
//        order.setId(4l);
//        List<Order> expectedCustomerOrders = new ArrayList<>();
//        expectedCustomerOrders.add(order);
//        //when
//        List<Order> actualCustomerOrders = customerFacade.retrieveCustomerOrdersByPhone(phone);
//        //then
//        System.out.println(actualCustomerOrders.toString());
//        Assertions.assertThat(expectedCustomerOrders.toString()).isEqualTo(actualCustomerOrders.toString());
//    }
//
//    @Test
//    public void givenCustomerId_toRetrieveCustomerPayment_shouldReturnCustomerPayments(){
//        //given
//        long customerId = 2l;
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 2023);
//        cal.set(Calendar.MONTH, Calendar.MARCH);
//        cal.set(Calendar.DAY_OF_MONTH, 11);
//        long dateInMillis = cal.getTimeInMillis();
//        Payment payment = new Payment(2l,1500,"Debit Card","success",new java.sql.Date(dateInMillis));
//        payment.setId(2l);
//        Customer customer = new Customer("Abhijeet","Mumbai","9944561278");
//        customer.setId(customerId);
//        payment.setCustomer(customer);
//        List<Payment> expectedCustomerPayments = new ArrayList<>();
//        expectedCustomerPayments.add(payment);
//        //when
//        List<Payment> actualCustomerPayments = customerFacade.retrieveCustomerPayments(customerId);
//        //then
//        System.out.println(actualCustomerPayments.toString());
//        Assertions.assertThat(expectedCustomerPayments.toString()).isEqualTo(actualCustomerPayments.toString());
//    }

}
