package com.example.mad_project;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class signin_inc {
    private String txtID;
    private String txtName;
    private String txtEmail;
    private String txtPw;
    private String txtRpw;


    public signin_inc(){

    }


    public String getTxtID() {
        return txtID;
    }

    public void setTxtID(String txtID) {
        this.txtID = txtID;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public String getTxtPw() {
        return txtPw;
    }

    public void setTxtPw(String txtPw) {
        this.txtPw = txtPw;
    }

    public String getTxtRpw() {
        return txtRpw;
    }

    public void setTxtRpw(String txtRpw) {
        this.txtRpw = txtRpw;
    }
}


