package com.springboot.OrderManagement.Payment.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;


import java.sql.Date;

@Getter
@Setter
public class PaymentRequest {

    private long id;

    private double amount;

    private String method;

    private String status;

    private Date date;

    private long customerId;

    @JsonCreator
    public PaymentRequest(double amount, String method, String status, Date date,long customerId) {
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.date = date;
        this.customerId = customerId;
    }
}
