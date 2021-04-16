package com.example.design_patterns_shop.model;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.invoke.MutableCallSite;

public class Repo {

    private Application application;

    private FirebaseAuth firebaseAuth;

    private MutableLiveData<FirebaseUser> userMutableLiveData;

    private MutableLiveData<Boolean> loggedOutMutableLiveData;

    public Repo(Application application){

        this.application = application;

        firebaseAuth = FirebaseAuth.getInstance();

        userMutableLiveData = new MutableLiveData<>();

        loggedOutMutableLiveData = new MutableLiveData<>();

        if(firebaseAuth.getCurrentUser() != null){

            userMutableLiveData.postValue(firebaseAuth.getCurrentUser());

            loggedOutMutableLiveData.postValue(false);

        }

    }

    public void register(String email, String password){

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(application.getMainExecutor(), task -> {

                    if(task.isSuccessful()){

                        userMutableLiveData.postValue(firebaseAuth.getCurrentUser());

                    }

                    else{

                        Toast.makeText(application, "Registration Failed, Try Again" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                });



    }

    public void login(String email, String password){

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(application.getMainExecutor(), task -> {

                    if(task.isSuccessful()){

                        userMutableLiveData.postValue(firebaseAuth.getCurrentUser());

                    }

                    else{

                        Toast.makeText(application, "Login Failed, Try Again" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                });



    }

    public void logout(){

        firebaseAuth.signOut();

        loggedOutMutableLiveData.postValue(true);

    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutMutableLiveData() {
        return loggedOutMutableLiveData;
    }
}
