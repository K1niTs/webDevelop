package com.example.webdevelop.webDevelop.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty; // Импортируем нужную аннотацию

public class BrandDTO {
    private UUID id;

    @NotEmpty(message = "Имя не должно быть пустое")
    @Size(max = 255, message = "Не может больше 255 символов")
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BrandDTO(){}
    @Override
    public String toString() {
        return "BrandDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public BrandDTO(UUID id, String name) {
        this.id = id;
        this.name = name;

        }
    }

