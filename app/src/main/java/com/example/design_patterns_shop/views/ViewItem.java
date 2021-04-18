package com.example.design_patterns_shop.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.design_patterns_shop.Adapter.MyAdapter;
import com.example.design_patterns_shop.R;
import com.example.design_patterns_shop.model.CartModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewItem extends AppCompatActivity {

    public DatabaseReference db;
    private FirebaseAuth firebaseAuth;

    Uri myUri;

    Button addToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item);

        Intent intent = getIntent();

        final String image = intent.getStringExtra(MyAdapter.MESSAGE_KEY);
        ImageView text1 = (ImageView) findViewById(R.id.imageView2);

        myUri = Uri.parse(image);

        Glide.with(ViewItem.this).load(myUri).into(text1);

        final String title = intent.getStringExtra(MyAdapter.MESSAGE_KEY1);
        TextView text2 = (TextView) findViewById(R.id.textView_View);
        text2.setText(title);

        final String manufacturer = intent.getStringExtra(MyAdapter.MESSAGE_KEY2);
        TextView text3 = (TextView) findViewById(R.id.textView2_View);
        text3.setText(manufacturer);

        final String category = intent.getStringExtra(MyAdapter.MESSAGE_KEY3);
        TextView text4 = (TextView) findViewById(R.id.category_textView);
        text4.setText(category);

        double price = intent.getDoubleExtra(MyAdapter.MESSAGE_KEY4, 0);
        TextView text5 = (TextView) findViewById(R.id.price_textView);
        text5.setText("€"+price);

        addToCart = (Button) findViewById(R.id.add_to_cart_button);

        addToCart.setOnClickListener(v -> {

            firebaseAuth = FirebaseAuth.getInstance();

            FirebaseUser u = firebaseAuth.getCurrentUser();

            String UserID = u.getUid();

            db = FirebaseDatabase.getInstance().getReference();

            CartModel cart = new CartModel(image, title, manufacturer, category, price);

            db.child("Users").child(UserID).child("Cart").child(title).setValue(cart);

            Toast.makeText(ViewItem.this, "Item Added to your Cart", Toast.LENGTH_SHORT).show();


        });




/*        final String stock = intent.getStringExtra(MyAdapter.MESSAGE_KEY5);
        TextView text6 = (TextView) findViewById(R.id.stock_textView);
        text3.setText(stock);*/
    }
}


