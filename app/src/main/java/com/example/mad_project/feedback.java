package com.example.mad_project;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletedata();
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
                float rating = ratingBar.getRating();
                feed.setReating(rating);


                //insert
//                String a;
//                a = Email.getText().toString();

               dbref.child("value1").setValue(feed);
                dbref.push().setValue(feed);

                //toast for success
                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();
                clearcontrols();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Data not saved", Toast.LENGTH_SHORT).show();
        }
    }

    //show details from data base

    public void showdetails() {
       //get usrprofile name
        String a;
        a = Email.getText().toString();



        DatabaseReference readdata = FirebaseDatabase.getInstance().getReference().child("feedback_inc").child("value1");
        readdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    multitext.setText(dataSnapshot.child("textml").getValue().toString());
                    Email.setText(dataSnapshot.child("mail").getValue().toString());
                    Toast.makeText(getApplicationContext(), "your rating is hidden cant show", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(), "no submitions", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deletedata(){
        DatabaseReference deletedata = FirebaseDatabase.getInstance().getReference().child("feedback_inc");
        deletedata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("value1")){
                    dbref=FirebaseDatabase.getInstance().getReference().child("feedback_inc").child("value1");
                    dbref.removeValue();
                    clearcontrols();
                    Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(), "not deleted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




private void clearcontrols(){
    multitext.setText("");
    Email.setText("");
    ratingBar.setRating(0);
}
}