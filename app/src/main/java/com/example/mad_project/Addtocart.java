package com.example.mad_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addtocart extends AppCompatActivity {
    private RecyclerView addtocartlist;
    private DatabaseReference addtocartDB;
    Button delete;
    DatabaseReference dbref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtocart);
        addtocartDB = FirebaseDatabase.getInstance().getReference().child("Cake");
        addtocartDB.keepSynced(true);

        addtocartlist =(RecyclerView)findViewById(R.id.myrecycleview);
        addtocartlist.setHasFixedSize(true);
        addtocartlist.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Cake,addtocartViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Cake, addtocartViewHolder>
                (Cake.class,R.layout.my_cart_row,addtocartViewHolder.class,addtocartDB) {
            @Override
            protected void populateViewHolder(addtocartViewHolder addtocartViewHolder, Cake Cake, int i) {
                addtocartViewHolder.setCake_name(Cake.getCake_name());
                addtocartViewHolder.setCake_quantity(Cake.getCake_quantity().toString());
                addtocartViewHolder.setCake_greeting(Cake.getCake_greeting());

            }
        };
        addtocartlist.setAdapter(firebaseRecyclerAdapter);
    }


    public static class addtocartViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public addtocartViewHolder(View itemView){
            super(itemView);
            mView= itemView;
        }
        public void setCake_name(String cakename){
            TextView post_title = (TextView)mView.findViewById(R.id.cakename);
            post_title.setText(cakename);

        }
        public void setCake_quantity(String quen){
            TextView Quentity = (TextView)mView.findViewById(R.id.quentity123);
            Quentity.setText(quen);
        }
        public void setCake_greeting(String price){
            TextView Quentity = (TextView)mView.findViewById(R.id.price);
            Quentity.setText(price);
        }

    }



}