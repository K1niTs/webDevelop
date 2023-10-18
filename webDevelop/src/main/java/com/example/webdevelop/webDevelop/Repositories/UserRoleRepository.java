package com.example.webdevelop.webDevelop.Repositories;

import com.example.webdevelop.webDevelop.Models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository <UserRole, Integer> {

}