package com.deliverit.entity.order;

import com.deliverit.entity.TableConstants;
import com.deliverit.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = TableConstants.TABLE_ORDER)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private Long shipmentId;
    private Long customerId;
    private String orderNumber;
    private LocalDateTime placementTime;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<OrderItem> items;

    public static Order of(OrderDto orderDto, ModelMapper modelMapper) {
        return modelMapper.map(orderDto, Order.class);
    }
}
