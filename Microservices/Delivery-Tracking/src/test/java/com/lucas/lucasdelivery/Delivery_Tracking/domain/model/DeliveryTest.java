package com.lucas.lucasdelivery.Delivery_Tracking.domain.model;

import com.lucas.lucasdelivery.Delivery_Tracking.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    public void shouldChangeToPlaced(){
        Delivery delivery = Delivery.draft();

        delivery.editPreparationDetails(createdValidPreparationDetails());

        delivery.place();

        assertEquals(DeliveryStatus.WAITING_FOR_COURIER, delivery.getStatus());
        assertNotNull(delivery.getPlaceAt());
    }

    @Test
    public void shouldNotPlaced(){
        Delivery delivery = Delivery.draft();

        assertThrows(DomainException.class, () -> {
            delivery.place();
        });

        assertEquals(DeliveryStatus.DRAFT, delivery.getStatus());
        assertNull(delivery.getPlaceAt());
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