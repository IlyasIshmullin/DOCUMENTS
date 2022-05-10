package com.example.documents_rewrite.authorization;

import static com.example.documents_rewrite.SplashActivity.APP_PREFERENCES_IS_LOGIN;
import static com.example.documents_rewrite.SplashActivity.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.documents_rewrite.R;

public class AuthorizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
    }
}