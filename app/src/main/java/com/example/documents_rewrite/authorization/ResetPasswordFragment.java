package com.example.documents_rewrite.authorization;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.documents_rewrite.R;

public class ResetPasswordFragment extends Fragment {
    private ImageButton backToAuthorization;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        backToAuthorization = view.findViewById(R.id.back_to_authorization);

        backToAuthorization.setOnClickListener(view12 -> Navigation.findNavController(view12).navigate(R.id.action_resetPasswordFragment_to_authorizationFragment));
        return view;
    }
}