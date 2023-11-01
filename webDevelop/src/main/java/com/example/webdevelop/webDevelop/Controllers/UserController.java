package com.example.webdevelop.webDevelop.Controllers;

import com.example.webdevelop.webDevelop.Controllers.views.UserViewModel;
import com.example.webdevelop.webDevelop.DTO.UserDTO;
import com.example.webdevelop.webDevelop.Models.User;
import com.example.webdevelop.webDevelop.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable UUID id, @Valid @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @GetMapping("/inactive-users-with-models")
    public List<Object[]> findInactiveUsersWithModels() {
        return userService.findInactiveUsersWithModels();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserViewModel userViewModel) {
        UserDTO userDTO = userService.regUserAndCheckExisting(userViewModel);
        if (userDTO != null) {
            return new ResponseEntity<>("User зарегистрирован", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User имя уже использовано", HttpStatus.BAD_REQUEST);
        }
    }
}

