package com.example.to426.dayofgoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchAttendee extends Activity implements View.OnClickListener {

    private Button finish,back;
    private EditText editName;
    private Spinner editIndustry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_attendee);


        finish = findViewById(R.id.finish);
        back = findViewById(R.id.back);
        editName = findViewById(R.id.editName);
        editIndustry = findViewById(R.id.editIndustry);
        finish.setOnClickListener(this);
        back.setOnClickListener(this);
        String[] industryChoices = new String[]{"Please Choose One","Aerospace","Agriculture",
                "Chemical","Computer","Construction","Consulting","Education","Energy",
                "Entertainment","Finance","Health Care","Information",
                "Manufacturing","Mass Media","Telecommunications","Transport","Other"};

        ArrayAdapter<String> industryAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,industryChoices);
        editIndustry.setAdapter(industryAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v == back){
            Intent go = new Intent(SearchAttendee.this,ConferenceIntro.class);
            startActivity(go);
        }else if(v == finish){
            if(!editName.getText().toString().equals("")&&!editIndustry.getSelectedItem().toString().equals("Please Choose One")){
                Intent go1 = new Intent(this,SearchResult.class);
                go1.putExtra("search_situation",1);
                go1.putExtra("search_name",editName.getText().toString());
                go1.putExtra("search_industry",editIndustry.getSelectedItem().toString());
                startActivity(go1);
            }else if(!editName.getText().toString().equals("")){
                Intent go2 = new Intent(this,SearchResult.class);
                go2.putExtra("search_situation",2);
                go2.putExtra("search_name",editName.getText().toString());
                startActivity(go2);
            }else if(!editIndustry.getSelectedItem().toString().equals("Please Choose One")){
                Intent go3 = new Intent(this,SearchResult.class);
                go3.putExtra("search_situation",3);
                go3.putExtra("search_industry",editIndustry.getSelectedItem().toString());
                startActivity(go3);
            }else{
                Toast.makeText(this,"Please enter either one of them",Toast.LENGTH_LONG).show();
            }





        }

    }
}
