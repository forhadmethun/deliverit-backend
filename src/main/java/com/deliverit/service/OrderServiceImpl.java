package com.deliverit.service;

import com.deliverit.entity.customer.Customer;
import com.deliverit.entity.order.Order;
import com.deliverit.entity.order.OrderItem;
import com.deliverit.entity.order.Shipment;
import com.deliverit.repository.CustomerRepository;
import com.deliverit.repository.OrderItemRepository;
import com.deliverit.repository.OrderRepository;
import com.deliverit.repository.ShipmentRepository;
import com.deliverit.service.interfaces.OrderService;
import com.deliverit.utility.dto.CustomerDto;
import com.deliverit.utility.io.OrderIO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.deliverit.utility.io.OrderIO.buildResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final ShipmentRepository shipmentRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    final ModelMapper modelMapper;

    @Override
    public OrderIO save(OrderIO orderRequest) {
        Shipment shipment = shipmentRepository
                .save(Shipment.of(orderRequest.getShipmentDto(), modelMapper));
        CustomerDto customerDto = orderRequest.getCustomerDto();
        Customer customer = customerRepository.save(Customer.of(customerDto, modelMapper));
        Order order = Order.of(orderRequest.getOrderDto(), modelMapper);
        order.setShipmentId(shipment.getShipmentId());
        order.setCustomerId(customer.getCustomerId());
        order = orderRepository.save(order);
        List<OrderItem> orderItems = getOrderItems(orderRequest, order.getOrderId());
        orderItemRepository.saveAll(orderItems);
        return find(order.getOrderId());
    }

    private List<OrderItem> getOrderItems(OrderIO orderIO, long orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        orderIO.getOrderItems()
                .forEach(orderItemDto -> {
                    OrderItem orderItem = OrderItem.of(orderItemDto, modelMapper);
                    orderItem.setOrderId(orderId);
                    orderItems.add(orderItem);
                });
        return orderItems;
    }

    @Override
    public OrderIO find(Long orderId) {
        Order order;
        Optional<Order> orderOptional  =  orderRepository.findById(orderId);
        if(!orderOptional.isPresent()){
           return null;
        }
        order = orderOptional.get();
        Customer customer = customerRepository.findById(order.getCustomerId()).get();
        Shipment shipment = shipmentRepository.findById(order.getShipmentId()).get();
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        return buildResponse(order, shipment, orderItems, customer,modelMapper);
    }
}
