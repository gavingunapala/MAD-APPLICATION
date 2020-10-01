package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addtocart extends AppCompatActivity {
    private RecyclerView addtocartlist;
    private DatabaseReference addtocartDB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtocart);
        addtocartDB = FirebaseDatabase.getInstance().getReference().child("addtocart_inc");
        addtocartDB.keepSynced(true);

        addtocartlist =(RecyclerView)findViewById(R.id.myrecycleview);
        addtocartlist.setHasFixedSize(true);
        addtocartlist.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<addtocart_inc,addtocartViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<addtocart_inc, addtocartViewHolder>
                (addtocart_inc.class,R.layout.my_cart_row,addtocartViewHolder.class,addtocartDB) {
            @Override
            protected void populateViewHolder(addtocartViewHolder addtocartViewHolder, addtocart_inc addtocart_inc, int i) {
                addtocartViewHolder.setQuentity(addtocart_inc.getQauntity());
                addtocartViewHolder.setTitle(addtocart_inc.getCakename());



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
        public void setTitle(String cakename){
            TextView post_title = (TextView)mView.findViewById(R.id.cakename);
            post_title.setText(cakename);

        }
        public void setQuentity(String quen){
            TextView Quentity = (TextView)mView.findViewById(R.id.quentity123);
            Quentity.setText(quen);
        }

    }

}