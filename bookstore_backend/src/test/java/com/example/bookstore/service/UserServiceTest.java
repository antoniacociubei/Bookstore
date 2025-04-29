/*
package com.example.bookstore.service;



import com.example.bookstore.constraints.Rol;
import com.example.bookstore.dto.AuthDto;
import com.example.bookstore.model.User;
import com.example.bookstore.repositories.UserRepository;
import com.example.bookstore.services.CartService;
import com.example.bookstore.services.imp.UserServiceImp;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;



public class UserServiceTest {
    private static final String NAME = "John";
    private static final String NAME_NOT = "Kate";

    private static final String PASSWORD = "1234";
    private static final String PASSWORD_NOT = "blabalabala";

    private UserServiceImp userServiceImp;

    @Mock
    private UserRepository userRepository;
    private CartService cartService;

    private User user;
    private AuthDto authDto;
    private AuthDto authDtoNot;

    @BeforeEach
    void init() {
        initMocks(this);
        user = new User();
        user.setId(3L);
        user.setName(NAME);
        user.setPassword(PASSWORD);
        user.setRol(Rol.CLIENT);

        authDto = new AuthDto();
        authDto.setName(NAME);
        authDto.setPassword(PASSWORD);

        authDtoNot = new AuthDto();
        authDtoNot.setName(NAME_NOT);
        authDtoNot.setPassword(PASSWORD_NOT);

        when(userRepository.findByNameAndPassword(authDto)).thenReturn(user);
    }

    @Test
    void givenExistingUser_whenFindByNameAndPassword_thenFindOne() {
        userServiceImp = new UserServiceImp(userRepository, cartService);

        User user = userServiceImp.findByNameAndPassword(authDto);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(NAME,user.getName());
        Assertions.assertEquals(PASSWORD,user.getPassword());
    }

    @Test
    void givenNonExistingUser_whenFindByNameAndPassword_thenThrowException() {
        when(userRepository.findByNameAndPassword(authDtoNot)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            userServiceImp.findByNameAndPassword(authDtoNot);
        });
    }
}



*/

/*

import com.example.bookstore.constraints.Rol;
import com.example.bookstore.dto.AuthDto;
import com.example.bookstore.model.User;
import com.example.bookstore.repositories.UserRepository;
import com.example.bookstore.services.CartService;
import com.example.bookstore.services.imp.UserServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private static final String NAME = "John";
    private static final String NAME_NOT = "Kate";

    private static final String PASSWORD = "1234";
    private static final String PASSWORD_NOT = "blabalabala";

    @InjectMocks
    private UserServiceImp userServiceImp;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartService cartService;

    private User user;
    private AuthDto authDto;
    private AuthDto authDtoNot;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setId(3L);
        user.setName(NAME);
        user.setPassword(PASSWORD);
        user.setRol(Rol.CLIENT);

        authDto = new AuthDto();
        authDto.setName(NAME);
        authDto.setPassword(PASSWORD);

        authDtoNot = new AuthDto();
        authDtoNot.setName(NAME_NOT);
        authDtoNot.setPassword(PASSWORD_NOT);

        when(userRepository.findByNameAndPassword(authDto.getName(), authDto.getPassword())).thenReturn(user);
        when(userRepository.findByNameAndPassword(authDtoNot.getName(), authDtoNot.getPassword())).thenReturn(null);
    }

    @Test
    void givenExistingUser_whenFindByNameAndPassword_thenFindOne() {
        User user = userServiceImp.logIn(authDto);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(NAME, user.getName());
        Assertions.assertEquals(PASSWORD, user.getPassword());
    }

    @Test
    void givenNonExistingUser_whenFindByNameAndPassword_thenThrowException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            userServiceImp.logIn(authDtoNot);
        });
    }




}
*/


import com.example.bookstore.constraints.Rol;
import com.example.bookstore.dto.AuthDto;
import com.example.bookstore.model.User;
import com.example.bookstore.repositories.UserRepository;
import com.example.bookstore.services.CartService;
import com.example.bookstore.services.imp.UserServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userService;

    private static final String NAME = "John";
    private static final String PASSWORD = "1234";
    private static final String NAME_NOT = "Kate";
    private static final String PASSWORD_NOT = "blabalabala";

    private User user;
    private AuthDto authDto;
    private AuthDto authDtoNot;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setId(3L);
        user.setName(NAME);
        user.setPassword(PASSWORD);
        user.setRol(Rol.CLIENT);

        authDto = new AuthDto();
        authDto.setName(NAME);
        authDto.setPassword(PASSWORD);

        authDtoNot = new AuthDto();
        authDtoNot.setName(NAME_NOT);
        authDtoNot.setPassword(PASSWORD_NOT);

        when(userRepository.findByNameAndPassword(NAME, PASSWORD)).thenReturn(user);
        when(userRepository.findByNameAndPassword(NAME_NOT, PASSWORD_NOT)).thenReturn(null);
    }

    @Test
    void givenExistingUser_whenLogIn_thenReturnUser() {
        User result = userService.logIn(authDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(user, result);
        Assertions.assertTrue(result.isLogged());
    }

    @Test
    void givenNonExistingUser_whenLogIn_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            userService.logIn(authDtoNot);
        });
    }
}
