package com.example.webdevelop.webDevelop.DTO;

import com.example.webdevelop.webDevelop.Enum.Engine;
import com.example.webdevelop.webDevelop.Enum.Transmission;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

public class OfferDTO {
    private UUID id;
    private String description;
    private Engine engine;
    private String imageUrl;
    private int mileage;
    private int price;
    private Transmission transmission;
    private int year;
    private LocalDateTime created;
    private LocalDateTime modified;
    private UserDTO seller;
    private ModelDTO model;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public UserDTO getSeller() {
        return seller;
    }

    public void setSeller(UserDTO seller) {
        this.seller = seller;
    }

    public ModelDTO getModel() {
        return model;
    }

    public void setModel(ModelDTO model) {
        this.model = model;
    }

    public OfferDTO(UUID id, String description, Engine engine, String imageUrl, int mileage, int price, Transmission transmission, int year, LocalDateTime created, LocalDateTime modified, UserDTO seller, ModelDTO model) {

        this.id = id;
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.created = created;
        this.modified = modified;
        this.seller = seller;
        this.model = model;
    }

    @Override
    public String toString() {
        return "OfferDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", engine=" + engine +
                ", imageUrl='" + imageUrl + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                ", transmission=" + transmission +
                ", year=" + year +
                ", created=" + created +
                ", modified=" + modified +
                ", seller=" + seller +
                ", model=" + model +
                '}';
    }

    public OfferDTO(){





    }
}
