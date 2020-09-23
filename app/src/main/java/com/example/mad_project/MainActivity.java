package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    Button createAccount,login;
    EditText Uname,Password;
    DatabaseReference dbref;
    login_to userprofile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize.
        Uname = findViewById(R.id.username_enter);
        Password = findViewById(R.id.passwordforlogin);
        createAccount = findViewById(R.id.create_account);
        login  = findViewById(R.id.login_button);

        userprofile = new login_to();

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

    //method to clear all user inputs

    private void clearControls(){
        Uname.setText("");
        Password.setText("");
    }

}