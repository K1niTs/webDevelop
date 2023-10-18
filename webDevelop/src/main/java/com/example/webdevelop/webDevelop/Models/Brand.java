package com.example.webdevelop.webDevelop.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "Brands")
public class Brand extends  BaseEntity {
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;
    @OneToMany(mappedBy = "brands")
    List<Model> models;
}
