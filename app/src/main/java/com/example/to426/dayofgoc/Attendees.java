package com.example.to426.dayofgoc;

public class Attendees {
    String Name;
    String Organization;
    String Industry;
    String Email;
    String Linkedin;


    public Attendees(){

    }

    public Attendees(String name, String organization, String industry, String email, String linkedin){
        this.Name = name;
        this.Organization = organization;
        this.Industry = industry;
        this.Linkedin = linkedin;
        this.Email = email;
    }
}
