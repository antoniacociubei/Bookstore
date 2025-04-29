package com.example.bookstore.services.imp;


import com.example.bookstore.model.Book;
import com.example.bookstore.model.Favorites;
import com.example.bookstore.model.User;
import com.example.bookstore.repositories.FavoritesRepository;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.FavoritesService;
import com.example.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesServiceImp implements FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;


   @Override
   public Favorites addBookToFavorites(Long userId, Long bookId) {
       User user = userService.findById(userId);
       Book book = bookService.findById(bookId);
       Favorites favorites = user.getFavorites();

       if (favorites == null) {
           favorites = new Favorites();
           user.setFavorites(favorites);
           userService.save(user);  // Save the user after setting favorites
       }

       favorites.getBooks().add(book);
       return favoritesRepository.save(favorites);
   }

    @Override
    public List<Book> getFavoriteBooks(Long userId) {
        User user = userService.findById(userId);
        Favorites favorites = user.getFavorites();
        return favorites.getBooks();
    }

    @Override
    public Favorites removeBookFromFavorites(Long userId, Long bookId) {
        User user = userService.findById(userId);
        Book book = bookService.findById(bookId);
        Favorites favorites = user.getFavorites();
        favorites.getBooks().remove(book);
        return favoritesRepository.save(favorites);
    }
}