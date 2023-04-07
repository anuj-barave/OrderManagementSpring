package com.springboot.OrderManagement.Payment.controller;

import com.springboot.OrderManagement.Payment.api.request.PaymentRequest;
import com.springboot.OrderManagement.Payment.api.response.PaymentResponse;
import com.springboot.OrderManagement.Payment.domain.Payment;
import com.springboot.OrderManagement.Payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments()
    {
        List<PaymentResponse> allPayments = paymentService.getAllPayments();
        return ResponseEntity.ok(allPayments);
    }

    @GetMapping("{paymentId}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable long paymentId)
    {
         PaymentResponse paymentById = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(paymentById);
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> addPayment(@RequestBody PaymentRequest paymentRequest)
    {
         PaymentResponse paymentResponse = paymentService.addPayment(paymentRequest);
         return ResponseEntity.ok(paymentResponse);
    }

    @PutMapping("{paymentId}")
    public ResponseEntity<PaymentResponse> updatePayemnt(@PathVariable long paymentId,@RequestBody PaymentRequest paymentRequest)
    {
        PaymentResponse paymentResponse = paymentService.updatePayment(paymentId,paymentRequest);
        return ResponseEntity.ok(paymentResponse);
    }

    @DeleteMapping("{paymentId}")
    public ResponseEntity<PaymentResponse> deletePayment(@PathVariable long paymentId)
    {
        PaymentResponse paymentResponse = paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Payment>> selectAllPaymentForCustomer()
    {
         List<Payment> payments = paymentService.selectAllPaymentForCustomer();
        return ResponseEntity.ok(payments);
    }


}
