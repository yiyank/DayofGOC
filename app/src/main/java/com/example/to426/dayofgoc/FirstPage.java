package com.example.to426.dayofgoc;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class FirstPage extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        button = findViewById(R.id.buttonSurvey);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Intent go = new Intent(FirstPage.this, LoginPage.class);
        startActivity(go);
    }
}
