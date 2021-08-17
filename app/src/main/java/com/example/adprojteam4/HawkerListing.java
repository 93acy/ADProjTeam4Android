package com.example.adprojteam4;

import androidx.recyclerview.widget.RecyclerView;

public class HawkerListing {

    private Long Id;
    private String address;
    private String postalCode;
    private String stallNo;
//    private Long stallImage;
    private String name;
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
