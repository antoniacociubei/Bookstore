package com.example.bookstore.services;

import com.example.bookstore.model.OrderHistory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component

public interface OrderHistoryService {

    List<OrderHistory> findRecivedOrders(Long clientId);
    List<OrderHistory> findAll();
}
