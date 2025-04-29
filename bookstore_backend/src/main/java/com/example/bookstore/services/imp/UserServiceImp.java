package com.example.bookstore.services.imp;

import com.example.bookstore.constraints.Rol;
import com.example.bookstore.dto.AuthDto;
import com.example.bookstore.dto.IdDto;
import com.example.bookstore.dto.MsgDto;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.User;
import com.example.bookstore.repositories.UserRepository;
import com.example.bookstore.services.CartService;
import com.example.bookstore.services.EmailService;
import com.example.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private final UserRepository userRepository;
    private final CartService cartService;
    private final SimpMessagingTemplate template; //FOR CHAT MESSAGING FUNCTIONALITY

    private final EmailService emailService;


    public UserServiceImp(UserRepository userRepository, CartService cartService, SimpMessagingTemplate template,EmailService emailService) {

        this.userRepository = userRepository;

        this.cartService = cartService;
        this.template = template;
        this.emailService=emailService;
    }


    @Override
    public User findByNameAndPassword(AuthDto authDto){
        return userRepository.findByNameAndPassword(authDto.getName(), authDto.getPassword());
    }


    public User findByEmail(String email){

        return userRepository.findByEmail(email);
    }

    public User findByName(String name){

        return userRepository.findByName(name);
    }
  //before af update
 /* @Override
  public User register(String name, String password) {
      if (name == null || name.isEmpty()) {
          throw new IllegalArgumentException("Name is required");
      }

      if (password == null || password.length() < 5) {
          throw new IllegalArgumentException("Password must be at least 5 characters long");
      }


      Cart cart = cartService.save();
      User user = User.builder()
              .name(name)
              .password(hashPassword(password)) //af
              .cart(cart)
              .rol(Rol.CLIENT)
              .build();
      return userRepository.save(user);
  }*/

    @Override
    public User register(String name, String email, String password) {
        //error with frontend validations
        /*if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (email == null || email.isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Email invalid");
        }

        if (password == null || password.length() < 5) {
            throw new IllegalArgumentException("Password must be at least 5 characters long");
        }*/

        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            throw new IllegalArgumentException("Un cont cu acest email există deja.");
        }


        Cart cart = cartService.save();
        User user = User.builder()
                .name(name)
                .email(email) //af
                .password(hashPassword(password)) //af
                .cart(cart)
                .rol(Rol.CLIENT)
                .build();
        return userRepository.save(user);
    }



    @Override
    public User logIn(AuthDto authDto) {
        if (authDto.getName() == null || authDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }

        if (authDto.getPassword() == null || authDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }

        User user = userRepository.findByName(authDto.getName());
        if (user == null) {
            return  null;
        } else {
            if (BCrypt.checkpw(authDto.getPassword(), user.getPassword())) {
                user.setLoggedC(true);
                userRepository.save(user);
                return user;
            }
        }

        return  null;
    }



    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User updateUserEmail(Long id, String email) {
        User updateUser = userRepository.findById(id).get();
        updateUser.setEmail(email);
        userRepository.save(updateUser);
        return updateUser;
    }

    @Override
    public User deleteUser(Long id) {
        User userToDelete = userRepository.findById(id).get();
        userRepository.delete(userToDelete);
        return userToDelete;
    }

    @Override
    public void chatMessages(MsgDto msgDto) {

        this.template.convertAndSendToUser(msgDto.getReciver(),"/chat",msgDto);
    }

    @Override
    public void logout(IdDto idDto) {
        User user = userRepository.findById(idDto.getId()).get();
            user.setLogged(false);
            userRepository.save(user);
        }


    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);
        return hashedPassword;
    }

    //PSW RESET

   /* public void initiatePasswordReset(String email) {
        User user = findByEmail(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            LocalDateTime expiryDate = LocalDateTime.now().plusHours(1); // token valid for 1 hour
            user.setPasswordResetToken(token);
            user.setTokenExpiryDate(expiryDate);
            userRepository.save(user);

            // Send reset token via email
            emailService.sendPasswordResetEmail(user.getEmail(), token);
        } else {
            throw new IllegalArgumentException("User with given email not found");
        }
    }*/

    public void initiatePasswordReset(String email) {
        // Log the initiation attempt
        System.out.println("Attempting to initiate password reset for email: " + email);

        User user = findByEmail(email);

        if (user != null) {
            System.out.println("User found for email: " + email);

            String token = UUID.randomUUID().toString();
            LocalDateTime expiryDate = LocalDateTime.now().plusHours(1); // token valid for 1 hour
            user.setPasswordResetToken(token);
            user.setTokenExpiryDate(expiryDate);

            System.out.println("Generated token: " + token);

            try {
                // Save the user with the reset token to the database
                userRepository.save(user);
                System.out.println("Saved user with reset token to the database.");

                // Attempt to send the reset email
                emailService.sendPasswordResetEmail(user.getEmail(), token);
                System.out.println("Reset email sent to: " + email);

            } catch(Exception e) {
                // Log any unexpected exceptions
                System.err.println("Error during password reset initiation: " + e.getMessage());
                e.printStackTrace();
            }

        } else {
            System.err.println("User not found for email: " + email);
            throw new IllegalArgumentException("User with given email not found");
        }
    }

    public void validatePasswordResetToken(String token) {
        User user = userRepository.findByPasswordResetToken(token);
        if (user == null || LocalDateTime.now().isAfter(user.getTokenExpiryDate())) {
            throw new IllegalArgumentException("Invalid or expired password reset token");
        }
    }

    public void resetPassword(String token, String newPassword) {
        validatePasswordResetToken(token);
        User user = userRepository.findByPasswordResetToken(token);
        user.setPassword(hashPassword(newPassword));
        user.setPasswordResetToken(null);
        user.setTokenExpiryDate(null);
        userRepository.save(user);
    }






}


/* @Override
    public User register(String name, String password) {

        *//*try {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name is required");
            }

            if (password == null || password.length() < 5) {
                throw new IllegalArgumentException("Password must be at least 5 characters long");
            }*//*

            String encodedPassword = passwordEncoder.encode(password);

            Cart cart = cartService.save();
            User user = User.builder()
                    .name(name)
                    .password(encodedPassword)
                    .cart(cart)
                    .rol(Rol.CLIENT)
                    .build();
            return userRepository.save(user);
       *//* } catch (Exception e) {
            log.error("Registration failed", e);
            // Optionally, throw the exception to be handled by a Controller Advice or error handler.
            throw e;
        }*//*
    }*/

   /* @Override
    public User register(String name, String password) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (password == null || password.length() < 5) {
            throw new IllegalArgumentException("Password must be at least 5 characters long");
        }

        String encodedPassword = passwordEncoder.encode(password);

        Cart cart = cartService.save();

        User user = User.builder()
                .name(name)
                .password(encodedPassword)
                .cart(cart)
                .rol(Rol.CLIENT)
                .build();
        return userRepository.save(user);
    }*/

