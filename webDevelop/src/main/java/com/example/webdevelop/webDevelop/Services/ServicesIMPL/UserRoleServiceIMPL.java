package com.example.webdevelop.webDevelop.Services.ServicesIMPL;

import com.example.webdevelop.webDevelop.DTO.UserRoleDTO;
import com.example.webdevelop.webDevelop.Models.UserRole;
import com.example.webdevelop.webDevelop.Repositories.UserRoleRepository;
import com.example.webdevelop.webDevelop.Services.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceIMPL implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceIMPL(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRoleDTO createUserRole(UserRoleDTO userRoleDTO) {
        UserRole userRole = modelMapper.map(userRoleDTO, UserRole.class);
        UserRole savedUserRole = userRoleRepository.save(userRole);
        return modelMapper.map(savedUserRole, UserRoleDTO.class);
    }

    @Override
    public UserRoleDTO getUserRoleById(UUID id) {
        Optional<UserRole> userRoleOptional = userRoleRepository.findById(id);
        if (userRoleOptional.isPresent()) {
            return modelMapper.map(userRoleOptional.get(), UserRoleDTO.class);
        }
        return null;
    }

    @Override
    public List<UserRoleDTO> getAllUserRoles() {
        List<UserRole> userRoles = userRoleRepository.findAll();
        return userRoles.stream()
                .map(userRole -> modelMapper.map(userRole, UserRoleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserRoleDTO updateUserRole(UUID id, UserRoleDTO userRoleDTO) {
        Optional<UserRole> userRoleOptional = userRoleRepository.findById(id);
        if (userRoleOptional.isPresent()) {
            UserRole userRole = userRoleOptional.get();
            userRole.setRole(userRoleDTO.getRole());
            UserRole updateUserRoles = userRoleRepository.save(userRole);
            return modelMapper.map(updateUserRoles, UserRoleDTO.class);
        } else {

            return null;
        }
    }

    @Override
    public void deleteUserRole(UUID id) {
        userRoleRepository.deleteById(id);
    }

    public List<Object[]> countUsersByRole() {
        return userRoleRepository.countUsersByRole();

    }
}
