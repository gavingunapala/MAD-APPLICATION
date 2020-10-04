package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {

    RecyclerView recyclerView;
    Button home,cart;


    String s1[], s2[], s3[], s4[];
    int images[] = {R.drawable.special_fondant_cake, R.drawable.rose_cupcakes, R.drawable.butter_cream_cake, R.drawable.chocolate_dripped_cake, R.drawable.ribbon_cake,
            R.drawable.unicorn_cup_cakes, R.drawable.butterscotch_cake, R.drawable.anniversary_cake};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home = findViewById(R.id.profile);
        cart = findViewById(R.id.cart);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this,UserProfile.class);
                startActivity(i);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this,Addtocart.class);
                startActivity(i);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.cake_list);
        s2 = getResources().getStringArray(R.array.description);
        s3 = getResources().getStringArray(R.array.price_list);
        s4 = getResources().getStringArray(R.array.weight_list);

        MtAdapter mtAdapter = new MtAdapter(this, s1, s2, s3, s4, images );
        recyclerView.setAdapter(mtAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}