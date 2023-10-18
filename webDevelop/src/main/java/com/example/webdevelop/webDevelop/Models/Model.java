package com.example.webdevelop.webDevelop.Models;

import jakarta.persistence.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Models")
public class Model extends BaseEntity {
    private String name;
    private Category category;
    public enum Category{
        CAR,
        BUSS,
        TRUCK,
        MOTORCYCLE
    }
    private URL imageURL;
    private int startYear;
    private int endYear;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String brand;

    @OneToMany(mappedBy = "models")
    List<Offer> offers;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brands;

}
