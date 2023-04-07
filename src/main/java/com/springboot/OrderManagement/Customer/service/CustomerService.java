package com.springboot.OrderManagement.Customer.service;

import com.springboot.OrderManagement.Customer.api.request.CustomerRequest;
import com.springboot.OrderManagement.Customer.api.response.CustomerResponse;
import com.springboot.OrderManagement.Customer.domain.Customer;
import com.springboot.OrderManagement.Customer.domain.CustomerFacade;
import com.springboot.OrderManagement.Order.domain.Order;
import com.springboot.OrderManagement.Payment.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private CustomerFacade customerFacade;

    @Autowired
    public void CustomerService(CustomerFacade customerFacade)
    {
        this.customerFacade = customerFacade;
    }

    public List<CustomerResponse> getAllCustomers(){
        List<Customer> customerList = customerFacade.getAllCustomers();
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for(Customer customer: customerList){
            customerResponses.add(new CustomerResponse(customer.getId(),customer.getName(),customer.getAddress(),customer.getPhone()));
        }
        return customerResponses;
    }

    public CustomerResponse getCustomerById(long customerId) {
        Customer customer = customerFacade.getCustomerById(customerId);
        return new CustomerResponse(customer.getId(),customer.getName(),customer.getAddress(),customer.getPhone());
    }

    public CustomerResponse getCustomerByName(String customerName) {
        Customer customer = customerFacade.getCustomerByName(customerName);
        return new CustomerResponse(customer.getId(),customer.getName(),customer.getAddress(),customer.getPhone());
    }

    public CustomerResponse addCusotmer(CustomerRequest customerRequest) {
        Customer customer = customerFacade.addCusotmer(customerRequest);
        return new CustomerResponse(customer.getId(),customer.getName(),customer.getAddress(),customer.getPhone());
    }

    public CustomerResponse updateCustomer(long customerId,CustomerRequest customerRequest) {
        Customer customer = customerFacade.updateCustomer(customerId,customerRequest);
        return new CustomerResponse(customer.getId(),customer.getName(),customer.getAddress(),customer.getPhone());
    }

    public CustomerResponse deleteCustomer(long customerId) {
        Customer customer = customerFacade.deleteCustomer(customerId);
        return new CustomerResponse(customer.getId(),customer.getName(),customer.getAddress(),customer.getPhone());
    }

    public List<Payment> retrieveCustomerPayments(long customerId){
        return customerFacade.retrieveCustomerPayments(customerId);
    }

    public List<Order> retrieveCustomerOrders(long customerId){
        return customerFacade.retrieveCustomerOrders(customerId);
    }

}


