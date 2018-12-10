package com.example.to426.dayofgoc;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private ArrayList<Attendees> GOCAttendees;
    private Button updateProfile;
    private FirebaseDatabase database;
    private ValueEventListener databaseListener;
    private DatabaseReference myRef;

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


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View profileview = inflater.inflate(R.layout.fragment_profile, container, false);

        GOCAttendees = new ArrayList<>();

        //initRecyclerViewFunction
        RecyclerView recyclerView = profileview.findViewById(R.id.rvattendee);
        final RecyclerViewAttendee rv = new RecyclerViewAttendee(GOCAttendees, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(rv);

        //initAttendeesFunction
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        databaseListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GOCAttendees.clear();
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

        updateProfile = profileview.findViewById(R.id.updateProfile);
        updateProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent update = new Intent(getActivity(),UpdateProfile.class);
                startActivity(update);
            }
        });

        // Inflate the layout for this fragment
        return profileview;
    }

}
