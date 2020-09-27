package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class home extends AppCompatActivity {
    RecyclerView recyclerView;

    String s1[], s2[];
    int images[] = {R.drawable.special_fondant_cake, R.drawable.unicorn_cup_cakes, R.drawable.chocolate_dripped_cake, R.drawable.ribbon_cake,
            R.drawable.anniversary_cake};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.cake_list);
        s2 = getResources().getStringArray(R.array.description);

        MtAdapter mtAdapter = new MtAdapter(this, s1, s2, images );
        recyclerView.setAdapter(mtAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}

