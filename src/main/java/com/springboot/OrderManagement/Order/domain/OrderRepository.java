package com.springboot.OrderManagement.Order.domain;

import com.springboot.OrderManagement.Customer.domain.Customer;
import com.springboot.OrderManagement.Delivery.domain.Delivery;
import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Order.domain.Order;
import com.springboot.OrderManagement.Payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface OrderRepository extends JpaRepository<Order,Long>{

    @Query("""
            select payment from Orders o where o.id =:orderId
            """)
    Payment findPaymentByOrderId(@Param("orderId") long orderId);

    @Query("""
            select delivery from Orders o where o.id =:orderId
            """)
    Delivery findDeliveryByOrderId(@Param("orderId") long orderId);

    @Query("""
            select foodList from Orders o where o.id =:orderId
            """)
    List<Food> findFoodByOrderId(@Param("orderId") long orderId);

    @Query("""
            select o.customer from Orders o inner join Customer c on o.customer.id = c.id
            where o.id = :orderId
            """)
    Customer findCustomerByOrderId(@Param("orderId") long orderId);

//    @Query("""
//            select o.foodlist from Orders o inner join OrderItems oi
//            on oi.order_id = o.id where o.id = :orderId
//            """)
//    List<Food> findFoodByOrderId(@Param("orderId") long orderId);
}
