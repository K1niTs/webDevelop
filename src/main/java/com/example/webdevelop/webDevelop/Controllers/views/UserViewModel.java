package com.example.webdevelop.webDevelop.Controllers.views;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserViewModel {
    @NotBlank(message = "Ник не должно быть пустое")
    @Size(max = 255, message = "Не может больше 255 символов")
    private String username;
    @NotEmpty(message = "Password не должно быть пустое")
    @Size(max = 255, message = "Не может больше 255 символов")
    private String password;
    @NotBlank(message = "Имя не должно быть пустое")
    @Size(max = 255, message = "Не может больше 255 символов")
    private String firstName;
    @NotBlank(message = "Фамилия не должна быть пустой")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String lastName;
    @NotBlank(message = "Image URL обязателен")
    @Pattern(regexp = "^(http|https)://.*$", message = "Image URL начинается с http:// или https://")
    private String imageUrl;

    public UserViewModel(){
        this.firstName = "default";
        this.lastName = "default";
        this.imageUrl = "https://";

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
