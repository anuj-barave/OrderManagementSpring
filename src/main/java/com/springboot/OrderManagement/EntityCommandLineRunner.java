package com.springboot.OrderManagement;

import com.springboot.OrderManagement.Customer.domain.Customer;
import com.springboot.OrderManagement.Customer.domain.CustomerFacade;
import com.springboot.OrderManagement.Delivery.domain.Delivery;
import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Food.domain.FoodFacade;
import com.springboot.OrderManagement.Order.domain.Order;
import com.springboot.OrderManagement.Order.domain.OrderFacade;
import com.springboot.OrderManagement.Payment.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class EntityCommandLineRunner implements CommandLineRunner {

    @Autowired
    CustomerFacade customerFacade;
    @Autowired
    FoodFacade foodFacade;
    @Autowired
    OrderFacade orderFacade;

    @Override
    public void run(String... args) throws Exception {
        Order order2 = new Order();
        Customer CustomerOrder2 = customerFacade.getCustomerById(2);
        System.out.println(CustomerOrder2);
        if(CustomerOrder2!=null) {
            order2.setCustomer(CustomerOrder2);
            Food OrderFood = foodFacade.getFoodById(4);
            if (OrderFood != null) {
                List<Food> OrderFoodList = new ArrayList<>();
                OrderFoodList.add(OrderFood);
                order2.setFoodList(OrderFoodList);
                Payment payment = new Payment();
                payment.setMethod("Online");
                payment.setStatus("Paid");
                payment.setAmount(100);
                payment.setCustomer(CustomerOrder2);
                payment.setDate(new Date(2002, 02, 28));
                order2.setPayment(payment);
                Delivery delivery = new Delivery("paid");
                order2.setDelivery(delivery);
                order2.setDate(new Date(2023,14,03));
                order2.setQuantity(OrderFoodList.size());

            }
        }
        orderFacade.addOrder(order2);
    }

}
