package com.example.documents_rewrite.authorization;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.documents_rewrite.MainActivity;
import com.example.documents_rewrite.R;
import com.google.android.material.button.MaterialButton;

public class FillInitialsFragment extends Fragment {

    private MaterialButton sendInitialsButton;
    private ImageButton backToRegisterButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fill_initials, container, false);

        // Init fill initials fragment buttons
        sendInitialsButton = view.findViewById(R.id.send_initials_button);
        backToRegisterButton = view.findViewById(R.id.back_to_register);

        backToRegisterButton.setOnClickListener(view12 -> Navigation.findNavController(view12).navigate(R.id.action_fillInitialsFragment_to_registerFragment));

        sendInitialsButton.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        });
        return view;
    }
}