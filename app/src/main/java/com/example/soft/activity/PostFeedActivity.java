package com.example.soft.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soft.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostFeedActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<PostFeedData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_feed);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(PostFeedActivity.this)
                        .setIcon(android.R.drawable.stat_sys_warning)
                        .setTitle("Profissão")
                        .setMessage("Tem certeza de que deseja publicar uma vaga de emprego?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(PostFeedActivity.this,PostActivity.class);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(PostFeedActivity.this,
                                        "Cancelado", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rview);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();




        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                // StringBuffer stringbuffer = new StringBuffer();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    PostFeedData userdetails = dataSnapshot1.getValue(PostFeedData.class);
                    PostFeedData listdata = new PostFeedData();
                    String jobTitle =userdetails.getJobTitle();
                    String jobLocation=userdetails.getJobLocation();
                    String companyName=userdetails.getCompanyName();
                    String jobDescription=userdetails.getJobDescription();
                    String contact=userdetails.getContact();
                    String emailID = userdetails.getEmailID();


                    listdata.setJobTitle(jobTitle);
                    listdata.setJobLocation(jobLocation);
                    listdata.setCompanyName(companyName);
                    listdata.setJobDescription(jobDescription);
                    listdata.setContact(contact);
                    listdata.setEmailID(emailID);
                    list.add(listdata);
                    // Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();

                }

                Context context = PostFeedActivity.this;
                RecyclerAdapter recycler = new RecyclerAdapter(context,list);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(PostFeedActivity.this);
                recyclerView.setLayoutManager(layoutmanager);
                recyclerView.setItemAnimator( new DefaultItemAnimator());
                recyclerView.setAdapter(recycler);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }
}