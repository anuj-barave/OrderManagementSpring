package com.springboot.OrderManagement.Payment.service;

import com.springboot.OrderManagement.Customer.domain.CustomerFacade;
import com.springboot.OrderManagement.Payment.api.request.PaymentRequest;
import com.springboot.OrderManagement.Payment.api.response.PaymentResponse;
import com.springboot.OrderManagement.Payment.domain.Payment;
import com.springboot.OrderManagement.Payment.domain.PaymentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentFacade paymentFacade;
    @Autowired
    CustomerFacade customerFacade;

    public List<PaymentResponse> getAllPayments() {
        List<Payment> allPayments = paymentFacade.getAllPayments();
        List<PaymentResponse> paymentResponses = new ArrayList<>();
        for (Payment payment: allPayments) {
            paymentResponses.add(new PaymentResponse(payment.getId(),payment.getAmount(),
                    payment.getMethod(),payment.getStatus(),payment.getDate()));
        }
        return paymentResponses;
    }

    public PaymentResponse getPaymentById(long paymentId) {
         Payment payment = paymentFacade.getPaymentById(paymentId);
        return new PaymentResponse(payment.getId(),payment.getAmount(),
                payment.getMethod(),payment.getStatus(),payment.getDate());
    }

    public PaymentResponse addPayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment(paymentRequest.getAmount(),paymentRequest.getMethod(),paymentRequest.getStatus(),paymentRequest.getDate());
        payment.setCustomer(customerFacade.getCustomerById(paymentRequest.getCustomerId()));
        Payment addPayment = paymentFacade.addPayment(payment);
        return new PaymentResponse(addPayment.getId(),addPayment.getAmount(),
                addPayment.getMethod(),addPayment.getStatus(),addPayment.getDate());
    }

    public PaymentResponse updatePayment(long paymentId, PaymentRequest paymentRequest) {
        Payment payment = paymentFacade.updatePayment(paymentId,paymentRequest);
        return new PaymentResponse(payment.getId(),payment.getAmount(),
                payment.getMethod(),payment.getStatus(),payment.getDate());
    }


    public PaymentResponse deletePayment(long paymentId) {
        Payment payment = paymentFacade.deletePayment(paymentId);
        return new PaymentResponse(payment.getId(),payment.getAmount(),
                payment.getMethod(),payment.getStatus(),payment.getDate());
    }

    public List<Payment> selectAllPaymentForCustomer()
    {
        return paymentFacade.selectAllPaymentForCustomer();
    }
}
