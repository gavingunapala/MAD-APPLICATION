package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class cakeology_cakes extends AppCompatActivity {

    ImageView mainImageView;
    TextView  description, price, weight;
    EditText title,quantity, greeting, candles;
    Button cartBtn;
    DatabaseReference dbRef;
    Cake cake;
//    long maxid = 0;

    String data1, data2, data3, data4;
    int myImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cakeology_cakes);

        mainImageView = findViewById(R.id.mainImageView);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
        weight = findViewById(R.id.weight);

        quantity = (EditText) findViewById(R.id.quantity);
        greeting = (EditText) findViewById(R.id.greeting);
        candles = (EditText) findViewById(R.id.candles);
        cartBtn = (Button) findViewById(R.id.cartBtn);
        cake = new Cake();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Cake");

//        dbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists())
//                    maxid = (dataSnapshot.getChildrenCount());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        //fetching data from home page
        getData();
        setData();

        // add to cart button
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id;
                id = data1;
                int qty = Integer.parseInt(quantity.getText().toString().trim());
                cake.setCake_name(data1);
                cake.setCake_quantity(qty);
                cake.setCake_greeting(greeting.getText().toString().trim());
                cake.setCake_candles(candles.getText().toString().trim());


                dbRef.child(String.valueOf(id)).setValue(cake);

                Toast.makeText(cakeology_cakes.this, "Successfully Inserted", Toast.LENGTH_LONG).show();
                Intent i = new Intent(cakeology_cakes.this,show_details.class);
                i.putExtra("cakenameforshow",id);
                startActivity(i);
            }
        });
    }


    private void getData() {
        if(getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2") && getIntent().hasExtra("data3")
                && getIntent().hasExtra("data4")) {

            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            data3 = getIntent().getStringExtra("data3");
            data4 = getIntent().getStringExtra("data4");
            myImage = getIntent().getIntExtra("myImage", 1);
        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        title.setText(data1);
        description.setText(data2);
        price.setText(data3);
        weight.setText(data4);
        mainImageView.setImageResource(myImage);

    }


}