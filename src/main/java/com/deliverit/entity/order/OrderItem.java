package com.deliverit.entity.order;

import com.deliverit.entity.TableConstants;
import com.deliverit.utility.dto.OrderItemDto;
import com.deliverit.utility.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = TableConstants.TABLE_ORDER_ITEM)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
    private Long orderId;
    private String description;
    private int quantity;
    private double unitPrice;

    public static OrderItem of(OrderItemDto orderItemDto, ModelMapper modelMapper) {
        return modelMapper.map(orderItemDto, OrderItem.class);
    }
}
