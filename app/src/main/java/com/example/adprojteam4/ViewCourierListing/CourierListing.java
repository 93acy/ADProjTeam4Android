package com.example.adprojteam4.ViewCourierListing;

import com.example.adprojteam4.HawkerListing;

public class CourierListing {
    //private Long id;
    //private Double totalPrice;
    //private HawkerListing hawkerListing;
    //private Collection<CourierListingDetails> courierListingDetails;

    private Long id;

    private String pickupLocation;
    private Double totalPrice;
    private Boolean optionForDoorDelivery;
    private String vicinity;
    private Double courierRating;

    //private Courier courier;

    private HawkerListing hawkerListing;

    //private Collection<CourierListingDetails> courierListingDetails;

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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getOptionForDoorDelivery() {
        return optionForDoorDelivery;
    }

    public void setOptionForDoorDelivery(Boolean optionForDoorDelivery) {
        this.optionForDoorDelivery = optionForDoorDelivery;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public Double getCourierRating() {
        return courierRating;
    }

    public void setCourierRating(Double courierRating) {
        this.courierRating = courierRating;
    }

    /*public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public HawkerListing getHawkerListing() {
        return hawkerListing;
    }

    public void setHawkerListing(HawkerListing hawkerListing) {
        this.hawkerListing = hawkerListing;
    }

    public Collection<CourierListingDetails> getCourierListingDetails() {
        return courierListingDetails;
    }

    public void setCourierListingDetails(Collection<CourierListingDetails> courierListingDetails) {
        this.courierListingDetails = courierListingDetails;
    }
     */
}
