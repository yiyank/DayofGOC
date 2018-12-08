package com.example.to426.dayofgoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileList extends Activity {

    private ArrayList<Attendees> GOCAttendees;
    private Button updateProfile;
    private FirebaseDatabase database;
    private ValueEventListener databaseListener;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);

        GOCAttendees = new ArrayList<>();

        //initRecyclerViewFunction
        RecyclerView recyclerView = findViewById(R.id.rvattendee);
        final RecyclerViewAttendee rv = new RecyclerViewAttendee(GOCAttendees, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rv);

        //initAttendeesFunction
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        databaseListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {

                    //String ue = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    String post1 = uniqueKeySnapshot.child("Name").getValue(String.class);
                    String post2 = uniqueKeySnapshot.child("Organization").getValue(String.class);
                    String post3 = uniqueKeySnapshot.child("Industry").getValue(String.class);
                    String post4 = uniqueKeySnapshot.child("Email").getValue(String.class);
                    String post5 = uniqueKeySnapshot.child("Linkedin").getValue(String.class);
                    GOCAttendees.add(new Attendees(post1,post2,post3,post4,post5));
                }
                rv.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        updateProfile = findViewById(R.id.updateProfile);
        updateProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent update = new Intent(ProfileList.this,UpdateProfile.class);
                startActivity(update);
            }
        });

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

    private void initRecyclerView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater optionMenuInflater = getMenuInflater();
        optionMenuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menuconference:
                Intent go1 = new Intent(this, ConferenceIntro.class);
                this.startActivity(go1);
                return true;
            case R.id.menumap:
                Intent go2 = new Intent(this, Map.class);
                this.startActivity(go2);
                return true;
            case R.id.menuprofilelist:
                Intent go3 = new Intent(this, ProfileList.class);
                this.startActivity(go3);
                return true;
            case R.id.menuschedule:
                Intent go4 = new Intent(this, Schedule.class);
                this.startActivity(go4);
                return true;
            case R.id.menusurvey:
                Intent go5 = new Intent(this, SurveyPage.class);
                this.startActivity(go5);
                return true;
            case R.id.menulogout:
                Intent go6 = new Intent(this,LoginPage.class);
                this.startActivity(go6);

            default:
                return false;
        }
    }
}
