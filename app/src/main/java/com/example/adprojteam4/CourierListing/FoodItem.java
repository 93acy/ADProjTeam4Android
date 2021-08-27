package com.example.adprojteam4.CourierListing;

public class FoodItem {

    private Long id;
    private String name;
    private String description;
    private String category;
    private Double upperPrice;
    private Double lowerPrice;

    public FoodItem(Long id, String name, String description, String category, Double upperPrice, Double lowerPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.upperPrice = upperPrice;
        this.lowerPrice = lowerPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getUpperPrice() {
        return upperPrice;
    }

    public void setUpperPrice(Double upperPrice) {
        this.upperPrice = upperPrice;
    }

    public Double getLowerPrice() {
        return lowerPrice;
    }

    public void setLowerPrice(Double lowerPrice) {
        this.lowerPrice = lowerPrice;
    }
}
