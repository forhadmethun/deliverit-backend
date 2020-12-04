package com.deliverit.entity.order;

import com.deliverit.entity.TableConstants;
import com.deliverit.utility.dto.ShipmentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = TableConstants.TABLE_SHIPMENT)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shipmentId;
    private String address;
    private ShipmentStatus shipmentStatus;
    private LocalDateTime placementTime;
    private LocalDateTime actualPickupTime;
    private LocalDateTime actualDeliveryTime;
    private Long deliveryFee;
    private String paymentMethod;
    private LocalDateTime expectedPickupTime;
    private LocalDateTime expectedDeliveryTime;
    private String shipmentInstruction;

    public static Shipment of(ShipmentDto shipmentDto, ModelMapper modelMapper) {
        return modelMapper.map(shipmentDto, Shipment.class);
    }
}
