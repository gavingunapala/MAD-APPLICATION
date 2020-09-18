package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button createAccount;
    private Button login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize buttons.
        createAccount = findViewById(R.id.create_account);
        login  = findViewById(R.id.login_button);

        //go to the create account acctivity.
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateAcount();
            }
        });

        //go to the create user profile acctivity.
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin();
            }
        });

    }
    public void openCreateAcount(){
        Intent i =  new Intent(this,signIn.class);
        startActivity(i);
    }

    public void openlogin(){
        Intent i =  new Intent(this,UserProfile.class);
        startActivity(i);
    }
}