package com.springboot.OrderManagement.Delivery.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryResponse {

        private long id;
        private String status;

        @JsonCreator
        public DeliveryResponse(long id, String status) {
            this.id = id;
            this.status = status;
        }
}


