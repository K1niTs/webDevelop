package com.example.webdevelop.webDevelop.DTO;
import com.example.webdevelop.webDevelop.Enum.Role;
import java.util.UUID;

public class UserRoleDTO {
    private UUID id;
    private Role role;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
    public UserRoleDTO() {
    }
    public UserRoleDTO(UUID id, Role role) {
        this.id = id;
        this.role = role;

    }
}

