package com.deliverit.dto.io;

import com.deliverit.entity.customer.Customer;
import com.deliverit.entity.order.Order;
import com.deliverit.entity.order.Shipment;
import com.deliverit.dto.CustomerDto;
import com.deliverit.dto.OrderDto;
import com.deliverit.dto.OrderItemDto;
import com.deliverit.dto.ShipmentDto;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderIO extends OrderDto {
    private String address;
    private Long deliveryFee;
    private String paymentMethod;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime expectedPickupTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime expectedDeliveryTime;
    private String shipmentInstruction;
    private CustomerDto customer;

    public OrderDto getOrderDto(){
        return OrderDto.builder()
                .orderId(this.getOrderId())
                .orderNumber(this.getOrderNumber())
                .customerId(this.getCustomerId())
                .placementTime(this.getPlacementTime())
                .build();

    }

    public ShipmentDto getShipmentDto(){
        return ShipmentDto.builder()
                .deliveryFee(this.getDeliveryFee())
                .paymentMethod(this.getPaymentMethod())
                .expectedPickupTime(this.getExpectedPickupTime())
                .expectedDeliveryTime(this.getExpectedDeliveryTime())
                .address(this.getAddress())
                .build();
    }

    public CustomerDto getCustomerDto(){
        return CustomerDto.builder()
                .email(this.customer!=null ? this.customer.getEmail() : null)
                .mobileNo(this.customer!=null ? this.customer.getMobileNo() : null)
                .name(this.customer!=null ? this.customer.getName() : null)
                .build();
    }

    public static OrderIO buildResponse(
            Order order,
            Shipment shipment,
            Customer customer, ModelMapper modelMapper
    ){
        List<OrderItemDto> orderItemdtos = new ArrayList<>();
        order.getItems().stream()
                .forEach(orderItem -> orderItemdtos.add(OrderItemDto.of(orderItem)));
        OrderIO orderResponse = new OrderIO();
        orderResponse.setShipmentId(shipment.getShipmentId());
        orderResponse.setAddress(shipment.getAddress());
        orderResponse.setDeliveryFee(shipment.getDeliveryFee());
        orderResponse.setPaymentMethod(shipment.getPaymentMethod());
        orderResponse.setExpectedDeliveryTime(shipment.getExpectedDeliveryTime());
        orderResponse.setExpectedPickupTime(shipment.getExpectedPickupTime());
        orderResponse.setShipmentInstruction(shipment.getShipmentInstruction());

        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setOrderNumber(order.getOrderNumber());
        orderResponse.setCustomerId(order.getCustomerId());
        orderResponse.setPlacementTime(order.getPlacementTime());

        orderResponse.setOrderItems(orderItemdtos);

        orderResponse.setCustomer(
                CustomerDto.of(customer, modelMapper)
        );

        return orderResponse;
    }
}
