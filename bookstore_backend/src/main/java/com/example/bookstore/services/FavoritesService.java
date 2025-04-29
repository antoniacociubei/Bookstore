package com.example.bookstore.services;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Favorites;
import com.example.bookstore.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface FavoritesService {
    Favorites addBookToFavorites(Long userId, Long bookId);
    List<Book> getFavoriteBooks(Long userId);
    Favorites removeBookFromFavorites(Long userId, Long bookId);
}
