package com.example.mad_project;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class feedback extends AppCompatActivity {

    Button view,savedetails;
    RatingBar ratingBar;
    EditText multitext,Email;
    DatabaseReference dbref;
    feedback_inc feed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //Initialize.
        view= findViewById(R.id.viewfeed);
        savedetails= findViewById(R.id.submit);
        multitext = findViewById(R.id.TextMultiLine);
        Email  = findViewById(R.id.EmailAddress);
        ratingBar = findViewById(R.id.ratingBar);
        feed = new feedback_inc();


        //update data retrive

        final String msg1,msg2;

        Intent i = getIntent();
        msg1 =i.getStringExtra("msg1");
        msg2 = i.getStringExtra("msg2");
        Email.setText(msg1);
        multitext.setText(msg2);



        //save to data base buitton click event
        savedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedetails();
            }
        });

        //view detales on button click event
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdetails();
            }
        });


    }


      public void savedetails(){
          final String userEnteredEmail = Email.getText().toString().trim();

          DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("feedback_inc");
          dbref = FirebaseDatabase.getInstance().getReference().child("feedback_inc");

          Query checkUser = reference.orderByChild("mail").equalTo(userEnteredEmail);
          checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  if (dataSnapshot.exists()) {
                      Toast.makeText(getApplicationContext(), "already added feedback click view button to see", Toast.LENGTH_SHORT).show();

                  }else{
                      try {
            if (TextUtils.isEmpty(multitext.getText().toString()))
                Toast.makeText(getApplicationContext(), "please write your feedback", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(Email.getText()))
                Toast.makeText(getApplicationContext(), "please enter email address", Toast.LENGTH_SHORT).show();
            else{
                //send data
                feed.setTextml(multitext.getText().toString());
                feed.setMail(Email.getText().toString().trim());
                float rating = ratingBar.getRating();
                feed.setReating(rating);


                //insert
                String a;
                a = Email.getText().toString();
                dbref.child(a).setValue(feed);
                //toast for success
                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
                a=("");
                clearcontrols();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Data not saved", Toast.LENGTH_SHORT).show();
        }
                  }
              }
              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {

              }
          });
    }


    //go to view details page
    public void showdetails() {
        Intent i =  new Intent(this,view_feedbck.class);
        startActivity(i);
    }


    //clear controls
    private void clearcontrols(){

        multitext.setText("");
        Email.setText("");
        ratingBar.setRating(0);
    }
}