package com.example.to426.dayofgoc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    //This is the list of events for day one
    private ArrayList<Events> GOCEvents;
    //This is the list of events for day two
    private ArrayList<Events> GOCEventsSecond;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View scheduleview = inflater.inflate(R.layout.fragment_schedule, container, false);

        initEvents(scheduleview);
        initEventsSecond(scheduleview);

        // Inflate the layout for this fragment
        return scheduleview;
    }

    //For Day One
    private void initEvents(View scheduleview) {
        GOCEvents = new ArrayList<>();

        GOCEvents.add(new Events("Alumni Happy Hour","Pizza House","4:00 PM","","Bringing all Tauber alumni together to celebrate and kickoff the conference! Come for free pizza, drinks and the chance to network with our speakers."));
        GOCEvents.add(new Events("Welcome Reception","Ross Colloquium","6:00 PM","","Registration will begin at 6:00 PM. Take this time to get your nametag, register and get free swag on us!"));
        GOCEvents.add(new Events("Opening Remarks","Ross Colloquium","7:00 PM","Dr. Alec D. Gallimore","We're so excited to host Dr. Alec D. Gallimore, the Robert J. Vlasic Dean of Engineering, as our first speaker and to inspire all the attendees."));
        GOCEvents.add(new Events("Dinner","Ross Colloquium","7:15 PM ","","Delicious dinner provided by Ross Catering, including dessert!"));
        GOCEvents.add(new Events("Case Competition Winner","Ross Colloquium","7:30 PM ","","Announcing the winners of the PwC and Strategy& Case Competition Finals."));
        GOCEvents.add(new Events("Keynote Address","Ross Colloquium","7:45 PM ","Toby Brzoznowski","Tony Brzoznowski, Co-Founder and Chief Strategy Officer of Llamasoft, will lead our first keynote address about the Digital Imperative and the New Age Operating Model."));

        initRecyclerView(scheduleview);
    }

    private void initRecyclerView(View scheduleview) {
        RecyclerView recyclerView = scheduleview.findViewById(R.id.rvschedule);
        RecyclerViewSchedule rv = new RecyclerViewSchedule(GOCEvents,getActivity());
        recyclerView.setAdapter(rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // For Day Two
    private void initRecyclerViewSecond(View scheduleview) {
        RecyclerView recyclerView = scheduleview.findViewById(R.id.rvschedule2);
        RecyclerViewSchedule rv2 = new RecyclerViewSchedule(GOCEventsSecond, getActivity());
        recyclerView.setAdapter(rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initEventsSecond(View scheduleview) {
        GOCEventsSecond = new ArrayList<>();

        GOCEventsSecond.add(new Events("Networking Breakfast","Ross Colloquium","8:00 AM","","Come early for a beautiful breakfast buffet and the chance to network with other early risers!"));
        GOCEventsSecond.add(new Events("Introductory Remarks","Ross Colloquium","8:45 AM","","Additional introductory remarks to set the tone for another inspiring day!"));
        GOCEventsSecond.add(new Events("Keynote Address","Ross Colloquium","9:00 AM","Mary Ellen Smith","Mary Ellen Smith, Corporate Vice President of Microsoft, will present the second keynote address on Digital Transformation Journey."));
        GOCEventsSecond.add(new Events("Panel First Round","R1210","9:55 AM","","Digitizing Operations: From Manufacturing to Services with panel members from General Electric, Boeing, Microsoft and more!"));
        GOCEventsSecond.add(new Events("Panel Second Round","R1210","11:05 AM","","Sustainability in the Digital Age with panelists from Dell, Amazon and Deloitte."));
        GOCEventsSecond.add(new Events("Lunch & Keynote","Ross Colloquium","12:15 PM","Russell Hensley","Keynote will be presented by Russell Hensley, Partner from McKinsey Detroit about Where Operations are Headed over the Next 25 Years."));
        GOCEventsSecond.add(new Events("Closing Remarks","Ross Colloquium","1:15 PM","","Thanking all the attendees and speakers who made this such a great event!"));
        GOCEventsSecond.add(new Events("Company Coffee Chats","Various Rooms","1:30 PM","","For attendees and company representatives who want to continue networking after the event!"));

        initRecyclerViewSecond(scheduleview);
    }

}
