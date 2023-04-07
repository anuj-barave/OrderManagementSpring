package com.springboot.OrderManagement.Order.api.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private long id;
    private long quantity;
    private Date date;
    private long customerId;
    private List<Long> foodids;

    public OrderRequest(long id, long quantity, Date date, long customerId, long myid, List<Long> foodId) {
        this.id = id;
        this.quantity = quantity;
        this.date = date;
        this.customerId = customerId;
        foodids = foodId;
    }
}
