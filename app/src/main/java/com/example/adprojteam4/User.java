package com.example.adprojteam4;

public class User {
    private long id;
    private String username;
    private String password;
    private boolean loggedIn;
    private String nric;
    private String name;
    private String race;
    private String dob;
    private String nationality;
    private String gender;
    private String address;
    private String mobileNo;
    private String vicinity;
    private Double customerRating;
//    private Collection<OrderDetail> orderDetails;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }
}
