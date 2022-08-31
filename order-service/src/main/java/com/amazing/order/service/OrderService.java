package com.amazing.order.service;

import com.amazing.order.entity.Order;
import com.amazing.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(Order order){
        orderRepository.save(order);
    }

    public Order getOrderById(int orderId){
        Optional<Order> orders = orderRepository.findById(orderId);
        return orders.get();
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
