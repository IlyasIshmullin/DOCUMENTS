package com.example.documents_rewrite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.documents_rewrite.authorization.AuthorizationActivity;
import com.example.documents_rewrite.mainApplication.app.MainActivity;

public class SplashActivity extends AppCompatActivity {
    public static SharedPreferences profile;
    public static final String APP_PREFERENCES = "profile";
    public static final String APP_PREFERENCES_IS_LOGIN = "check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profile = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if(profile.contains(APP_PREFERENCES_IS_LOGIN)) {
            if(profile.getBoolean(APP_PREFERENCES_IS_LOGIN, Boolean.parseBoolean(""))) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Intent intent = new Intent(this, AuthorizationActivity.class);
                startActivity(intent);
                finish();
            }
        }
        else {
            Intent intent = new Intent(this, AuthorizationActivity.class);
            startActivity(intent);
            finish();
        }
    }
}