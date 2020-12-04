package com.deliverit.utility.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentDto {
    private Long id;
    private String address;
    private String shipmentStatus;
    private LocalDateTime placementTime;
    private LocalDateTime actualPickupTime;
    private LocalDateTime actualDeliveryTime;
    private Long deliveryFee;
    private String paymentMethod;
    private LocalDateTime expectedPickupTime;
    private LocalDateTime expectedDeliveryTime;
    private String shipmentInstruction;
}
