package com.example.webdevelop.webDevelop.Models;

import com.example.webdevelop.webDevelop.Enum.Role;
import com.example.webdevelop.webDevelop.Models.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "UserRole")
public class UserRole extends BaseEntity {
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<User> users;

    public UserRole(Role role) {
        this.role = role;
    }
    public UserRole(){

    }
    public List<User> getUsers() {
        return users;
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role=" + role +
                ", users=" + users +
                ", id=" + id +
                "} " + super.toString();
    }
}