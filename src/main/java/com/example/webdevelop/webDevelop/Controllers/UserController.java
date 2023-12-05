package com.example.webdevelop.webDevelop.Controllers;

import com.example.webdevelop.webDevelop.Controllers.views.UserViewModel;
import com.example.webdevelop.webDevelop.DTO.ModelDTO;
import com.example.webdevelop.webDevelop.DTO.OfferDTO;
import com.example.webdevelop.webDevelop.DTO.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.webdevelop.webDevelop.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/details/{user-id}")
    public String userDetails(@PathVariable("user-id") UUID userId, Model model) {
        UserDTO userDTO = userService.getUserById(userId);
        model.addAttribute("userDetails", userDTO);
        return "users-details";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<UserDTO> userDTOS = userService.getAllUsers();
        model.addAttribute("users", userDTOS);
        return "users-all";
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable UUID id, @Valid @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @PostMapping("/delete/users/{id}")
    public String deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return "redirect:/users/all";
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

    @GetMapping("/add")
    public String createUser() {
        return "users-add";
    }

    @ModelAttribute("userModel")
    public UserDTO initUser() {
        return new UserDTO();
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        userService.createUser(userDTO);
        return "redirect:/users/add";
    }
}

