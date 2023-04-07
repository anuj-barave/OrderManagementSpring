package com.springboot.OrderManagement.Customer.domain;

import com.springboot.OrderManagement.Customer.api.request.CustomerRequest;
import com.springboot.OrderManagement.Order.domain.Order;
import com.springboot.OrderManagement.Payment.domain.Payment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class CustomerFacade {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerFacade(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer getCustomerByName(String customerName) {
        return customerRepository.findByName(customerName);
    }

    public Customer addCusotmer(CustomerRequest customerRequest) {
        Customer customer = new Customer(customerRequest.getName(),customerRequest.getAddress(),customerRequest.getPhone());
        customerRepository.save(customer);
        return customer;
    }

    public Customer updateCustomer(long customerId, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        assert customer != null;
        customer.setName(customerRequest.getName());
        customer.setAddress(customerRequest.getAddress());
        customer.setPhone(customerRequest.getPhone());
        customerRepository.save(customer);
        return customer;
    }

    public Customer deleteCustomer(long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        assert customer != null;
        customerRepository.delete(customer);
        return customer;
    }

    public List<Payment> retrieveCustomerPayments(long customerId){
        return customerRepository.retrieveCustomerPayments(customerId);
    }

    public List<Order> retrieveCustomerOrders(long customerId){
        return customerRepository.retrieveCustomerOrders(customerId);
    }

}
