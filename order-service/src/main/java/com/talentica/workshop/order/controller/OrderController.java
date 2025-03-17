package com.talentica.workshop.order.controller;


import com.talentica.workshop.order.model.Order;
import com.talentica.workshop.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/place")
    public Order placeOrder(@RequestParam(required = false, defaultValue = "cappuccino") String coffeeType, @RequestParam(required = false, defaultValue = "1") String quantity) {
        return orderService.placeOrder(coffeeType, Integer.valueOf(quantity));
    }
}
