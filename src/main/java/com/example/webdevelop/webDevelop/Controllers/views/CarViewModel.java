package com.example.webdevelop.webDevelop.Controllers.views;

import com.example.webdevelop.webDevelop.Models.BaseEntity;

import java.util.UUID;

public class CarViewModel extends BaseEntity {
    private UUID id;
    private String imageURL;
    private String brandName;
    private String modelName;
    private String offerDescription;
    private int offerMileage;
    private int offerPrice;

    @Override
    public String toString() {
        return "CarViewModel{" +
                "id=" + id +
                ", imageURL='" + imageURL + '\'' +
                ", brandName='" + brandName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", offerDescription='" + offerDescription + '\'' +
                ", offerMileage=" + offerMileage +
                ", offerPrice=" + offerPrice +
                '}';
    }

    public CarViewModel(UUID id, String imageURL, String brandName, String modelName, String offerDescription, int offerMileage, int offerPrice) {
        this.id = id;
        this.imageURL = imageURL;
        this.brandName = brandName;
        this.modelName = modelName;
        this.offerDescription = offerDescription;
        this.offerMileage = offerMileage;
        this.offerPrice = offerPrice;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public int getOfferMileage() {
        return offerMileage;
    }

    public void setOfferMileage(int offerMileage) {
        this.offerMileage = offerMileage;
    }

    public int getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(int offerPrice) {
        this.offerPrice = offerPrice;
    }
}