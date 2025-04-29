package com.example.bookstore.controllers;
import com.example.bookstore.model.Book;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.FavoritesService;
import com.example.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @PostMapping("/{userId}/add/{bookId}")
    public void addBookToFavorites(@PathVariable Long userId, @PathVariable Long bookId) {
        favoritesService.addBookToFavorites(userId, bookId);
    }

    @GetMapping("/{userId}")
    public List<Book> getFavoriteBooks(@PathVariable Long userId) {
        return favoritesService.getFavoriteBooks(userId);
    }

    @DeleteMapping("/{userId}/remove/{bookId}")
    public void removeBookFromFavorites(@PathVariable Long userId, @PathVariable Long bookId) {
        favoritesService.removeBookFromFavorites(userId, bookId);
    }
}