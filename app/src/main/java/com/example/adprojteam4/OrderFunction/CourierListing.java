package com.example.adprojteam4.OrderFunction;

import java.util.Date;

public class CourierListing {

    private Long id;

    private String pickupLocation;
    private String pickupDate;
    private String pickupTime;
    private String orderBeforeTime;
    private String courierOrderStatus;

    public CourierListing(String pickupLocation, String pickupDate, String pickupTime, String orderBeforeTime) {
        this.pickupLocation = pickupLocation;
        this.pickupDate = pickupDate;
        this.pickupTime = pickupTime;
        this.orderBeforeTime = orderBeforeTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getOrderBeforeTime() {
        return orderBeforeTime;
    }

    public void setOrderBeforeTime(String orderBeforeTime) {
        this.orderBeforeTime = orderBeforeTime;
    }

    public String getCourierOrderStatus() {
        return courierOrderStatus;
    }

    public void setCourierOrderStatus(String courierOrderStatus) {
        this.courierOrderStatus = courierOrderStatus;
    }
}
