package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signIn extends AppCompatActivity {

    EditText txtID, txtName, txtEmail, txtPw, txtRpw;
    Button button11,btnCancel, btnhome;
    DatabaseReference dbref;
    signin_inc sign;
//    long maxid = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        txtName = (EditText) findViewById(R.id.editTextTextPersonName3);
        txtEmail = (EditText) findViewById(R.id.editTextTextEmailAddress3);
        txtPw = (EditText) findViewById(R.id.editTextTextPassword2);
        txtRpw = (EditText) findViewById(R.id.editTextTextPassword4);

        button11 = (Button)findViewById(R.id.button11);
        btnCancel = (Button) findViewById(R.id.cancel);
        btnhome = (Button) findViewById(R.id.backtohome);

        sign = new signin_inc();

        dbref = FirebaseDatabase.getInstance().getReference().child("signin_inc");

//        dbref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists())
//                    maxid = (snapshot.getChildrenCount());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a;


                try {
                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "You can not keep this field empty", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtEmail.getText()))
                        Toast.makeText(getApplicationContext(), "You can not keep this field empty", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtPw.getText()))
                        Toast.makeText(getApplicationContext(), "You can not keep this field empty", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtRpw.getText()))
                        Toast.makeText(getApplicationContext(), "You can not keep this field empty", Toast.LENGTH_SHORT).show();
                    else{
                        //send data
                        sign.setTxtName(txtName.getText().toString().trim());
                        sign.setTxtEmail(txtEmail.getText().toString().trim());
                        sign.setTxtPw(txtPw.getText().toString().trim());

                        a = txtName.getText().toString();

                        dbref.child(String.valueOf(a)).setValue(sign);


                        Toast.makeText(getApplicationContext(), "Your account has been created", Toast.LENGTH_SHORT).show();
                        clearcontrols();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }

    //    public void createProfile(){
//
//        try {
//            if (TextUtils.isEmpty(txtName.getText().toString()))
//                Toast.makeText(getApplicationContext(), "You can not keep this field empty", Toast.LENGTH_SHORT).show();
//            else if (TextUtils.isEmpty(txtEmail.getText()))
//                Toast.makeText(getApplicationContext(), "You can not keep this field empty", Toast.LENGTH_SHORT).show();
//            else if (TextUtils.isEmpty(txtPw.getText()))
//                Toast.makeText(getApplicationContext(), "You can not keep this field empty", Toast.LENGTH_SHORT).show();
//            else if (TextUtils.isEmpty(txtRpw.getText()))
//                Toast.makeText(getApplicationContext(), "You can not keep this field empty", Toast.LENGTH_SHORT).show();
//            else{
//                //send data
//                sign.setTxtName(txtName.getText().toString().trim());
//                sign.setTxtEmail(txtEmail.getText().toString().trim());
//                sign.setTxtPw(txtPw.getText().toString().trim());
//
//                dbref.child(String.valueOf(maxid+1)).setValue(sign);
//
//
//                Toast.makeText(getApplicationContext(), "Your account has been created", Toast.LENGTH_SHORT).show();
//                clearcontrols();
//            }
//        }catch (Exception e){
//            Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_SHORT).show();
//        }
//    }
    private void clearcontrols(){
        txtName.setText("");
        txtEmail.setText("");
        txtPw.setText("");
        txtRpw.setText("");
    }

}
