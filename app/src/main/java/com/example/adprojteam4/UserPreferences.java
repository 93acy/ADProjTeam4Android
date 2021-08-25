package com.example.adprojteam4;

import java.util.ArrayList;
import java.util.List;

public class UserPreferences {
    private String foodType;
    private ArrayList<String> carbType;
    private ArrayList<String> proteinType;

    public UserPreferences(String foodType, ArrayList<String> carbType, ArrayList<String> proteinType) {
        this.foodType = foodType;
        this.carbType = carbType;
        this.proteinType = proteinType;
    }
}
