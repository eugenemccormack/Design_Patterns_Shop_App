package com.example.design_patterns_shop.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.design_patterns_shop.R;
import com.example.design_patterns_shop.view_model.Register_View_Model;
import com.google.firebase.auth.FirebaseUser;

public class Register_Fragment extends Fragment {

    private EditText name_editText_register;
    private EditText email_editText_register;
    private EditText password_editText_login;
    private EditText phone_editText_register;
    private EditText address1_editText_register;
    private EditText address2_editText_register;
    private TextView already_signed_up;


    private Button register_button_register;

    private Register_View_Model register_view_model;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        register_view_model = new ViewModelProvider(this).get(Register_View_Model.class);
        register_view_model.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {

                if(firebaseUser != null){

                    //Toast.makeText(getContext(), "User Successfully Created", Toast.LENGTH_SHORT).show();

                    Navigation.findNavController(getView()).navigate(R.id.action_register_Fragment_to_signed_In_Fragment);

                }

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.register_fragment, container, false);

        name_editText_register = view.findViewById(R.id.name_editText_register);
        email_editText_register = view.findViewById(R.id.email_editText_register);
        password_editText_login = view.findViewById(R.id.password_editText_login);
        phone_editText_register = view.findViewById(R.id.phone_editText_register);
        address1_editText_register = view.findViewById(R.id.address1_editText_register);
        address2_editText_register = view.findViewById(R.id.address2_editText_register);

        register_button_register = view.findViewById(R.id.register_button_register);
        already_signed_up = view.findViewById(R.id.already_signed_up);

        register_button_register.setOnClickListener(v -> {

            String name = name_editText_register.getText().toString().trim();
            String email = email_editText_register.getText().toString().trim();
            String password = password_editText_login.getText().toString().trim();
            String phone = phone_editText_register.getText().toString().trim();
            String address1 = address1_editText_register.getText().toString().trim();
            String address2 = address2_editText_register.getText().toString().trim();

            if (name.trim().isEmpty()) {

                name_editText_register.requestFocus();
                name_editText_register.setError("Name Required");

            } else if (email.trim().isEmpty()) {

                email_editText_register.requestFocus();
                email_editText_register.setError("Email Required");

            }

             else if (password.trim().isEmpty()) {

                password_editText_login.requestFocus();
                password_editText_login.setError("Password Required");

        }

            else if (phone.trim().isEmpty()) {

                phone_editText_register.requestFocus();
                phone_editText_register.setError("Phone Required");

    }


            else if (address1.trim().isEmpty()) {

               address1_editText_register.requestFocus();
               address1_editText_register.setError("Address Required");

            }

            else if (address2.trim().isEmpty()) {

                address2_editText_register.requestFocus();
                address2_editText_register.setError("Address Required");

            }


            else{


                //if(email.length() > 0 && password.length() > 5 ){

                register_view_model.register(email, password, name, phone, address1, address2);

            }



        });

        already_signed_up.setOnClickListener(v -> {

            Navigation.findNavController(getView()).navigate(R.id.action_register_Fragment_to_login_Fragment);

        });

        return view;
    }


}
