package com.example.to426.dayofgoc;

public class Events {
    String EventName;
    String Location;
    String Time;
    Attendees Speaker;
    String Description;

    public Events(){

    }

    public Events(String eventName, String location, String time, Attendees speaker, String description){
        this.EventName = eventName;
        this.Location = location;
        this.Time = time;
        this.Speaker = speaker;
        this.Description = description;
    }
}


