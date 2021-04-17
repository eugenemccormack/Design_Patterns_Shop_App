package com.example.design_patterns_shop.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.design_patterns_shop.model.Repo;
import com.google.firebase.auth.FirebaseUser;

public class Login_View_Model extends AndroidViewModel {

    private Repo repo;

    private MutableLiveData<FirebaseUser> userMutableLiveData;


    public Login_View_Model(@NonNull Application application) {
        super(application);

        repo = new Repo(application);

        userMutableLiveData = repo.getUserMutableLiveData();



    }

/*    public void register(String email, String password){

        repo.register(email, password);

    }*/

    public void login(String email, String password){

        repo.login(email, password);

    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
