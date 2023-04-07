package com.springboot.OrderManagement.Customer.controller;

import com.springboot.OrderManagement.Customer.api.request.CustomerRequest;
import com.springboot.OrderManagement.Customer.api.response.CustomerResponse;
import com.springboot.OrderManagement.Customer.service.CustomerService;
import com.springboot.OrderManagement.Order.domain.Order;
import com.springboot.OrderManagement.Payment.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> addOrder(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.addCusotmer(customerRequest);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllOrder() {
        List<CustomerResponse> customerResponses = customerService.getAllCustomers();
        return ResponseEntity.ok(customerResponses);
    }

    @GetMapping("{customerId}")
    public ResponseEntity<CustomerResponse> getOrderById(@PathVariable long customerId) {
        CustomerResponse customerResponse = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customerResponse);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<CustomerResponse> updateOrderById(@PathVariable long customerId,@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.updateCustomer(customerId,customerRequest);
        return ResponseEntity.ok(customerResponse);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<CustomerResponse> deleteOrderById(@PathVariable long customerId) {
        CustomerResponse customerResponse = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("{customerId}/payment")
    public ResponseEntity<List<Payment>> retrieveCustomerPayments(@PathVariable long customerId){
        List<Payment> payments = customerService.retrieveCustomerPayments(customerId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("{customerId}/Order")
    public ResponseEntity<List<Order>> retrieveCustomer(@PathVariable long customerId){
        List<Order> orders = customerService.retrieveCustomerOrders(customerId);
        return ResponseEntity.ok(orders);
    }

}
