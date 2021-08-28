package com.example.adprojteam4;

import java.util.ArrayList;

public class TempStall {
    private String address;
    private ArrayList<String> carbType;
    private String foodType;
    private String name;
    private String postalCode;
    private ArrayList<String> proteinType;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getCarbType() {
        return carbType;
    }

    public void setCarbType(ArrayList<String> carbType) {
        this.carbType = carbType;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public ArrayList<String> getProteinType() {
        return proteinType;
    }

    public void setProteinType(ArrayList<String> proteinType) {
        this.proteinType = proteinType;
    }
}
