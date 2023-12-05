package com.example.webdevelop.webDevelop.Controllers;

import com.example.webdevelop.webDevelop.DTO.UserRoleDTO;
import com.example.webdevelop.webDevelop.Services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-roles")
public class UserRoleController {
    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/create")
    public UserRoleDTO createUserRole(@RequestBody UserRoleDTO userRoleDTO) {
        return userRoleService.createUserRole(userRoleDTO);
    }

    @GetMapping("/{id}")
    public UserRoleDTO getUserRoleById(@PathVariable UUID id) {
        return userRoleService.getUserRoleById(id);
    }

    @GetMapping("/all")
    public List<UserRoleDTO> getAllUserRoles() {
        return userRoleService.getAllUserRoles();
    }

    @PutMapping("/{id}")
    public UserRoleDTO updateUserRole(@PathVariable UUID id, @RequestBody UserRoleDTO userRoleDTO) {
        return userRoleService.updateUserRole(id, userRoleDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUserRole(@PathVariable UUID id) {
        userRoleService.deleteUserRole(id);
    }

    @GetMapping("/count-users-by-role")
    public List<Object[]> countUsersByRole() {
        return userRoleService.countUsersByRole();
    }
}
