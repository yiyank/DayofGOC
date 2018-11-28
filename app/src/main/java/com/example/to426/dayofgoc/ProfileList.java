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

public class ProfileList extends Activity {

    private ArrayList<Attendees> GOCAttendees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);
        initEvents();
    }


    private void initEvents() {
        GOCAttendees = new ArrayList<>();

        GOCAttendees.add(new Attendees("Name 1","Organization 1","Industry 1","Email 1","Linkedin 1"));
        GOCAttendees.add(new Attendees("Name 2","Organization 2","Industry 2","Email 2","Linkedin 1"));
        GOCAttendees.add(new Attendees("Name 3","Organization 3","Industry 3","Email 3","Linkedin 1"));
        GOCAttendees.add(new Attendees("Name 4","Organization 4","Industry 4","Email 4","Linkedin 1"));

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rvattendee);
        RecyclerViewAttendee rv = new RecyclerViewAttendee(GOCAttendees, this);
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

            default:
                return false;
        }
    }
}
