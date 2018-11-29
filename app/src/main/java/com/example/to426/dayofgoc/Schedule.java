package com.example.to426.dayofgoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class Schedule extends Activity {

    private ArrayList<Events> GOCEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        initEvents();
    }

    private void initEvents() {
        GOCEvents = new ArrayList<>();

        GOCEvents.add(new Events("Event 1","Location 1","Time 1","Speaker 1","Description 1"));
        GOCEvents.add(new Events("Event 2","Location 2","Time 2","Speaker 2","Description 2"));
        GOCEvents.add(new Events("Event 3","Location 3","Time 3","Speaker 3","Description 3"));
        GOCEvents.add(new Events("Event 4","Location 4","Time 4","Speaker 4","Description 4"));



        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rvschedule);
        RecyclerViewSchedule rv = new RecyclerViewSchedule(GOCEvents, this);
        recyclerView.setAdapter(rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
