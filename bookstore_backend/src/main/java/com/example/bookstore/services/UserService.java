package com.example.bookstore.services;

import com.example.bookstore.dto.AuthDto;
import com.example.bookstore.dto.IdDto;
import com.example.bookstore.dto.MsgDto;
import com.example.bookstore.model.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    User findByEmail(String email);
    //User register(String name, String password);

    User register(String name, String email, String password);

    User logIn(AuthDto authDto);
    void logout(IdDto idDto);
    User findById(Long id);

    User findByNameAndPassword(AuthDto authDto);
    User save (User user);
    List<User> findAll();

    User updateUserEmail(Long id, String email);
    User deleteUser(Long id);
    void chatMessages(MsgDto msgDto);

    void initiatePasswordReset(String email);

    void validatePasswordResetToken(String token);

    void resetPassword(String token, String newPassword);
}
