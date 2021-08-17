package com.example.adprojteam4;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private SessionManager sessionManager;

    public AuthInterceptor(Context context) {
        this.sessionManager = new SessionManager(context);
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (request.url().encodedPath().equals("/register")
                || request.url().encodedPath().equals("/authenticate")) {
            return chain.proceed(request);
        }

        Request.Builder requestBuilder = chain.request().newBuilder();
        String token = this.sessionManager.fetchAuthToken();
        requestBuilder.addHeader("Authorization", "Bearer " + token);

        return chain.proceed(requestBuilder.build());
    }
}
