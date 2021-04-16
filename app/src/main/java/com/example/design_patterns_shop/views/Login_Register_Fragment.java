package com.example.design_patterns_shop.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.design_patterns_shop.R;
import com.example.design_patterns_shop.view_model.Login_Register_View_Model;
import com.google.firebase.auth.FirebaseUser;

public class Login_Register_Fragment extends Fragment {

    private EditText email_editText_login;
    private EditText password_editText_login;
    private Button login_button_login;
    private Button register_button_login;

    private Login_Register_View_Model login_register_view_model;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        login_register_view_model = new ViewModelProvider(this).get(Login_Register_View_Model.class);
        login_register_view_model.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {

                if(firebaseUser != null){

                    //Toast.makeText(getContext(), "User Successfully Created", Toast.LENGTH_SHORT).show();

                    Navigation.findNavController(getView()).navigate(R.id.action_login_Register_Fragment_to_signed_In_Fragment);

                }

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_register_fragment, container, false);

        email_editText_login = view.findViewById(R.id.email_editText_login);
        password_editText_login = view.findViewById(R.id.password_editText_login);
        login_button_login = view.findViewById(R.id.login_button_login);
        register_button_login = view.findViewById(R.id.register_button_login);

        register_button_login.setOnClickListener(v -> {

            String email = email_editText_login.getText().toString();
            String password = password_editText_login.getText().toString();

            if(email.length() > 0 && password.length() > 5 ){

                login_register_view_model.register(email, password);

            }

        });

        login_button_login.setOnClickListener(v -> {

            String email = email_editText_login.getText().toString();
            String password = password_editText_login.getText().toString();

            if(email.length() > 0 && password.length() > 0 ){

                login_register_view_model.login(email, password);

            }

        });


        return view;
    }
}
