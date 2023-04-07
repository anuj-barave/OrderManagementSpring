package com.springboot.OrderManagement.Delivery.controller;

import com.springboot.OrderManagement.Delivery.api.request.DeliveryRequest;
import com.springboot.OrderManagement.Delivery.api.response.DeliveryResponse;
import com.springboot.OrderManagement.Delivery.domain.Delivery;
import com.springboot.OrderManagement.Delivery.domain.DeliveryFacade;
import com.springboot.OrderManagement.Delivery.service.DeliveryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<List<DeliveryResponse>> getAllDelivery()
    {
         List<DeliveryResponse> deliveries = deliveryService.getAllDeliverys();
         return ResponseEntity.ok(deliveries);
    }

    @GetMapping("{deliveryId}")
    public ResponseEntity<DeliveryResponse> getDeliveryById(@PathVariable long deliveryId)
    {
        DeliveryResponse deliveries = deliveryService.getDeliveryById(deliveryId);
        return ResponseEntity.ok(deliveries);
    }

    @PostMapping
    public ResponseEntity<DeliveryResponse> addDelivery(@RequestBody DeliveryRequest deliveryRequest)
    {
        DeliveryResponse deliveries = deliveryService.addDelivery(deliveryRequest);
        return ResponseEntity.ok(deliveries);
    }

    @PutMapping("{deliveryId}")
    public ResponseEntity<DeliveryResponse> updateDelivery(@PathVariable long deliveryId,@RequestBody DeliveryRequest deliveryRequest)
    {
        DeliveryResponse deliveries = deliveryService.updateDelivery(deliveryId,deliveryRequest);
        return ResponseEntity.ok(deliveries);
    }

    @DeleteMapping("{deliveryId}")
    public ResponseEntity<DeliveryResponse> deleteDelivery(@PathVariable long deliveryId)
    {
        DeliveryResponse deliveries = deliveryService.deleteDelivery(deliveryId);
        return ResponseEntity.ok(deliveries);
    }

}
