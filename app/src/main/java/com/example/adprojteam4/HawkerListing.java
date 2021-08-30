package com.example.adprojteam4;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HawkerListing {

    private Long Id;
    private String address;
    private String postalCode;
    private String stallNo;
    private String locationArea;
//    private string stallImageUrl;
    private String name;
//    private Long ogpId;
//    private Long lastEditedId1;
//    private String lastEditedDate1;
    private String foodType;
    private String carbType;
    private String proteinType;

    public HawkerListing(String name, String address, String postalCode, String stallNo, String locationArea) {
        this.address = address;
        this.postalCode = postalCode;
        this.stallNo = stallNo;
        this.name = name;
        this.locationArea = locationArea;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStallNo() {
        return stallNo;
    }

    public void setStallNo(String stallNo) {
        this.stallNo = stallNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getCarbType() {
        return carbType;
    }

    public void setCarbType(String carbType) {
        this.carbType = carbType;
    }

    public String getProteinType() {
        return proteinType;
    }

    public void setProteinType(String proteinType) {
        this.proteinType = proteinType;
    }

    public String getLocationArea() {
        return locationArea;
    }

    public void setLocationArea(String locationArea) {
        this.locationArea = locationArea;
    }
}
