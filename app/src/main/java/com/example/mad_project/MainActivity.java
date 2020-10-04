package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button createAccount, login;
    EditText Uname, Password;
    DatabaseReference dbref;
    login_to userprofile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize.
        Uname = findViewById(R.id.usernameforenter);
        Password = findViewById(R.id.passwordforlogin);
        createAccount = findViewById(R.id.create_account);
        login = findViewById(R.id.login_button);

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

    public void openCreateAcount() {
        Intent i = new Intent(this, signIn.class);
        startActivity(i);
    }

    public void openlogin() {

        final String userEnteredUsername = Uname.getText().toString().trim();
        final String userEnteredPassword = Password.getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("signin_inc");

        Query checkUser = reference.orderByChild("txtName").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("txtPw").getValue(String.class);


                    if (passwordFromDB.equals(userEnteredPassword)) {
                        Toast.makeText(getApplicationContext(), "valied user", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, UserProfile.class);
                        intent.putExtra("user",Uname.getText().toString());
                        startActivity(intent);
                        finish();


                    } else {
                        Password.setError("Invalid Password");
//                        Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }else{
                    Password.setError("Invalid Username");
//                    Toast.makeText(getApplicationContext(), "No such User exist", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}