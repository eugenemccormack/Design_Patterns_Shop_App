package com.example.design_patterns_shop.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.design_patterns_shop.R;
import com.example.design_patterns_shop.view_model.Login_Register_View_Model;
import com.example.design_patterns_shop.view_model.Signed_In_View_Model;
import com.google.firebase.auth.FirebaseUser;

public class Signed_In_Fragment extends Fragment {

    private TextView signed_in_user;
    private Button logout_button;

    private Signed_In_View_Model signed_in_view_model;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        signed_in_view_model = new ViewModelProvider(this).get(Signed_In_View_Model.class);
        signed_in_view_model.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {

                if(firebaseUser != null){

                   signed_in_user.setText("User " + firebaseUser.getEmail() + " is Logged In");

                }

            }

        });

        signed_in_view_model.getLoggedOutMutableLiveData().observe(this, loggedOut -> {

            if(loggedOut){

                Navigation.findNavController(getView()).navigate(R.id.action_signed_In_Fragment_to_login_Register_Fragment);

            }

        });

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.signed_in_fragment, container, false);

        signed_in_user = view.findViewById(R.id.signed_in_user);
        logout_button = view.findViewById(R.id.logout_button);

        logout_button.setOnClickListener(v -> {

            signed_in_view_model.logout();

        });

        return view;
    }
}