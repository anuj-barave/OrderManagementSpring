package com.springboot.OrderManagement.Delivery.domain;

import com.springboot.OrderManagement.Delivery.api.request.DeliveryRequest;
import com.springboot.OrderManagement.Food.api.request.FoodRequest;
import com.springboot.OrderManagement.Food.domain.Food;
//import com.springboot.OrderManagement.Food.domain.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryFacade {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryFacade(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<Delivery> getAllDelivery() {
        return deliveryRepository.findAll();
    }

    public Delivery getDeliveryById(long id) {
        System.out.println(id);
        return deliveryRepository.findById(id).orElse(null);
    }

    public Delivery addDelivery(Delivery deliveryRequest) {
        return deliveryRepository.save(deliveryRequest);
    }

    public Delivery updateDelivery(long DeliveryId, Delivery deliveryRequest) {
        Delivery delivery = deliveryRepository.findById(DeliveryId).orElse(null);
        assert delivery != null;
        delivery.setStatus(deliveryRequest.getStatus());
        Delivery save = deliveryRepository.save(delivery);
        return save;
    }

    public Delivery deleteDelivery(long DeliveryId) {
        Delivery delivery = deliveryRepository.findById(DeliveryId).orElse(null);
        assert delivery != null;
        deliveryRepository.delete(delivery);
        return delivery;
    }
}
