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

    public Repo(Application application){

        this.application = application;

        firebaseAuth = FirebaseAuth.getInstance();

        userMutableLiveData = new MutableLiveData<>();

    }

    public void register(String email, String password){

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(application.getMainExecutor(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            userMutableLiveData.postValue(firebaseAuth.getCurrentUser());

                        }

                        else{

                            Toast.makeText(application, "Registration Failed, Try Again" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });



    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
