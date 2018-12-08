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

        GOCEvents.add(new Events("Alumni Happy Hour","Pizza House","4:00 PM","","Bringing all Tauber alumni together to celebrate and kickoff the conference! Come for free pizza, drinks and the chance to network with our speakers."));
        GOCEvents.add(new Events("Welcome Reception","Ross Colloquium","6:00 PM","","Registration will begin at 6:00 PM. Take this time to get your nametag, register and get free swag on us!"));
        GOCEvents.add(new Events("Opening Remarks","Ross Colloquium","7:00 PM","Dr. Alec D. Gallimore","We're so excited to host Dr. Alec D. Gallimore, the Robert J. Vlasic Dean of Engineering, as our first speaker and to inspire all the attendees."));
        GOCEvents.add(new Events("Dinner","Ross Colloquium","7:15 PM ","","Delicious dinner provided by Ross Catering, including dessert!"));
        GOCEvents.add(new Events("Case Competition Winner","Ross Colloquium","7:30 PM ","","Announcing the winners of the PwC and Strategy& Case Competition Finals."));
        GOCEvents.add(new Events("Keynote Address","Ross Colloquium","7:45 PM ","Toby Brzoznowski","Tony Brzoznowski, Co-Founder and Chief Strategy Officer of Llamasoft, will lead our first keynote address about the Digital Imperative and the New Age Operating Model."));

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

        GOCEventsSecond.add(new Events("Networking Breakfast","Ross Colloquium","8:00 AM","","Come early for a beautiful breakfast buffet and the chance to network with other early risers!"));
        GOCEventsSecond.add(new Events("Introductory Remarks","Ross Colloquium","8:45 AM","","Additional introductory remarks to set the tone for another inspiring day!"));
        GOCEventsSecond.add(new Events("Keynote Address","Ross Colloquium","9:00 AM","Mary Ellen Smith","Mary Ellen Smith, Corporate Vice President of Microsoft, will present the second keynote address on Digital Transformation Journey."));
        GOCEventsSecond.add(new Events("Panel First Round","R1210","9:55 AM","","Digitizing Operations: From Manufacturing to Services with panel members from General Electric, Boeing, Microsoft and more!"));
        GOCEventsSecond.add(new Events("Panel Second Round","R1210","11:05 AM","","Sustainability in the Digital Age with panelists from Dell, Amazon and Deloitte."));
        GOCEventsSecond.add(new Events("Lunch & Keynote","Ross Colloquium","12:15 PM","Russell Hensley","Keynote will be presented by Russell Hensley, Partner from McKinsey Detroit about Where Operations are Headed over the Next 25 Years."));
        GOCEventsSecond.add(new Events("Closing Remarks","Ross Colloquium","1:15 PM","","Thanking all the attendees and speakers who made this such a great event!"));
        GOCEventsSecond.add(new Events("Company Coffee Chats","Various Rooms","1:30 PM","","For attendees and company representatives who want to continue networking after the event!"));

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
