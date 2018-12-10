package com.example.to426.dayofgoc;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class ConferenceIntro extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottom_nav;
    private FrameLayout mainframe;

    private HomeFragment homeFragment;
    private MapFragment mapFragment;
    private ScheduleFragment scheduleFragment;
    private ProfileFragment profileFragment;
    private SurveyFragment surveyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_intro);

        bottom_nav = findViewById(R.id.bottom_nav);
        mainframe = findViewById(R.id.mainframe);

        homeFragment = new HomeFragment();
        mapFragment = new MapFragment();
        scheduleFragment = new ScheduleFragment();
        profileFragment = new ProfileFragment();
        surveyFragment = new SurveyFragment();

        setFragment(homeFragment);
        bottom_nav.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.menuconference:
                setFragment(homeFragment);
                return true;
            case R.id.menumap:
                setFragment(mapFragment);
                return true;
            case R.id.menuschedule:
                setFragment(scheduleFragment);
                return true;
            case R.id.menuprofile:
                setFragment(profileFragment);
                return true;
            case R.id.menulogout:
                setFragment(surveyFragment);
                return true;
        }
        return false;
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe, fragment);
        fragmentTransaction.commit();
    }
}
