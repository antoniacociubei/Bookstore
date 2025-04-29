package com.example.bookstore.repositories;

import com.example.bookstore.model.OrderHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends CrudRepository<OrderHistory,Long> {

    List<OrderHistory> findByClientId(Long id);
}
