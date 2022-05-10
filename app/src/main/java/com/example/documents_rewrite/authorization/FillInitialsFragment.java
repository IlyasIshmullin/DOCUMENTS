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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FillInitialsFragment extends Fragment {

    private MaterialButton sendInitialsButton;
    private ImageButton backToRegisterButton;

    private TextInputLayout nameTextLayout;
    private TextInputLayout surnameTextLayout;
    private TextInputLayout usernameTextLayout;
    private TextInputEditText edName;
    private TextInputEditText edSurname;
    private TextInputEditText edUsername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fill_initials, container, false);

        // Init text fields
        nameTextLayout = view.findViewById(R.id.fill_initials_name_text_input_layout);
        surnameTextLayout = view.findViewById(R.id.fill_initials_surname_text_input_layout);
        usernameTextLayout = view.findViewById(R.id.fill_initials_username_text_input_layout);
        edName = view.findViewById(R.id.fill_initials_edit_text_name);
        edSurname = view.findViewById(R.id.fill_initials_edit_text_surname);
        edUsername = view.findViewById(R.id.fill_initials_edit_text_username);

        // Init fill initials fragment buttons
        sendInitialsButton = view.findViewById(R.id.send_initials_button);
        backToRegisterButton = view.findViewById(R.id.back_to_register);

        backToRegisterButton.setOnClickListener(view12 -> Navigation.findNavController(view12).navigate(R.id.action_fillInitialsFragment_to_registerFragment));

        sendInitialsButton.setOnClickListener(view1 -> {
            // TODO User newUser = new User(name, surname, username);
            // TODO data in DB
            // TODO is authorized
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        });
        return view;
    }
}