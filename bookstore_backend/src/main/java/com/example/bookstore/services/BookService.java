package com.example.bookstore.services;

import com.example.bookstore.constraints.Category;
import com.example.bookstore.constraints.Rol;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookService {
    List<Book> findAll();
    //Book findBookByTitle(String title);

    List<Book> findBookByTitle(String title);

    byte[] exportBooksToXml() throws Exception;

    Book findById(Long id);
    Book save(BookDto bookDto);
    List<Book> findByCategory(Category category);
    List<Book> findByAuthor(String author);
    List<Book> findByPriceBetween(int low, int high);
    //Book updateBook(Book book, Rol role);

    Book updateBook(Long id, double price);

    //Book deleteBook (Long id);
    void deleteBook(Long id);

    List<String> exportBooksDetalis();
}
