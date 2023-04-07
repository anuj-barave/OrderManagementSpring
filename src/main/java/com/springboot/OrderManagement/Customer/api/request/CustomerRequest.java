package com.springboot.OrderManagement.Customer.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;


@Getter
public class CustomerRequest {

    private long id;
    private String name;
    private String address;
    private String phone;

    @JsonCreator
    public CustomerRequest(long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
