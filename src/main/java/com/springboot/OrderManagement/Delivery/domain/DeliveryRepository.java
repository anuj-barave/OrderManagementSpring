package com.springboot.OrderManagement.Delivery.domain;

import com.springboot.OrderManagement.Delivery.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
}
