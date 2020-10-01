package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class view_feedbck extends AppCompatActivity {
    Button delete;
    TextView multitext,Email,rating;
    DatabaseReference dbref;
    feedback_inc feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedbck);

        multitext = findViewById(R.id.viewFeedBack);
        Email = findViewById(R.id.viewMail);
        rating = findViewById(R.id.viewRatings);
        feed = new feedback_inc();
        delete = findViewById(R.id.deketefeedback);




// view feedback

        DatabaseReference readdata = FirebaseDatabase.getInstance().getReference().child("feedback_inc").child("value1");
        readdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    multitext.setText(dataSnapshot.child("textml").getValue().toString());
                    Email.setText(dataSnapshot.child("mail").getValue().toString());
                    rating.setText(dataSnapshot.child("reating").getValue().toString());

                }
                else{
                    Toast.makeText(getApplicationContext(), "no submitions", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //delete feedback button click

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletefeed();
            }
        });


    }

    //delete feed code
    public void deletefeed(){
        dbref = FirebaseDatabase.getInstance().getReference().child("feedback_inc");

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

    //clear controls
    private void clearcontrols(){
        multitext.setText("");
        Email.setText("");
        rating.setText("");
    }
}