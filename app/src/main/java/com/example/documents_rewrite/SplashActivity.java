package com.example.documents_rewrite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.documents_rewrite.authorization.AuthorizationActivity;
import com.example.documents_rewrite.mainApplication.app.MainActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    public static SharedPreferences profile;
    public static final String APP_PREFERENCES = "profile";
    public static final String APP_PREFERENCES_IS_LOGIN = "check";
    public static final String APP_PREFERENCES_NAME = "name";
    public static final String APP_PREFERENCES_SURNAME = "surname";
    public static final String APP_PREFERENCES_USERNAME = "username";
    public static final String APP_PREFERENCES_EMAIL = "e-mail";
    public static final String APP_PREFERENCES_DESCRIPTION = "description";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profile = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = profile.edit();
        editor.putString(APP_PREFERENCES_NAME, "Name").apply();
        editor.putString(APP_PREFERENCES_SURNAME, "Surname").apply();
        editor.putString(APP_PREFERENCES_EMAIL, "E-Mail").apply();
        editor.putString(APP_PREFERENCES_USERNAME, "Username").apply();
        editor.putString(APP_PREFERENCES_DESCRIPTION, "Description").apply();
        if(profile.contains(APP_PREFERENCES_IS_LOGIN)) {
            if(profile.getBoolean(APP_PREFERENCES_IS_LOGIN, Boolean.parseBoolean(""))) {
                Intent intent = new Intent(this, IdentificationActivity.class);
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