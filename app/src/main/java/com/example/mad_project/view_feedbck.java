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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class view_feedbck extends AppCompatActivity {
    Button delete,mailok,update;
    EditText entermail;
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
        mailok = findViewById(R.id.mailok);
        entermail= findViewById(R.id.entermail);
        update= findViewById(R.id.updatefeed);

        //update button click

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEnteredemail = entermail.getText().toString().trim();
                Intent i = new Intent(view_feedbck.this,feedback.class);
                i.putExtra("msg1",userEnteredemail);
                i.putExtra("msg2",multitext.getText().toString());
                startActivity(i);

            }
        });




// view feedback
        mailok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String userEnteredemail = entermail.getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("feedback_inc");

                Query checkUser = reference.orderByChild("mail").equalTo(userEnteredemail);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            DatabaseReference readdata = FirebaseDatabase.getInstance().getReference().child("feedback_inc").child(userEnteredemail);
                            readdata.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.hasChildren()){
                                        multitext.setText(dataSnapshot.child("textml").getValue().toString());
                                        Email.setText(dataSnapshot.child("mail").getValue().toString());
                                        rating.setText(dataSnapshot.child("reating").getValue().toString());
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "no submission", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                        }else{
                            Toast.makeText(getApplicationContext(), "No submission or wrong Email.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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
        final String userEnteredemail = entermail.getText().toString().trim();
        dbref = FirebaseDatabase.getInstance().getReference().child("feedback_inc");

        DatabaseReference deletedata = FirebaseDatabase.getInstance().getReference().child("feedback_inc");
        deletedata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(userEnteredemail)){
                    dbref=FirebaseDatabase.getInstance().getReference().child("feedback_inc").child(userEnteredemail);
                    dbref.removeValue();
                    clearcontrols();
                    Toast.makeText(getApplicationContext(), "deleted successfully ", Toast.LENGTH_SHORT).show();

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