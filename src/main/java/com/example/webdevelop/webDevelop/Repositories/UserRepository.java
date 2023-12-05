package com.example.webdevelop.webDevelop.Repositories;

import com.example.webdevelop.webDevelop.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <User, UUID> {
    @Query("SELECT U.username AS user_username, U.isActive AS user_isActive, M.name AS model_name " +
            "FROM User U " +
            "JOIN Offer O ON U.id = O.seller.id " +
            "JOIN Model M ON O.model.id = M.id " +
            "WHERE U.isActive = false")
        List<Object[]> findInactiveUsersWithModels();

        Optional<User> findUserByUsername(String username);
}