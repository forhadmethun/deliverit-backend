package com.deliverit.service;

import com.deliverit.entity.customer.Customer;
import com.deliverit.entity.order.Order;
import com.deliverit.entity.order.OrderItem;
import com.deliverit.entity.order.Shipment;
import com.deliverit.entity.order.ShipmentStatus;
import com.deliverit.repository.CustomerRepository;
import com.deliverit.repository.OrderItemRepository;
import com.deliverit.repository.OrderRepository;
import com.deliverit.repository.ShipmentRepository;
import com.deliverit.service.interfaces.OrderService;
import com.deliverit.dto.CustomerDto;
import com.deliverit.dto.ShipmentDto;
import com.deliverit.dto.io.OrderIO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.deliverit.dto.io.OrderIO.buildResponse;

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
        ShipmentDto shipmentDto = orderRequest.getShipmentDto();
        shipmentDto.setShipmentStatus(ShipmentStatus.NEW);
        Shipment shipment = shipmentRepository
                .save(Shipment.of(shipmentDto, modelMapper));
        CustomerDto customerDto = orderRequest.getCustomerDto();
        Customer customer = customerRepository.save(Customer.of(customerDto, modelMapper));
        Order order = Order.of(orderRequest.getOrderDto(), modelMapper);
        order.setShipmentId(shipment.getShipmentId());
        order.setCustomerId(customer.getCustomerId());
        order = orderRepository.save(order);
        List<OrderItem> orderItems = getOrderItems(orderRequest, order);
        orderItemRepository.saveAll(orderItems);
        order.setItems(orderItems);
        return find(order.getOrderId());
    }

    private List<OrderItem> getOrderItems(OrderIO orderIO, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        orderIO.getOrderItems()
                .forEach(orderItemDto -> {
                    OrderItem orderItem = OrderItem.of(orderItemDto, modelMapper);
                    orderItem.setOrder(order);
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
        return buildResponse(order, shipment, customer,modelMapper);
    }

    @Override
    public List<OrderIO> findAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderIO> orderIOS = new ArrayList<>();
        orders.stream()
                .forEach(order ->{
                    Customer customer = customerRepository.findById(order.getCustomerId()).get();
                    Shipment shipment = shipmentRepository.findById(order.getShipmentId()).get();
                    orderIOS.add(buildResponse(order, shipment, customer,modelMapper));
                });
        return orderIOS;
    }

    @Override
    public List<OrderIO> findAllByStatus(String statusCode) {
        List<Shipment> shipments = shipmentRepository.findAllByShipmentStatus(ShipmentStatus.valueOf(statusCode));
        List<OrderIO> orderIOS = new ArrayList<>();
        shipments.forEach(shipment -> {
            Order order = orderRepository.findByShipmentId(shipment.getShipmentId()).get();
            Customer customer = customerRepository.findById(order.getCustomerId()).get();
            orderIOS.add(buildResponse(order, shipment, customer,modelMapper));
        });
        return orderIOS;
    }
}
