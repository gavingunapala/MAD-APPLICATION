//package com.example.mad_project;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//
//public class myAdepterCart extends FirebaseRecyclerAdapter<Cake,myAdepterCart.myViewHolder> {
//    public myAdepterCart(@NonNull FirebaseRecyclerOptions<Cake> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Cake Cake) {
//
//        holder.cakename.setText(Cake.getCake_name());
//        holder.cakeprice.setText(Cake.getCake_greeting());
//        holder.quentity.setText(Cake.getCake_quantity());
//
//    }
//
//    @NonNull
//    @Override
//    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.my_cart_row, parent, false);
//
//        return new myViewHolder(view);
//    }
//
//    class myViewHolder extends RecyclerView.ViewHolder{
//        TextView cakename,cakeprice,quentity;
//
//        public myViewHolder(@NonNull View itemView) {
//            super(itemView);
//            cakename=(TextView)itemView.findViewById(R.id.cakename);
//            cakeprice=(TextView)itemView.findViewById(R.id.price);
//            quentity=(TextView)itemView.findViewById(R.id.quentity123);
//        }
//    }
//}
