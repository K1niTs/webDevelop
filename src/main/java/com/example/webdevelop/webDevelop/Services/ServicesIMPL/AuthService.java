package com.example.webdevelop.webDevelop.Services.ServicesIMPL;


import com.example.webdevelop.webDevelop.Controllers.views.RegUserViewModel;
import com.example.webdevelop.webDevelop.Enum.Role;
import com.example.webdevelop.webDevelop.Models.User;
import com.example.webdevelop.webDevelop.Repositories.UserRepository;
import com.example.webdevelop.webDevelop.Repositories.UserRoleRepository;
import com.example.webdevelop.webDevelop.Services.UserRoleService;
import com.example.webdevelop.webDevelop.Services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;
    private ModelMapper modelMapper;
    private UserRoleService userRoleService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper,UserRoleService userRoleService,UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.userService=userService;
        this.userRoleService=userRoleService;
    }


    public void registerUser(RegUserViewModel regUserViewModel) {
        User user= modelMapper.map(regUserViewModel, User.class);
        user.setRole(userRoleService.getByRole(Role.ADMIN));
        user.setActive(true);
        user.setImageUrl("blank");
        user.setPassword(passwordEncoder.encode(regUserViewModel.getPassword()));
        userRepository.saveAndFlush(user);
    }
    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {

        }
    }

    public User getUser(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}