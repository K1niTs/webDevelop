package com.example.webdevelop.webDevelop.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table (name = "UserRole")
public class UserRole extends BaseEntity {
    private Roles roles;
    @OneToMany(mappedBy = "userRole")
    List<User> users;
    private enum Roles{
    USER,
    ADMIN

    }
}
