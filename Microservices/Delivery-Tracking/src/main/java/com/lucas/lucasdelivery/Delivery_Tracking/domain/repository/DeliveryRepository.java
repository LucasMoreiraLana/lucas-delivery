package com.lucas.lucasdelivery.Delivery_Tracking.domain.repository;

import com.lucas.lucasdelivery.Delivery_Tracking.domain.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
    
}
