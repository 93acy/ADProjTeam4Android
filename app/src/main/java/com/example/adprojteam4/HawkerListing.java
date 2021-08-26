package com.example.adprojteam4;

import androidx.recyclerview.widget.RecyclerView;

public class HawkerListing {

    private Long Id;
    private String address;
    private String postalCode;
    private String stallNo;
    private Long stallImage;
    private String name;

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

    //    private Long stallImage
//    private Long ogpId;
//    private Long lastEditedId1;
//    private String lastEditedDate1;

    public HawkerListing(String address, String postalCode, String stallNo, String name) {
        this.address = address;
        this.postalCode = postalCode;
        this.stallNo = stallNo;
        this.name = name;
    }
}
