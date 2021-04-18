package com.example.design_patterns_shop.model;

import android.app.Application;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Repo {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    public DatabaseReference db;

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

    public void register(String email, String password, String name, String phone, String address1, String address2){

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(application.getMainExecutor(), task -> {

                    if(task.isSuccessful()){

                        userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                        FirebaseUser u = firebaseAuth.getCurrentUser();
                        String UserID = u.getUid();
                        db = FirebaseDatabase.getInstance().getReference();
                        UserModel users = new UserModel(name, phone, address1, address2);
                        db.child("Users").child(UserID).setValue(users);

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
