package com.springboot.OrderManagement.Payment.domain;

import com.springboot.OrderManagement.Payment.api.request.PaymentRequest;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PaymentFacade {

    @Autowired
    PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(long id) {
        System.out.println(id);
        Payment payment =  paymentRepository.findById(id).orElse(null);
        return payment;
    }

    public Payment addPayment(Payment paymentRequest) {
        Payment save = paymentRepository.save(paymentRequest);
        return save;
    }

    public Payment updatePayment(long PaymentId, PaymentRequest paymentRequest) {
        Payment payment = paymentRepository.findById(PaymentId).orElse(null);
        assert payment != null;
        payment.setMethod(paymentRequest.getMethod());
        payment.setAmount(paymentRequest.getAmount());
        payment.setDate(paymentRequest.getDate());
        payment.setStatus(paymentRequest.getStatus());
        paymentRepository.save(payment);
        return payment;
    }

    public Payment deletePayment(long foodId) {
        Payment payment = paymentRepository.findById(foodId).orElse(null);
        assert payment != null;
        paymentRepository.delete(payment);
        return payment;
    }

    public List<Payment> selectAllPaymentForCustomer()
    {
         List<Payment> payments = paymentRepository.selectAllPaymentForCustomer();
         return payments;
    }
}
