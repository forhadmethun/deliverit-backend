package com.deliverit.utility.dto;

import com.deliverit.entity.order.ShipmentStatus;
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
    private ShipmentStatus shipmentStatus;
    private LocalDateTime placementTime;
    private LocalDateTime actualPickupTime;
    private LocalDateTime expectedPickupTime;
    private LocalDateTime expectedDeliveryTime;
    private LocalDateTime actualDeliveryTime;
    private Long deliveryFee;
    private String paymentMethod;
}
