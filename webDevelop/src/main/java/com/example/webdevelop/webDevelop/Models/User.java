package com.example.webdevelop.webDevelop.Models;

import jakarta.persistence.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Users")
public class User extends BaseEntity{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private Role role;
    private enum Role{
        USER,
        ADMIN
    };
    private URL imageUrl;
    private LocalDateTime created;
    private LocalDateTime modified;
    @OneToMany(mappedBy = "user")
    private List<Offer> offers;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole userRole;
}
