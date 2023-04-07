package com.springboot.OrderManagement.Delivery.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryRequest {

    private String status;

    @JsonCreator
    public DeliveryRequest(String status) {
        this.status = status;
    }
}
