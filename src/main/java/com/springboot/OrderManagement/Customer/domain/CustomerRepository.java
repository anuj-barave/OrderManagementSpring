package com.springboot.OrderManagement.Customer.domain;

import com.springboot.OrderManagement.Customer.domain.Customer;
import com.springboot.OrderManagement.Order.domain.Order;
import com.springboot.OrderManagement.Payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByName(String customerName);

    @Query("""
            select payments from Customer c 
            where c.id=:customerId
            """)
    List<Payment> retrieveCustomerPayments(@Param("customerId") long customerId);

    @Query("""
            select orders from Customer c 
            where c.id=:customerId
            """)
    List<Order> retrieveCustomerOrders(@Param("customerId") long customerId);

}
