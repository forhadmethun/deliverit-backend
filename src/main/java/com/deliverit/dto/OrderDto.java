package com.deliverit.dto;

import com.deliverit.entity.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long orderId;
    private Long shipmentId;
    private String orderNumber;
    private Long customerId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime placementTime;

    private List<OrderItemDto> orderItems;

    public static OrderDto of(Order order, ModelMapper modelMapper) {
        return modelMapper.map(order, OrderDto.class);
    }
}
