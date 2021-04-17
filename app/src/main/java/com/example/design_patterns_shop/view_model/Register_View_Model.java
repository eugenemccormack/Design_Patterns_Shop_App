package com.example.design_patterns_shop.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.design_patterns_shop.model.Repo;
import com.google.firebase.auth.FirebaseUser;

public class Register_View_Model extends AndroidViewModel {

    private Repo repo;

    private MutableLiveData<FirebaseUser> userMutableLiveData;


    public Register_View_Model(@NonNull Application application) {
        super(application);

        repo = new Repo(application);

        userMutableLiveData = repo.getUserMutableLiveData();



    }

    public void register(String email, String password, String name, String phone, String address1, String address2){

        repo.register(email, password, name, phone, address1, address2);

    }

/*    public void login(String email, String password){

        repo.login(email, password);

    }*/

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

}
