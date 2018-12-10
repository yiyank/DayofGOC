package com.example.to426.dayofgoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchResult extends Activity implements View.OnClickListener{

    private Button back;
    private ArrayList<Attendees> GOCAttendees;
    private FirebaseDatabase database;
    private ValueEventListener databaseListener;
    private DatabaseReference myRef;
    String name_search, industry_search;
    Integer search_type; //1 for both name and industry,2 for name, 3 for industry

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        back = findViewById(R.id.finish);
        back.setOnClickListener(this);
        GOCAttendees = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.rvattendee);
        final RecyclerViewAttendee rv = new RecyclerViewAttendee(GOCAttendees, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rv);
        //initAttendeesFunction
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        search_type = getIntent().getIntExtra("search_situation",0);

        if(search_type == 1){
            name_search = getIntent().getStringExtra("search_name");
            industry_search = getIntent().getStringExtra("search_industry");
            databaseListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    GOCAttendees.clear();
                    for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {
                        if(uniqueKeySnapshot.child("Name").getValue(String.class).equals(name_search)&&
                        uniqueKeySnapshot.child("Industry").getValue(String.class).equals(industry_search)){
                            String post1 = uniqueKeySnapshot.child("Name").getValue(String.class);
                            String post2 = uniqueKeySnapshot.child("Organization").getValue(String.class);
                            String post3 = uniqueKeySnapshot.child("Industry").getValue(String.class);
                            String post4 = uniqueKeySnapshot.child("Email").getValue(String.class);
                            String post5 = uniqueKeySnapshot.child("Linkedin").getValue(String.class);
                            GOCAttendees.add(new Attendees(post1,post2,post3,post4,post5));
                        }
                    }
                    rv.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

        } else if(search_type == 2){
            name_search = getIntent().getStringExtra("search_name");
            industry_search = "";
            databaseListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    GOCAttendees.clear();
                    for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {
                        if(uniqueKeySnapshot.child("Name").getValue(String.class).equals(name_search)){
                            String post1 = uniqueKeySnapshot.child("Name").getValue(String.class);
                            String post2 = uniqueKeySnapshot.child("Organization").getValue(String.class);
                            String post3 = uniqueKeySnapshot.child("Industry").getValue(String.class);
                            String post4 = uniqueKeySnapshot.child("Email").getValue(String.class);
                            String post5 = uniqueKeySnapshot.child("Linkedin").getValue(String.class);

                            GOCAttendees.add(new Attendees(post1,post2,post3,post4,post5));
                        }
                    }
                    rv.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };


        } else if(search_type == 3){
            name_search = "";
            industry_search = getIntent().getStringExtra("search_industry");
            databaseListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    GOCAttendees.clear();
                    for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {
                        if(uniqueKeySnapshot.child("Industry").getValue(String.class).equals(industry_search)){
                            String post1 = uniqueKeySnapshot.child("Name").getValue(String.class);
                            String post2 = uniqueKeySnapshot.child("Organization").getValue(String.class);
                            String post3 = uniqueKeySnapshot.child("Industry").getValue(String.class);
                            String post4 = uniqueKeySnapshot.child("Email").getValue(String.class);
                            String post5 = uniqueKeySnapshot.child("Linkedin").getValue(String.class);
                            GOCAttendees.add(new Attendees(post1,post2,post3,post4,post5));
                        }
                    }
                    rv.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

        } else{
            Toast.makeText(this,"Please try again!",Toast.LENGTH_LONG).show();
        }



    }

    public void onStart(){
        super.onStart();
        myRef.addValueEventListener(databaseListener);
    }

    public void onStop(){
        super.onStop();
        if(databaseListener != null){
            myRef.removeEventListener(databaseListener);
        }
    }

    @Override
    public void onClick(View v) {
        Intent go = new Intent(this,ConferenceIntro.class);
        startActivity(go);
    }
}
