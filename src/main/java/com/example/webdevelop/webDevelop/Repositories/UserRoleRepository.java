package com.example.webdevelop.webDevelop.Repositories;

import com.example.webdevelop.webDevelop.Models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface UserRoleRepository extends JpaRepository <UserRole, UUID> {

    @Query("SELECT ur.role AS role, COUNT(u.id) AS user_count " +
            "FROM UserRole ur " +
            "LEFT JOIN ur.users u ON ur.role = ur.role " +
            "GROUP BY ur.role")
    List<Object[]> countUsersByRole();
}

