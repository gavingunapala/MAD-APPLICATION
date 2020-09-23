package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedback extends AppCompatActivity {

    Button view,delete,savedetails;
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
        delete = findViewById(R.id.deletefeed);
        multitext = findViewById(R.id.TextMultiLine);
        Email  = findViewById(R.id.EmailAddress);
        ratingBar = findViewById(R.id.ratingBar);
        feed = new feedback_inc();


        //save to data base.
        savedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              savedetails();
            }
        });
    }
    public void savedetails(){
        dbref = FirebaseDatabase.getInstance().getReference().child("feedback_inc");

        try {
            if (TextUtils.isEmpty(multitext.getText().toString()))
                Toast.makeText(getApplicationContext(), "please write your feedback", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(Email.getText()))
                Toast.makeText(getApplicationContext(), "please enter email address", Toast.LENGTH_SHORT).show();
            else{
                //send data
                feed.setTextml(multitext.getText().toString());
                feed.setMail(Email.getText().toString().trim());
            //    feed.setReating(ratingBar);


                //insert
                dbref.push().setValue(feed);

                //toast for success
                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
                clearcontrols();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Data not saved", Toast.LENGTH_SHORT).show();
        }
    }
private void clearcontrols(){
    multitext.setText("");
    Email.setText("");
    ratingBar.setRating(0);
}
}