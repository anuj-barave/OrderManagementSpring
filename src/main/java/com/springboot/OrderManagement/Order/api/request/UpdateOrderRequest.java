package com.springboot.OrderManagement.Order.api.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class UpdateOrderRequest {

    private long id;
    private long quantity;
    private Date date;
    private long customerId;
    private List<Long> foodids;
    private long paymentId;
    private long deliveryId;

    public UpdateOrderRequest(long quantity, Date date, long customerId, List<Long> foodIds, long paymentId, long deliveryId) {
        this.quantity = quantity;
        this.date = date;
        this.customerId = customerId;
        foodids = foodIds;
        this.paymentId = paymentId;
        this.deliveryId = deliveryId;
    }
}