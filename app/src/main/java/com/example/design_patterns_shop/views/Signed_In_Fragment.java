package com.example.design_patterns_shop.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.design_patterns_shop.R;
import com.example.design_patterns_shop.view_model.NewItem;
import com.example.design_patterns_shop.view_model.Signed_In_View_Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Signed_In_Fragment extends Fragment {

    private TextView signed_in_user;
    private Button logout_button;
    private FloatingActionButton button_add;

    private Signed_In_View_Model signed_in_view_model;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        signed_in_view_model = new ViewModelProvider(this).get(Signed_In_View_Model.class);
        signed_in_view_model.getUserMutableLiveData().observe(this, firebaseUser -> {

            if(firebaseUser != null){

               signed_in_user.setText("User " + firebaseUser.getEmail() + " is Logged In");

            }

        });

        signed_in_view_model.getLoggedOutMutableLiveData().observe(this, loggedOut -> {

            if(loggedOut){

                Navigation.findNavController(getView()).navigate(R.id.action_signed_In_Fragment_to_login_Fragment);

            }

        });

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.signed_in_fragment, container, false);

        signed_in_user = view.findViewById(R.id.signed_in_user);
        logout_button = view.findViewById(R.id.logout_button);

        FloatingActionButton buttonAdd = view.findViewById(R.id.button_add);

        logout_button.setOnClickListener(v -> {

            signed_in_view_model.logout();

        });



        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getView()).navigate(R.id.action_signed_In_Fragment_to_storeItems_Model);

                /*Intent intent= new Intent(getApplicationContext(), NewItem.class);
                startActivity(intent);*/

            }
        });

        return view;
    }
}
