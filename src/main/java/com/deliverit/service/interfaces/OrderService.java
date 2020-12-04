package com.deliverit.service.interfaces;

import com.deliverit.utility.io.OrderIO;

import java.util.List;

public interface OrderService {
    OrderIO save(OrderIO orderIO);
    OrderIO find(Long orderId);
    List<OrderIO> findAll();
    List<OrderIO> findAllByStatus(String statusCode);
}
