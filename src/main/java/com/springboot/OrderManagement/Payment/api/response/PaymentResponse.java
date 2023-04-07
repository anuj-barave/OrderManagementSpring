package com.springboot.OrderManagement.Payment.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PaymentResponse {
    private long id;

    private double amount;

    private String method;

    private String status;

    private Date date;

    @JsonCreator
    public PaymentResponse(long id, double amount, String method, String status, Date date) {
        this.id = id;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.date = date;
    }
}
