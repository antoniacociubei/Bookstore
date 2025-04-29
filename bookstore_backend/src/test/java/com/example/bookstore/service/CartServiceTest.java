package com.example.bookstore.service;


import com.example.bookstore.dto.AddCartDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.User;
import com.example.bookstore.repositories.CartRepository;
import com.example.bookstore.repositories.UserRepository;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.CartService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
/*


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CartServiceTest {

    @MockBean
    private BookService produsService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testAddToCart() {

        AddCartDto addCartDto = new AddCartDto();
        addCartDto.setUserId(1L);
        addCartDto.setBookId(2L);

        User user = new User();
        user.setId(1L);
        Cart cart = new Cart();
        cart.setTotalPrice(0);
        List<Book> books = new ArrayList<>();
        cart.setBooks(books);
        user.setCart(cart);

        Book book = new Book();
        book.setId(2L);
        double bookPrice = 10.99;
        book.setPrice(bookPrice);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(produsService.findById(2L)).thenReturn(book);

        cartService.addToCart(addCartDto);

        assertEquals(cart.getTotalPrice(), bookPrice);
        assertEquals(cart.getBooks().size(), 1);
        assertTrue(cart.getBooks().contains(book));
    }

}



*/
