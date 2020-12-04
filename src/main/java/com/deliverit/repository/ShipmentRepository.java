package com.deliverit.repository;

import com.deliverit.entity.order.Shipment;
import com.deliverit.entity.order.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findAllByShipmentStatus(ShipmentStatus shipmentStatus);
}
