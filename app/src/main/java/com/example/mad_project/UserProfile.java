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

public class UserProfile extends AppCompatActivity {
    private Button home,update,feedback,delete;
    EditText  username,email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        //Initialize buttons.
        home= findViewById(R.id.homeProfile);
        update= findViewById(R.id.updateProfile);
        feedback= findViewById(R.id.feedbackProfile);
        delete= findViewById(R.id.deleteProfile);



        username= findViewById(R.id.username);
        email= findViewById(R.id.EMAIL);
        password= findViewById(R.id.PasswordProfile);

        final String a;
        Intent intent = getIntent();
        a=intent.getStringExtra("user");



        //FEEDBACK NAVIGATION

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(UserProfile.this,feedback.class);
                startActivity(intent1);
            }
        });


        DatabaseReference readdata = FirebaseDatabase.getInstance().getReference().child("signin_inc").child(a);
        readdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    username.setText(dataSnapshot.child("txtName").getValue().toString());
                    email.setText(dataSnapshot.child("txtEmail").getValue().toString());
                    password.setText(dataSnapshot.child("txtPw").getValue().toString());

                }
                else{
                    Toast.makeText(getApplicationContext(), "no submission", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        //go to the create account acctivity.
        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openActivityhome();
            }
        });
    }

    public void openActivityhome(){
        Intent i =  new Intent(this,home.class);
        startActivity(i);
    }
}