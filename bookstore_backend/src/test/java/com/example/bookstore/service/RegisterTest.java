/*
package com.example.bookstore.service;
import com.example.bookstore.dto.AuthDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest {
    // Create an instance of YourService and mock any dependencies

    @Test
    void testLogIn_InvalidUsername() {
        // Arrange
        AuthDto authDto = new AuthDto("", "password");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userService.logIn(authDto);
        });
    }

    @Test
    void testLogIn_InvalidPassword() {
        // Arrange
        AuthDto authDto = new AuthDto("username", "");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            yourService.logIn(authDto);
        });
    }

    @Test
    void testLogIn_UserNotFound() {
        // Arrange
        AuthDto authDto = new AuthDto("nonexistent", "password");

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            yourService.logIn(authDto);
        });
    }

    @Test
    void testLogIn_ValidCredentials() {
        // Arrange
        AuthDto authDto = new AuthDto("username", "password");

        // Act
        User result = yourService.logIn(authDto);

        // Assert
        assertNotNull(result);
        // Add additional assertions as needed
    }
}
*/
