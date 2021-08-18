package com.example.adprojteam4;

public class JwtRequest {
    private String username;
    private String password;

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
