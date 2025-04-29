package com.example.bookstore.repositories;

import com.example.bookstore.dto.AuthDto;
import com.example.bookstore.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByNameAndPassword(String name, String password);
    User findByEmail(String email);
    Optional<User> findUserById(Long id);
    User findByName(String name);

    User findByPasswordResetToken(String token);
}
