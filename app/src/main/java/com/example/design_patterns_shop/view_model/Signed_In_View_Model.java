package com.example.design_patterns_shop.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.design_patterns_shop.model.Repo;
import com.google.firebase.auth.FirebaseUser;

public class Signed_In_View_Model extends AndroidViewModel {

    private Repo repo;

    private MutableLiveData<FirebaseUser> userMutableLiveData;

    private MutableLiveData<Boolean> loggedOutMutableLiveData;

    public Signed_In_View_Model(@NonNull Application application) {

        super(application);

        repo = new Repo(application);

        userMutableLiveData = repo.getUserMutableLiveData();

        loggedOutMutableLiveData = repo.getLoggedOutMutableLiveData();
    }

    public void logout(){

        repo.logout();

    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutMutableLiveData() {
        return loggedOutMutableLiveData;
    }
}
