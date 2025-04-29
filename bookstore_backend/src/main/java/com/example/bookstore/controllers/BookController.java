package com.example.bookstore.controllers;

import com.example.bookstore.constraints.Category;
import com.example.bookstore.constraints.Rol;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/save")
    public ResponseEntity saveBook(@RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.save(bookDto));
    }


    @GetMapping("/export")
    public ResponseEntity exportXML(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.exportBooksDetalis());
    }
   /* @PostMapping("/save")
    public ResponseEntity saveBook(@RequestBody List<BookDto> bookDtoList) {
        List<Book> savedBooks = new ArrayList<>();
        for (BookDto bookDto : bookDtoList) {
            savedBooks.add(bookService.save(bookDto));
        }
        return ResponseEntity.status(HttpStatus.OK).body(savedBooks);
    }*/

    /*@PutMapping("/remove/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id, @RequestHeader("Role") String role){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteBook(id , Rol.valueOf(role)));
    }
*/
    /*@PutMapping("/remove/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id, @RequestHeader("Role") String role){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteBook(id , Rol.valueOf(role)));
    }
*/
  /*  @DeleteMapping("/remove/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id, @RequestHeader("Role") String role){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteBook(id , Rol.valueOf(role)));
    }
*/

   /* @PutMapping("/remove/{id}")
    public ResponseEntity deleteProdus(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteBook(id));
    }*/
    @GetMapping("listAllBooks")
    public ResponseEntity findAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }


    /*@GetMapping("/title/{title}")
    public ResponseEntity<Book> findBookByTitle(@PathVariable String title) {

        return ResponseEntity.ok(bookService.findBookByTitle(title));
      *//*  Book book = bookService.findByTitle(title);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }*//*
    }*/

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> findBookByTitle(@PathVariable String title) {
        List<Book> booksByTtl= bookService.findBookByTitle(title);
        return ResponseEntity.ok(booksByTtl);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> findByAuthor(@PathVariable String author) {
        List<Book> booksByAuth= bookService.findByAuthor(author);
        return ResponseEntity.ok(booksByAuth);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Book>> findBooksByCategory(@PathVariable Category category) {
        List<Book> books = bookService.findByCategory(category);
        return ResponseEntity.ok(books);
    }


/*    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable Long id, @RequestBody Map<String, Double> body, @RequestHeader("Role") String role) {
        Double price = body.get("price");
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id, price, Rol.valueOf(role)));
    }*/

    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable Long id, @RequestBody Map<String, Double> body) {
        Double price = body.get("price");

        Book updatedBook = bookService.updateBook(id, price);

        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



   /* @PostMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable Long id, @RequestBody double price, @RequestHeader("Role") String role) {


            return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id,price, Rol.valueOf(role)));

    }*/


}
