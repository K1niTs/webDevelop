//package com.example.webdevelop.webDevelop.Init;
//
//
//import com.example.webdevelop.webDevelop.Enum.Role;
//import com.example.webdevelop.webDevelop.Models.User;
//import com.example.webdevelop.webDevelop.Models.UserRole;
//import com.example.webdevelop.webDevelop.Repositories.UserRepository;
//import com.example.webdevelop.webDevelop.Repositories.UserRoleRepository;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//
//
//@Component
//public class Init implements CommandLineRunner {
//    private final UserRepository userRepository;
//    private final UserRoleRepository userRoleRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final String defaultPassword;
//
//    public Init(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, @Value("${app.default.password}") String defaultPassword) {
//        this.userRepository = userRepository;
//        this.userRoleRepository = userRoleRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.defaultPassword = defaultPassword;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        initRoles();
//        initUsers();
//    }
//    private void initRoles() {
//        if (userRoleRepository.count() == 0) {
//            userRoleRepository.save(new UserRole(Role.ADMIN));
//            userRoleRepository.save(new UserRole(Role.USER));
//        }
//    }
//    private void initUsers() {
//        if (userRepository.count() == 0) {
//            initAdmin();
//            initUser();
//        }
//    }
//
//    private void initAdmin() {
//        if (userRepository.count() == 0) {
//            var adminRole = userRoleRepository
//                    .findByRole(Role.ADMIN)
//                    .orElseThrow();
//
//            var adminUser = new User("admin", passwordEncoder.encode(defaultPassword), "admin@example.com", "Admin Adminovich", true,  null, adminRole);
//
//            userRepository.save(adminUser);
//        }
//    }
//
//    private void initUser() {
//        if (userRepository.count() == 0) {
//            var userRole = userRoleRepository
//                    .findByRole(Role.USER)
//                    .orElseThrow();
//
//            var normalUser = new User("user", passwordEncoder.encode(defaultPassword), "user@example.com", "User Userovich", true, null, userRole);
//
//            userRepository.save(normalUser);
//        }
//    }
//
//
//}