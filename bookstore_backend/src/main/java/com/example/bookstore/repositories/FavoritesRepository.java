package com.example.bookstore.repositories;

import com.example.bookstore.model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    // Custom queries can be defined here if needed
}
