package com.example.bookstore.services.imp;

import com.example.bookstore.constraints.Category;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.UserRepository;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.exporter.FileExporter;
import com.example.bookstore.services.exporter.XMLFileExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;

@Service
public class BookServiceImp implements BookService {

    private BookRepository bookRepository;
    private UserRepository userRepository;


    public BookServiceImp(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @Autowired
    public BookServiceImp(BookRepository bookRepository, UserRepository userRepository){
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

   /* @Override
    public byte[] exportBooksToXml() throws Exception {
        List<Book> books = (List<Book>) bookRepository.findAll();

        JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        marshaller.marshal(books, outputStream);

        return outputStream.toByteArray();
    }*/

    public byte[] exportBooksToXml() throws Exception {
        List<Book> books = bookRepository.findAll();

        JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        marshaller.marshal(new JAXBElement(new QName("books"), List.class, books), outputStream);

        return outputStream.toByteArray();
    }



    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).get();
    }


    @Override
    public List<Book> findAll() {
        List<Book> booksList= bookRepository.findByQuantityGreaterThan(0);
        return booksList;
    }

    @Override
    public Book save(BookDto book) {
        // Validate author
        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            throw new IllegalArgumentException("Author is required");
        }

        // Validate title
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }

        // Validate category
        if (book.getCategory() == null) {
            throw new IllegalArgumentException("Category is required");
        }

        // Validate price
        if (book.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be a positive value");
        }

        // Validate quantity
        if (book.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        return bookRepository.save(Book.builder()
                .author(book.getAuthor())
                .price(book.getPrice())
                .category(book.getCategory())
                .quantity(book.getQuantity())
                .publisher(book.getPublisher())
                .title(book.getTitle())
                .build());
    }

    @Override
    public List<Book> findBookByTitle(String author){
        return bookRepository.findBookByTitle(author);
    }

    @Override
    public List<Book> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }


    @Override
    public List<Book> findByCategory(Category category){
        return bookRepository.findByCategory(category);
    }

    @Override
    public List<Book> findByPriceBetween(int low, int high) {
        return bookRepository.findByPriceIsBetween(low,high);
    }


    @Override
    public Book updateBook(Long id, double price) {

        // Validate price
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be a positive value");
        }
        try {
            Book updatedBook = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
            updatedBook.setPrice(price);
            bookRepository.save(updatedBook);
            return updatedBook;
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Failed to update book: " + ex.getMessage());
        }
    }


    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private String exportBook(Long id){
        Book b=bookRepository.findById(id).get();
        FileExporter fileExporter=new XMLFileExporter();
        return fileExporter.exportData(b);

    }

    @Override
    public List<String> exportBooksDetalis() {
        List <Book> bookList=bookRepository.findAll();
        List <String> xmlLogs=new ArrayList<>();
        for (Book b:bookList) {
            xmlLogs.add(exportBook(b.getId()));
        }
        return xmlLogs;
    }


}
