package com.example.webdevelop.webDevelop.Controllers.views;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegUserViewModel {
    private String username;
    private String firstName;
    private String lastName;
    private String password;

    public RegUserViewModel(String username, String firstName, String lastName, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public RegUserViewModel() {

    }

    @Override
    public String toString() {
        return "RegUserViewModel{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    @NotEmpty(message = "User name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotEmpty(message = "first name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotEmpty(message = "last name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @NotEmpty(message = "Password cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
