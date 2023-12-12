package com.example.webdevelop.webDevelop.Controllers.views;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserViewModel {

    private String username;
    private String firstName;
    private String lastName;

    public UserViewModel(){
        this.firstName = "default";
        this.lastName = "default";

    }
    @NotBlank(message = "Ник не должно быть пустое")
    @Size(max = 255, message = "Не может больше 255 символов")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotBlank(message = "Имя не должно быть пустое")
    @Size(max = 255, message = "Не может больше 255 символов")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotBlank(message = "Фамилия не должна быть пустой")
    @Size(max = 255, message = "Name must be less than 255 characters")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
