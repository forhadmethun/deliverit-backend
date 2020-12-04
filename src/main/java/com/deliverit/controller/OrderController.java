package com.deliverit.controller;

import com.deliverit.service.interfaces.OrderService;
import com.deliverit.utility.io.OrderIO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    final ModelMapper modelMapper;

    @PostMapping(value = "/orders")
    @ResponseStatus(code = HttpStatus.CREATED)
    public OrderIO createOrder(@RequestBody OrderIO request) {
        log.info("OrderController:: createOrder: request: " + request.toString());
        return orderService.save(request);
    }

    @GetMapping(value = "/orders/{orderId}")
    public OrderIO getOrder(@PathVariable Long orderId) {
        log.info("OrderController:: getOrder: orderId: {}", orderId);
        return orderService.find(orderId);
    }

    @GetMapping(value = "/orders")
    public List<OrderIO> getOrders(
            @RequestParam(required = false) String statusCode
    ) {
        log.info("OrderController:: getOrders");
        if (statusCode != null) {
            return orderService.findAllByStatus(statusCode);
        }
        return orderService.findAll();
    }
}
