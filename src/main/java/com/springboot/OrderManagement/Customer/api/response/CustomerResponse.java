package com.springboot.OrderManagement.Customer.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {

    private long id;
    private String name;
    private String address;
    private String phone;
    private String wasd;
    private String test;
    private String wasd;

    @JsonCreator
    public CustomerResponse(long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
