package com.springboot.OrderManagement.Payment.domain;

import com.springboot.OrderManagement.Food.domain.Food;
import com.springboot.OrderManagement.Payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query("select p from Payment p where p.customer.id = 1")
    public List<Payment> selectAllPaymentForCustomer();
}
