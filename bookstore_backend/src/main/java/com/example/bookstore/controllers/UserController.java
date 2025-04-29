package com.example.bookstore.controllers;

import com.example.bookstore.dto.*;
import com.example.bookstore.model.User;
import com.example.bookstore.services.CartService;
import com.example.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final CartService cartService;


    public UserController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @PostMapping("/logIn")
    public ResponseEntity login(@RequestBody AuthDto logIn) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.logIn(logIn));
    }

    //before final assignemnt

   /* @PostMapping("/register")
    public ResponseEntity register(@RequestBody AuthDto registerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.register(registerDto.getName(), registerDto.getPassword()));
    }
*/
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto registerDto) {
        System.out.println("Received email: " + registerDto.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(userService.register(registerDto.getName(),registerDto.getEmail(), registerDto.getPassword()));

    }

    //final assignment
   /* @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> register(@RequestBody AuthDto registerDto) {
        try {
            User registeredUser = userService.register(registerDto.getName(), registerDto.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (Exception e) {
            // You might want to handle different exceptions with different messages and status codes
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }*/


    @PostMapping("/updateEmail")
    public ResponseEntity updateEmail(@RequestBody UpdateEmailDto updateEmailDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserEmail(updateEmailDto.getId(), updateEmailDto.getEmail()));
    }

    @GetMapping("/listAll")
    public ResponseEntity listAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

   /* @PostMapping("/addToCart")
    public ResponseEntity addToCart (@RequestBody AddCartDto addCartDto){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addToCart(addCartDto));
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity findUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }


    @GetMapping("/private")
    public MsgDto recevePrivateMessage(@RequestBody MsgDto chatMsgDto) {
        userService.chatMessages(chatMsgDto);
        return chatMsgDto;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody IdDto idDto) {
      userService.logout(idDto);
      return ResponseEntity.ok().build();
  }

   /* @PostMapping("/initiate-password-reset")
    public ResponseEntity<String> initiatePasswordReset(@RequestBody String email) {
        try {
            userService.initiatePasswordReset(email);
            return new ResponseEntity<>("Password reset link has been sent to your email.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }*/

    /*@PostMapping("/initiate-password-reset")
    public ResponseEntity<String> initiatePasswordReset(@RequestBody EmailDto emailDto) {
        try {
            userService.initiatePasswordReset(emailDto.getEmail());
            return new ResponseEntity<>("Password reset link has been sent to your email.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }*/

    @PostMapping("/initiate-password-reset")
    public ResponseEntity<String> initiatePasswordReset(@RequestBody EmailDto emailDTO) {
        try {
            System.out.println("Received email: " + emailDTO.getEmail());
            userService.initiatePasswordReset(emailDTO.getEmail());
            return new ResponseEntity<>("Password reset link has been sent to your email.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            System.out.println("Error processing email: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




    // Endpoint to validate the token (optional but can be used to check before allowing users to enter a new password)
    @GetMapping("/validate-password-reset-token")
    public ResponseEntity<String> validatePasswordResetToken(@RequestParam String token) {
        try {
            userService.validatePasswordResetToken(token);
            return new ResponseEntity<>("Token is valid.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to reset the password
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetDto passwordResetDto) {
        try {
            userService.resetPassword(passwordResetDto.getToken(), passwordResetDto.getNewPassword());
            return new ResponseEntity<>("Password has been reset.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}






