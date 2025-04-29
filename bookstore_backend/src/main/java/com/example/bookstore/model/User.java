package com.example.bookstore.model;

import com.example.bookstore.constraints.Rol;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    private Rol rol;
     @OneToOne
     private Cart cart;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorites_id", referencedColumnName = "id")
    private Favorites favorites;

    @Column
    private boolean logged ;
     @OneToMany
     private List<OrderHistory> historyList;

  /*  public boolean isAdmin() {
        return this.rol == Rol.ADMIN;
    }
*/
    public void setLoggedC(boolean b){
        this.logged=b;
    }

    //password reset
    private String passwordResetToken;
    private LocalDateTime tokenExpiryDate;



}
