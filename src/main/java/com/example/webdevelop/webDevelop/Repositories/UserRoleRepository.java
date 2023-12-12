package com.example.webdevelop.webDevelop.Repositories;

import com.example.webdevelop.webDevelop.DTO.UserRoleDTO;
import com.example.webdevelop.webDevelop.Enum.Role;
import com.example.webdevelop.webDevelop.Models.User;
import com.example.webdevelop.webDevelop.Models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRoleRepository extends JpaRepository <UserRole, UUID> {

    @Query("SELECT ur.role AS role, COUNT(u.id) AS user_count " +
            "FROM UserRole ur " +
            "LEFT JOIN ur.users u ON ur.role = ur.role " +
            "GROUP BY ur.role")
    List<Object[]> countUsersByRole();

//    @Query("SELECT ur FROM UserRole ur WHERE ur.role = :role")
//    Optional<UserRole> findByRole(@Param("role") Role role);

    UserRole findByRole(Role role);
}
