package com.example.documents_rewrite.authorization;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.documents_rewrite.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class RegisterFragment extends Fragment {
    private FirebaseAuth firebaseAuth;

    private MaterialButton loginFragmentButton;
    private MaterialButton registerButton;

    private TextInputLayout registerEmailTextLayout;
    private TextInputLayout registerPasswordTextLayout;
    private TextInputLayout registerConfirmPasswordTextLayout;
    private TextInputEditText edRegisterEmail;
    private TextInputEditText edRegisterPassword;
    private TextInputEditText edRegisterConfirmPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Init text fields
        registerEmailTextLayout = view.findViewById(R.id.register_email_text_input_layout);
        registerPasswordTextLayout = view.findViewById(R.id.register_password_text_input_layout);
        registerConfirmPasswordTextLayout = view.findViewById(R.id.register_confirm_password_text_input_layout);
        edRegisterEmail = view.findViewById(R.id.register_edit_text_email);
        edRegisterPassword = view.findViewById(R.id.register_edit_text_password);
        edRegisterConfirmPassword = view.findViewById(R.id.register_edit_text_confirm_password);

        // Init firebase authorization
        firebaseAuth = FirebaseAuth.getInstance();

        // Init register fragment buttons
        loginFragmentButton = view.findViewById(R.id.login_fragment_button);
        registerButton = view.findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edRegisterEmail.getText().toString().isEmpty())
                    registerEmailTextLayout.setError("Enter email");
                else if(edRegisterPassword.getText().toString().length() < 8)
                    registerPasswordTextLayout.setError("Password must be at least 8 characters long!");
                else if(!edRegisterPassword.getText().toString().equals(edRegisterConfirmPassword.getText().toString()))
                    registerConfirmPasswordTextLayout.setError("Passwords must be the same");
                else {
                    registerNewUser(edRegisterEmail.getText().toString(),
                            edRegisterPassword.getText().toString(), view);
                }
            }
        });

        loginFragmentButton.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_registerFragment_to_authorizationFragment));
        return view;
    }

    protected void registerNewUser(String email, String password, View view){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful())
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_fillInitialsFragment);
            else
                Toast.makeText(getActivity(), "Please, check email and password", Toast.LENGTH_LONG).show();

        });
    }
}