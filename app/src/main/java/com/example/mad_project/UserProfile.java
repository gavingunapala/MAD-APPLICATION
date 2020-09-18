package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserProfile extends AppCompatActivity {
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Initialize buttons.
        home= findViewById(R.id.backtohome);

        //go to the create account acctivity.
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityhome();
            }
        });
    }

    public void openActivityhome(){
        Intent i =  new Intent(this,Home.class);
        startActivity(i);
    }
}