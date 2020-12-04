package com.deliverit.utility.dto;

import com.deliverit.entity.order.Order;
import com.deliverit.entity.order.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private Long itemId;
    private Long orderId;
    private String description;
    private int quantity;
    private double unitPrice;

    public static OrderItemDto of(OrderItem orderItem, ModelMapper modelMapper) {
        return modelMapper.map(orderItem, OrderItemDto.class);
    }
}
