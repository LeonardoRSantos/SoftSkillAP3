package com.example.soft.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soft.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {

    List<PostFeedData> listdata;
    Context context;

    //Construtor
    public RecyclerAdapter(Context context, List<PostFeedData> listdata) {
        this.context = context;
        this.listdata = listdata;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);

        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        PostFeedData data = listdata.get(position);
        holder.mJobTitle.setText(data.getJobTitle());
        holder.mJobLocation.setText(data.getJobLocation());
        holder.mCompanyName.setText(data.getCompanyName());
        holder.mContact.setText(data.getContact());
        holder.mJobDescription.setText(data.getJobDescription());
        holder.mEmailID.setText(data.getEmailID());
        Log.i("email",data.getEmailID()+"WTF "+data.getJobTitle());
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView mJobTitle, mCompanyName, mJobLocation, mContact, mJobDescription, mEmailID;

        public MyHolder(View itemView) {
            super(itemView);
            mJobTitle = itemView.findViewById(R.id.vaddress1);
            mJobLocation =  itemView.findViewById(R.id.vaddress2);
            mCompanyName =  itemView.findViewById(R.id.vaddress3);
            mContact = itemView.findViewById(R.id.vaddress5);
            mJobDescription = itemView.findViewById(R.id.vaddress4);
            mEmailID = itemView.findViewById(R.id.vaddress6);
        }
    }
}


