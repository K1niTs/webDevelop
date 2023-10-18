package com.example.webdevelop.webDevelop.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;

@Entity
@Table (name = "Offers")
public class Offer extends BaseEntity {
    private String description;
    private Engine engine;
    private enum Engine{
        GASOLINE,
        DIESEL,
        ELECTRIC,
        HYBRID
    }
    private URL imageUrl;
    private int mileage;
    private BigDecimal price;
    private Transmission transmission;
    private enum Transmission{
        MANUAL,
        AUTOMATIC
    }
    private int year;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String model;
    private String seller;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model models;
    @ManyToOne
    @JoinColumn(name ="seller_id")
    private User user;


}

