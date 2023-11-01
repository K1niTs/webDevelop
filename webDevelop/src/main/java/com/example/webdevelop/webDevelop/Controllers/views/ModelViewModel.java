package com.example.webdevelop.webDevelop.Controllers.views;

import com.example.webdevelop.webDevelop.Enum.Category;

public class ModelViewModel {
    private String name;
    private Category category;
    private String imageURL;
    private int startYear;
    private int endYear;

    public ModelViewModel(String name, Category category, String imageURL, int startYear, int endYear) {
        this.name = name;
        this.category = category;
        this.imageURL = imageURL;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public ModelViewModel() {
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
}
