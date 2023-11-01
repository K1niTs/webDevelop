package com.example.webdevelop.webDevelop.DTO;

import com.example.webdevelop.webDevelop.Enum.Category;
import jakarta.validation.constraints.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

public class ModelDTO {
    private UUID id;
    @NotEmpty(message = "Имя не должно быть пустое")
    @Size(max = 255, message = "Не может больше 255 символов")
    private String name;

    @NotNull(message = "Категория не может быть null")
    private Category category;

    @NotEmpty(message = "imageURL не должно быть пустое")
    @Pattern(regexp = "^(http|https)://[a-zA-Z0-9./]+$", message = "Неправильный формат")
    private String imageURL;

    @PositiveOrZero(message = "Нельзя минус")
    private int startYear;

    @PositiveOrZero(message = "Нельзя минус")
    private int endYear;

    @NotNull(message = "Бренд не может быть null")
    private BrandDTO brand;
    public ModelDTO(){}

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ModelDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", imageURL=" + imageURL +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", brand='" + brand + '\'' +
                '}';
    }

    public ModelDTO(UUID id, String name, Category category, String imageURL, int startYear, int endYear, BrandDTO brand) {

        this.id = id;
        this.name = name;
        this.category = category;
        this.imageURL = imageURL;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brand = brand;
    }
}

