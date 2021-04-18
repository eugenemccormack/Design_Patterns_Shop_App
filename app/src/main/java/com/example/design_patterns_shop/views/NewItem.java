package com.example.design_patterns_shop.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.design_patterns_shop.R;
import com.example.design_patterns_shop.model.StoreItemsModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewItem extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    public DatabaseReference db;

    ImageView imageView;
    EditText edTitle;
    EditText edManufacturer;
    EditText edCategory;
    EditText edPrice;
    Button addButton;

     Double price = 0.00;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);

        mAuth = FirebaseAuth.getInstance();

        imageView = (ImageView) findViewById(R.id.imageView);
        edTitle = (EditText) findViewById(R.id.title_item);
        edManufacturer = (EditText) findViewById(R.id.manufacturer_item);
        edCategory = (EditText) findViewById(R.id.category_item);
        edPrice = (EditText) findViewById(R.id.price_item);

        addButton = (Button) findViewById(R.id.add_newNote);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = edTitle.getText().toString();
                String manufacturer = edManufacturer.getText().toString();
                String category = edCategory.getText().toString();
                price = Double.parseDouble(edPrice.getText().toString());

                //double c = 2.16;
                Double d = new Double(price);

                if(price == null){


                    edPrice.requestFocus();
                    edPrice.setError("Price Required");

                    Toast.makeText(NewItem.this, "Please Enter a Price", Toast.LENGTH_SHORT).show();

                }




                if(title.trim().isEmpty()){

                    edTitle.requestFocus();
                    edTitle.setError("Title Required");

                    Toast.makeText(NewItem.this, "Please Enter a Title", Toast.LENGTH_SHORT).show();

                }

                else if(manufacturer.trim().isEmpty()){


                    edManufacturer.requestFocus();
                    edManufacturer.setError("Manufacturer Required");

                    Toast.makeText(NewItem.this, "Please Enter a Manufacturer", Toast.LENGTH_SHORT).show();

                }

                else if(category.trim().isEmpty()){


                    edCategory.requestFocus();
                    edCategory.setError("Category Required");

                    Toast.makeText(NewItem.this, "Please Enter a Category", Toast.LENGTH_SHORT).show();

                }





                else {

        /*            try {

                        double price = Double.parseDouble(edPrice.getText().toString());
                    }
                    catch (Exception e) {

                        Log.w("DBError", "Exception: " + e);
                    }*/



                    Log.w("DBError", "Price: " + price);

                    mUser = mAuth.getCurrentUser();
                    String userID = mUser.getUid();
                    db = FirebaseDatabase.getInstance().getReference();
                    StoreItemsModel userNote = new StoreItemsModel(title, manufacturer, category, price);
                    db.child("Items").child(title).setValue(userNote);



                    Toast.makeText(NewItem.this, "New Item Added", Toast.LENGTH_SHORT).show();

                    Intent intent= new Intent(getApplicationContext(), StoreItems.class);
                    startActivity(intent);
                }







            }
        });

    }
}
