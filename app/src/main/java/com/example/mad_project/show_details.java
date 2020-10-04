package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class show_details extends AppCompatActivity {

    TextView a,b,c,d;
    Button updateBtn;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        a = (TextView) findViewById(R.id.nameView);
        b = (TextView) findViewById(R.id.quantityView);
        c = (TextView) findViewById(R.id.greetingView);
        d = (TextView) findViewById(R.id.candlesView);
        updateBtn =findViewById(R.id.updateBtn);

        final String abc;
        Intent i = getIntent();
        abc=i.getStringExtra("cakenameforshow");

        Intent intent  = new Intent(show_details.this,cakeology_cakes.class);
        startActivity(intent);




        dbRef = FirebaseDatabase.getInstance().getReference().child("Cake").child(abc);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                a.setText(snapshot.child("cake_name").getValue().toString());
                b.setText(snapshot.child("cake_quantity").getValue().toString());
                c.setText(snapshot.child("cake_greeting").getValue().toString());
                d.setText(snapshot.child("cake_candles").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    /*public void update(View view) {
        if(isQuantityChanged() || isGreetingChanged()) {
            Toast.makeText(this, "Successfully Updated!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isGreetingChanged() {
    }

    private boolean isQuantityChanged() {
    }*/

}

