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

    //This is the list of events for day one
    private ArrayList<Events> GOCEvents;
    //This is the list of events for day two
    private ArrayList<Events> GOCEventsSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        initEvents();
        initEventsSecond();
    }

    //For Day One
    private void initEvents() {
        GOCEvents = new ArrayList<>();

        GOCEvents.add(new Events("Alumni Happy Hour","Pizza House","4:00 PM","","Bringing all Tauber alumni together to celebrate and kickoff the conference! Come for free pizza, drinks and the chance to network with our speakers. Dress code: Business Casual"));
        GOCEvents.add(new Events("Welcome Reception","Ross Colloquium","6:00 PM","","Description 2"));
        GOCEvents.add(new Events("Opening Remarks","Ross Colloquium","7:00 PM","Joel Tauber, Dr. Alec D. Gallimore","Description 3"));
        GOCEvents.add(new Events("Dinner","Ross Colloquium","7:15 PM ","","Description 4"));
        GOCEvents.add(new Events("Case Competition Winner","Ross Colloquium","7:30 PM ","","Description 4"));
        GOCEvents.add(new Events("Keynote Address","Ross Colloquium","7:45 PM ","Toby Brzoznowski","Description 4"));

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rvschedule);
        RecyclerViewSchedule rv = new RecyclerViewSchedule(GOCEvents, this);
        recyclerView.setAdapter(rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // For Day Two
    private void initRecyclerViewSecond() {
        RecyclerView recyclerView = findViewById(R.id.rvschedule2);
        RecyclerViewSchedule rv2 = new RecyclerViewSchedule(GOCEventsSecond, this);
        recyclerView.setAdapter(rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initEventsSecond() {
        GOCEventsSecond = new ArrayList<>();

        GOCEventsSecond.add(new Events("Networking Breakfast","Ross Colloquium","8:00 AM","","Description 1"));
        GOCEventsSecond.add(new Events("Introductory Remarks","Ross Colloquium","8:45 AM","","Description 1"));
        GOCEventsSecond.add(new Events("Keynote Address","Ross Colloquium","9:00 AM","Mary Ellen Smith","Description 1"));
        GOCEventsSecond.add(new Events("Panels First Round","R1210, R1220","9:55 AM","","Description 1"));
        GOCEventsSecond.add(new Events("Panels Second Round","R1210, R1220","11:05 AM","","Description 1"));
        GOCEventsSecond.add(new Events("Lunch & Keynote","Ross Colloquium","12:15 PM","Russell Hensley","Description 1"));
        GOCEventsSecond.add(new Events("Closing Remarks","Ross Colloquium","1:15 PM","","Description 1"));
        GOCEventsSecond.add(new Events("Company Coffee Chats","Various Rooms","1:30 PM","","Description 1"));

        initRecyclerViewSecond();
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
