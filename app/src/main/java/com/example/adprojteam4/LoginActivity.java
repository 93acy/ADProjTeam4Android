package com.example.adprojteam4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    private EditText etUsername, etPassword;
    SharedPreferences userPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences userPrefs = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        bottomNav = findViewById(R.id.bottomNavbar);
        bottomNav.setSelectedItemId(R.id.nav_myAccount);

        etUsername = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        findViewById(R.id.tvRegisterLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void loginUser() {
        SessionManager sessionManager = new SessionManager(this);

        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty()) {
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return;
        } else if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        Call<JwtResponse> call = RetrofitClient
                .getInstance(this)
                .getAPI()
                .authenticate(new JwtRequest(username, password));

        call.enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                JwtResponse jwtResponse = null;
                try {
                    jwtResponse = response.body();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (response.isSuccessful() && jwtResponse != null) {
                    sessionManager.saveAuthToken(jwtResponse.getToken());
                    Toast.makeText(LoginActivity.this, "User logged in!", Toast.LENGTH_LONG).show();
                    userPrefs.edit().putBoolean("DabaoIsLoggedIn", true);
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class).putExtra("username", username));
                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect Credentials! Try again!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}