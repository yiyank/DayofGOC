package com.example.to426.dayofgoc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SurveyPage extends Activity implements View.OnClickListener {

    private Button buttonSurvey;
    private TextView textViewSurveyFeedback, textViewParagraph1, textViewParagraph2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_page);

        buttonSurvey = findViewById(R.id.buttonSurvey);
        textViewSurveyFeedback = findViewById(R.id.textViewSurveyFeedback);
        textViewParagraph1 = findViewById(R.id.textViewParagraph1);
        textViewParagraph2 = findViewById(R.id.textViewParagraph2);

        buttonSurvey.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if (v == buttonSurvey) {
            Intent browserSurvey = new Intent (Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/1gSQd4ePpPFuAv4y1nElVJ_TPk7WkzYRFVgXnV09oNLk/edit?ts=5be1a2cc"));
            startActivity(browserSurvey);
        }
    }
}
