package com.example.documents_rewrite.authorization;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.documents_rewrite.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordFragment extends Fragment {
    private ImageButton backToAuthorization;
    private MaterialButton resetButton;
    private TextInputEditText resetPasswordEmailAddress;

    private FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        backToAuthorization = view.findViewById(R.id.back_to_authorization);
        resetButton = view.findViewById(R.id.reset_password_button);

        resetPasswordEmailAddress = view.findViewById(R.id.reset_password_edit_text_email);
        firebaseAuth = FirebaseAuth.getInstance();
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.sendPasswordResetEmail(resetPasswordEmailAddress.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), resetPasswordEmailAddress.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        backToAuthorization.setOnClickListener(view12 -> Navigation.findNavController(view12).navigate(R.id.action_resetPasswordFragment_to_authorizationFragment));
        return view;
    }
}
