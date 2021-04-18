package com.example.design_patterns_shop.view_model;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.design_patterns_shop.R;
import com.example.design_patterns_shop.Adapter.MyAdapter;
import com.example.design_patterns_shop.model.StoreItemsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StoreItems extends AppCompatActivity {


    TextView markerText;

    private Signed_In_View_Model signed_in_view_model;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    public DatabaseReference db;


    ArrayList<String> myDataset;
    List<StoreItemsModel> listData;
    MyAdapter mAdapter;
    RecyclerView mRecyclerView;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_fragment);

        signed_in_view_model = new ViewModelProvider(this).get(Signed_In_View_Model.class);

/*        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.notes);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.notes:

                        return true;

                    case R.id.mapSelect:

                        startActivity(new Intent(getApplicationContext(), Maps.class));
                        overridePendingTransition(0,0);
                        return true;

                    *//*case R.id.weather:

                        startActivity(new Intent(getApplicationContext(), Weather.class));
                        overridePendingTransition(0,0);
                        return true;*//*

                    case R.id.details:

                        startActivity(new Intent(getApplicationContext(), Update.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });*/

        EditText edittext = findViewById(R.id.edittext);
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }
        });



        mAuth = FirebaseAuth.getInstance();

        mUser = mAuth.getCurrentUser();
        final String userIDFind = mUser.getUid();

        db = FirebaseDatabase.getInstance().getReference("Items");//"Users")//.child(userIDFind);

        FloatingActionButton buttonAdd = findViewById(R.id.button_add);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager= new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        // prepare the dataset which sources the recycler view



        listData = new ArrayList<>();


        mAdapter = new MyAdapter(listData);

        //Add the divider line
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        // set adapter for the recycler view
        mRecyclerView.setAdapter(mAdapter);
        retrieveUser_firebase();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.w("DBError", "Add Item Pressed");

                Intent intent= new Intent(getApplicationContext(), NewItem.class);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onResume() {

        super.onResume();


    }



    private void filter(String text){

        ArrayList<StoreItemsModel> filteredList = new ArrayList<>();

        for(StoreItemsModel item : listData){

            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){

                filteredList.add(item);

            }

        }

        mAdapter.filterList(filteredList);

    }

    public void retrieveUser_firebase(){


        mUser = mAuth.getCurrentUser();
        String userID = mUser.getUid();

        DatabaseReference fireDBUser= FirebaseDatabase.getInstance().getReference("Items");//.child(userID).child("Note");//.child("title");;

        fireDBUser.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //every //time change data the event listener will execute on data change method

                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    //retrieve all nodes

                    StoreItemsModel data = userSnapshot.getValue(StoreItemsModel.class);
                    listData.add(data);


                    mAdapter.notifyItemInserted(listData.size()-1);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w("DBError", "Cancel Access DB");

            }
        });
    }

    public void viewCart(MenuItem menuitem) {

        signed_in_view_model.logout();


    }

    public void checkout(MenuItem menuitem) {


    }

    public void logout(MenuItem menuitem) {

        signed_in_view_model.logout();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_list, menu);


        return true;
    }
}
