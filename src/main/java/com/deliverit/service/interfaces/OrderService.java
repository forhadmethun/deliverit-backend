package com.deliverit.service.interfaces;

import com.deliverit.utility.dto.OrderDto;
import com.deliverit.utility.io.OrderIO;

public interface OrderService {
    OrderIO save(OrderIO orderIO);
    OrderIO find(Long orderId);
}
