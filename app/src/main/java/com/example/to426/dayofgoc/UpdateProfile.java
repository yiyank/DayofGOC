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

public class UpdateProfile extends Activity implements View.OnClickListener {


    private Button finish;
    private EditText editName, editLink, editOrganization;
    private Spinner editIndustry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

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
        DatabaseReference myRef = database.getReference();

        myRef.orderByChild("Email").equalTo(FirebaseAuth.getInstance().getCurrentUser().getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ue = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                for (DataSnapshot datas1:dataSnapshot.getChildren()){
                    if(datas1.child("Email").getValue(String.class).equals(ue) ){
                        if(!editName.getText().toString().equals("")){
                            datas1.child("Name").getRef().setValue(editName.getText().toString());
                        }
                        if(!editOrganization.getText().toString().equals("")){
                            datas1.child("Organization").getRef().setValue(editOrganization.getText().toString());
                        }
                        if(!editIndustry.getSelectedItem().toString().equals("Please Choose One")){
                            datas1.child("Industry").getRef().setValue(editIndustry.getSelectedItem().toString());
                        }
                        if(!editLink.getText().toString().equals("")){
                            datas1.child("Linkedin").getRef().setValue(editLink.getText().toString());
                        }
                        Toast.makeText(UpdateProfile.this,"Information Updated!",Toast.LENGTH_SHORT).show();
                        Intent go = new Intent(UpdateProfile.this, ProfileList.class);
                        startActivity(go);
                        }else{ }
                    }

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
