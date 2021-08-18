package com.example.adprojteam4;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

public class SessionManager {
    private SharedPreferences prefs;
    @NotNull
    public static final String USER_TOKEN = "user_token";

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public void saveAuthToken(String token) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }

    public String fetchAuthToken() {
        return prefs.getString(USER_TOKEN, null);
    }

    public void deleteAuthToken() {
        prefs.edit().remove(USER_TOKEN).apply();
    }
}
