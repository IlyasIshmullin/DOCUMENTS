package com.example.documents_rewrite.authorization;

import static com.example.documents_rewrite.SplashActivity.APP_PREFERENCES_IS_LOGIN;
import static com.example.documents_rewrite.SplashActivity.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.documents_rewrite.mainApplication.app.MainActivity;
import com.example.documents_rewrite.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class AuthorizationFragment extends Fragment {
    private MaterialButton registerFragmentButton;
    private MaterialButton logInButton;
    private TextView forgotPassword;

    private FirebaseAuth firebaseAuth;

    private TextInputLayout authorizationEmailTextLayout;
    private TextInputLayout authorizationPasswordTextLayout;
    private TextInputEditText edAuthorizationEmail;
    private TextInputEditText edAuthorizationPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authorization, container, false);

        // Init firebase authorization
        firebaseAuth = FirebaseAuth.getInstance();

        // Init buttons
        registerFragmentButton = view.findViewById(R.id.register_fragment_button);
        forgotPassword = view.findViewById(R.id.forgot_password_button);
        logInButton = view.findViewById(R.id.login_button);

        // Init text fields
        authorizationEmailTextLayout = view.findViewById(R.id.authorization_email_text_input_layout);
        authorizationPasswordTextLayout = view.findViewById(R.id.authorization_password_text_input_layout);
        edAuthorizationEmail = view.findViewById(R.id.authorization_edit_text_email);
        edAuthorizationPassword = view.findViewById(R.id.authorization_edit_text_password);

        registerFragmentButton.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_authorizationFragment_to_registerFragment));

        forgotPassword.setOnClickListener(view12 -> {
            forgotPassword.setTextColor(Color.rgb(0,0, 0));
            Navigation.findNavController(view12).navigate(R.id.action_authorizationFragment_to_resetPasswordFragment);
        });

        logInButton.setOnClickListener(view13 -> {
            if(!authorizationUser(edAuthorizationEmail.getText().toString(), edAuthorizationPassword.getText().toString())){
                authorizationEmailTextLayout.setError("Enter your email");
                authorizationPasswordTextLayout.setError("Enter password");
            }
        });
        return view;
    }

    protected boolean authorizationUser(String email, String password){
        if(email.isEmpty() || password.isEmpty()){
             return false;
        } else{
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            SharedPreferences.Editor editor = profile.edit();
                            editor.putBoolean(APP_PREFERENCES_IS_LOGIN, true).apply();
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().finish();
                        }
                        else
                            Toast.makeText(getActivity(), "Please, check email and password", Toast.LENGTH_LONG).show();
                    });
        }
        return true;
    }
}