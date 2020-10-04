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

import java.util.regex.Pattern;

public class signIn extends AppCompatActivity {

    EditText txtID, txtName, txtEmail, txtPw, txtRpw;
    Button button11,btnCancel, btnhome,back;
    DatabaseReference dbref;
    signin_inc sign;
    String pwd;
//    long maxid = 0;

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        txtName = (EditText) findViewById(R.id.editTextTextPersonName3);
        txtEmail = (EditText) findViewById(R.id.editTextTextEmailAddress3);
        txtPw = (EditText) findViewById(R.id.editTextTextPassword2);
        txtRpw = (EditText) findViewById(R.id.editTextTextPassword4);

        back = findViewById(R.id.cancel);
        button11 = (Button)findViewById(R.id.button11);
        btnCancel = (Button) findViewById(R.id.cancel);
        btnhome = (Button) findViewById(R.id.backtohome);

        sign = new signin_inc();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(signIn.this,MainActivity.class);
                startActivity(i);
            }
        });

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
//        };


        button11.setOnClickListener(new View.OnClickListener() {
            String uid;
            String pwd;
            String pwdR;


            @Override
            public void onClick(View v) {
//                sign.setTxtName(txtName.getText().toString().trim());
//                sign.setTxtEmail(txtEmail.getText().toString().trim());
//                sign.setTxtPw(txtPw.getText().toString().trim());
//
//                dbref.child(String.valueOf(maxid+1)).setValue(sign);
//                Toast.makeText(signIn.this, "Your account has been created", Toast.LENGTH_SHORT).show();
                String email = txtEmail.getText().toString();
                pwd = txtPw.getText().toString();
                pwdR = txtRpw.getText().toString();


                try {
                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        txtName.setError("Field cannot be empty");

                    else if ((txtName.getText().toString()).length() >= 15)
                        txtName.setError("Username too long");

                    else if (TextUtils.isEmpty(txtEmail.getText()))
                        txtEmail.setError("Field cannot be empty");

                    else if(!(isValid(email)))
                        txtEmail.setError("Invalid email address");

                    else if (TextUtils.isEmpty(txtPw.getText().toString()))
                        txtPw.setError("Field cannot be empty");

                    else if((txtPw.getText().toString()).length() <= 6)
                        txtPw.setError("Password should contain at least 6 characters");

                    else if (TextUtils.isEmpty(txtRpw.getText().toString()))
                        txtRpw.setError("Field cannot be empty");

                    else if(!pwdR.equals(pwd))
                        txtRpw.setError("Your password is not match with Re-enter password");

                    else{
                        //send data
                        sign.setTxtName(txtName.getText().toString().trim());
                        sign.setTxtEmail(txtEmail.getText().toString().trim());
                        sign.setTxtPw(txtPw.getText().toString().trim());

                        uid = txtName.getText().toString();

                        dbref.child(String.valueOf(uid)).setValue(sign);


                        Toast.makeText(getApplicationContext(), "Your account has been created", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(signIn.this,MainActivity.class);
                         startActivity(i);

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
