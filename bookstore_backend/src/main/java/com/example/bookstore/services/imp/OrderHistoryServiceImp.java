package com.example.bookstore.services.imp;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.OrderHistory;
import com.example.bookstore.repositories.OrderHistoryRepository;
import com.example.bookstore.repositories.UserRepository;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.CartService;
import com.example.bookstore.services.OrderHistoryService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderHistoryServiceImp implements OrderHistoryService {
     
    private final OrderHistoryRepository orderHistoryRepo;
    private final UserRepository userRepository; 
    private final CartService cartService;
    private final BookService bookService;

    public OrderHistoryServiceImp(OrderHistoryRepository orderHistoryRepo, UserRepository userRepository, CartService cartService, BookService bookService) {
        this.orderHistoryRepo = orderHistoryRepo;
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.bookService = bookService;
    }

    @Override
    public List<OrderHistory> findRecivedOrders(Long clientId) {
        return orderHistoryRepo.findByClientId(clientId);
    }


        @Override
    public List<OrderHistory> findAll() {
            return (List<OrderHistory>) orderHistoryRepo.findAll();
    }
}
