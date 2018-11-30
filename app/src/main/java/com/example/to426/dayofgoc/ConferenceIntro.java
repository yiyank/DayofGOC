package com.example.to426.dayofgoc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class ConferenceIntro extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private ScheduleFragment scheduleFragment;
    private MapFragment mapFragment;
    private SurveyFragment surveyFragment;
    private ProfileFragment profileFragment;
    //private SurveyFragment surveyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_intro);

        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);

        homeFragment = new HomeFragment();
        scheduleFragment = new ScheduleFragment();
        mapFragment = new MapFragment();

        mMainNav.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iconHome:
                setFragment(homeFragment);
                return true;
            case R.id.iconSchedule:
                setFragment(scheduleFragment);
                return true;
            case R.id.iconMap:
                setFragment(mapFragment);
                return true;
            case R.id.iconSurvey:
                setFragment(surveyFragment);
                return true;
            case R.id.iconProfile:
                setFragment(profileFragment);
                return true;
            default:
                return false;
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
