package com.example.webdevelop.webDevelop.DTO;

import com.example.webdevelop.webDevelop.Enum.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    @NotEmpty(message = "Username не должно быть пустое")
    @Size(max = 255, message = "Не может больше 255 символов")
    private String username;

    @NotEmpty(message = "Password не должно быть пустое")
    @Size(max = 255, message = "Не может больше 255 символов")
    private String password;

    @NotEmpty(message = "First name не должно быть пустое")
    @Size(max = 255, message = "Не может больше 255 символов")
    private String firstName;

    @NotEmpty(message = "Last name не должно быть пустое")
    @Size(max = 255, message = "Не может больше 255 символов")
    private String lastName;
    private boolean isActive;
    private String imageUrl;
    private UserRoleDTO role;


    public UserDTO(UUID id, String username, String password, String firstName, String lastName, boolean isActive, String imageUrl, UserRoleDTO role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserRoleDTO getRole() {
        return role;
    }

    public void setRole(UserRoleDTO role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", imageUrl='" + imageUrl + '\'' +
                ", role=" + role +
                '}';
    }

    public UserDTO(){

    }
}

