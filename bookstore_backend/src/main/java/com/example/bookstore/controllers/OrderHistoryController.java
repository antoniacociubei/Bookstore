package com.example.bookstore.controllers;

import com.example.bookstore.services.OrderHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderHistoryController {
    private final OrderHistoryService orderHistoryService;

    public OrderHistoryController(OrderHistoryService orderHistoryService) {
        this.orderHistoryService = orderHistoryService;

    }
    @GetMapping("/findOrders/{id}")
    public ResponseEntity findOrder(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderHistoryService.findRecivedOrders(id));
    }

}
