package com.amazing.order.controller;

import com.amazing.order.entity.Order;
import com.amazing.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class.getName());
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> addOrder(@RequestBody Order order){
        LOG.info("Add order");
        orderService.saveOrder(order);
        return ResponseEntity.ok("Order saved");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId){
        LOG.info("Get order for "+orderId);
        Order order = orderService.getOrderById(Integer.parseInt(orderId));
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrder(){
        LOG.info("Get All order");
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
