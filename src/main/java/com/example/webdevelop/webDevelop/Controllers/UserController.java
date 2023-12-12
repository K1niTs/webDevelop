package com.example.webdevelop.webDevelop.Controllers;

import com.example.webdevelop.webDevelop.Controllers.views.Profile;
import com.example.webdevelop.webDevelop.Controllers.views.RegUserViewModel;

import com.example.webdevelop.webDevelop.DTO.UserDTO;
import com.example.webdevelop.webDevelop.Models.User;
import com.example.webdevelop.webDevelop.Services.ServicesIMPL.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.webdevelop.webDevelop.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
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
    @GetMapping("/add")
    public String createUser() {
        return "users-add";
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        userService.createUser(userDTO);
        return "redirect:/users/add";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/users/login";
    }
    @PostMapping("/register")
    public String registerUser(@Valid RegUserViewModel regUserViewModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("regUserView", regUserViewModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.regUserView", bindingResult);
            return "redirect:/users/register";
        }

        // Register the user
        authService.registerUser(regUserViewModel);
        authService.authWithHttpServletRequest(request, regUserViewModel.getUsername(), regUserViewModel.getPassword());


        return "redirect:/";
    }
    @ModelAttribute("regUserView")
    public RegUserViewModel initUser(){ return new RegUserViewModel();}
    @GetMapping("/register")
    public String regNewUSer(){
        return "register";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        Profile userProfileView = new Profile(
                username,
                user.getFirstName(),
                user.getLastName(),
                user.getPassword()
        );

//        model.addAttribute("offers",userService.allUserOffers(username));
        model.addAttribute("user", userProfileView);

        return "profile";
    }
}

