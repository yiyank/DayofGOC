package com.example.to426.dayofgoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends Activity implements View.OnClickListener {


    private Button finish;
    private EditText editName, editLink, editOrganization;
    private Spinner editIndustry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        finish = findViewById(R.id.finish);
        editName = findViewById(R.id.editName);
        editLink = findViewById(R.id.editLink);
        editOrganization = findViewById(R.id.editOrganization);
        editIndustry = findViewById(R.id.editIndustry);

        String[] industryChoices = new String[]{"Please Choose One","Aerospace","Agriculture",
                "Chemical","Computer","Construction","Education","Energy",
                "Entertainment","Finance","Health Care","Information",
                "Manufacturing","Mass Media","Telecommunications","Transport","Other"};

        ArrayAdapter<String> industryAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,industryChoices);
        editIndustry.setAdapter(industryAdapter);
        finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(editName.getText().toString());

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.child("Name").getRef().setValue(editName.getText().toString());
                dataSnapshot.child("Organization").getRef().setValue(editOrganization.getText().toString());
                dataSnapshot.child("Industry").getRef().setValue(editIndustry.getSelectedItem().toString());
                dataSnapshot.child("Linkedin").getRef().setValue(editLink.getText().toString());
                dataSnapshot.child("Email").getRef().setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                Toast.makeText(Register.this,"Profile Created!",Toast.LENGTH_SHORT).show();
                Intent go = new Intent(Register.this, ConferenceIntro.class);
                startActivity(go);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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

            default:
                return false;
        }
    }





}
