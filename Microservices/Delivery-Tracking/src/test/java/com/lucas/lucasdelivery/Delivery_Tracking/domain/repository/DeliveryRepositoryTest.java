package com.lucas.lucasdelivery.Delivery_Tracking.domain.repository;

import com.lucas.lucasdelivery.Delivery_Tracking.domain.model.ContactPoint;
import com.lucas.lucasdelivery.Delivery_Tracking.domain.model.Delivery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeliveryRepositoryTest {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    public void shouldPersist(){
        Delivery delivery = Delivery.draft();

        delivery.editPreparationDetails(createdValidPreparationDetails());

        delivery.addItem("Computador", 2);
        delivery.addItem("notebook", 2);

        deliveryRepository.saveAndFlush(delivery);

        Delivery persistedDelivery = deliveryRepository.findById(delivery.getId()).orElseThrow();

        assertEquals(2,persistedDelivery.getItems().size());

    }

    private Delivery.PreparationDetails createdValidPreparationDetails(){

        ContactPoint sender = ContactPoint.builder()
                .zipCode("00000-000")
                .street("Rua Açaí")
                .number("515")
                .complement("Sala 2")
                .name("João Silva")
                .phone("(31) 98722-5429")
                .build();

        ContactPoint recipient = ContactPoint.builder()
                .zipCode("93857-000")
                .street("Rua Castanheira")
                .number("301")
                .complement("")
                .name("Lucas Moreira")
                .phone("(21) 4002-8922")
                .build();

        return Delivery.PreparationDetails.builder()
                .sender(sender)
                .recipient(recipient)
                .distanceFee(new BigDecimal("15.00"))
                .courierPayout(new BigDecimal("5.00"))
                .expectedDeliveryTime(Duration.ofHours(5))
                .build();
    }

}