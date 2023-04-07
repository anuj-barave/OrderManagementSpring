package com.springboot.OrderManagement.Delivery.service;

import com.springboot.OrderManagement.Delivery.api.request.DeliveryRequest;
import com.springboot.OrderManagement.Delivery.api.response.DeliveryResponse;
import com.springboot.OrderManagement.Delivery.domain.Delivery;
import com.springboot.OrderManagement.Delivery.domain.DeliveryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    DeliveryFacade deliveryFacade;

    public List<DeliveryResponse> getAllDeliverys() {
         List<Delivery> delivery = deliveryFacade.getAllDelivery();
         List<DeliveryResponse> deliveryResponses = new ArrayList<>();
        for (Delivery delivery1:delivery) {
            deliveryResponses.add(new DeliveryResponse(delivery1.getId(),delivery1.getStatus()));
        }
        return deliveryResponses;
    }

    public DeliveryResponse getDeliveryById(long DeliveryId) {
        Delivery delivery = deliveryFacade.getDeliveryById(DeliveryId);
        return new DeliveryResponse(delivery.getId(),delivery.getStatus());
    }

    public DeliveryResponse addDelivery(DeliveryRequest deliveryRequest) {
         Delivery delivery = new Delivery(deliveryRequest.getStatus());
         Delivery delivery1 = deliveryFacade.addDelivery(delivery);
         return new DeliveryResponse(delivery.getId(),delivery.getStatus());
    }

    public DeliveryResponse updateDelivery(long DeliveryId,DeliveryRequest deliveryRequest) {
        Delivery delivery = new Delivery(deliveryRequest.getStatus());
        Delivery delivery1 = deliveryFacade.updateDelivery(DeliveryId, delivery);
        return new DeliveryResponse(delivery1.getId(),delivery1.getStatus());
    }

    public DeliveryResponse deleteDelivery(long DeliveryId) {
        Delivery delivery = deliveryFacade.deleteDelivery(DeliveryId);
        return new DeliveryResponse(delivery.getId(),delivery.getStatus());
    }

}
