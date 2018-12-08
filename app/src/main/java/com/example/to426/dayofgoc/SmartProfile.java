package com.example.to426.dayofgoc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;


public class SmartProfile extends Activity {
    String Emailforattendee,Nameforattendee,Organizationforattendee,Industryforattendee;
    TextView NamePlaceholder,IndustryPlaceholder,OrganizationPlaceholder,EmailPlaceholder;
    ImageView ImagePlaceholder;
    private StorageReference mStorageRef;
    private Bitmap my_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_profile);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        Emailforattendee = getIntent().getStringExtra("email_value");
        Industryforattendee = getIntent().getStringExtra("industry_value");
        Nameforattendee = getIntent().getStringExtra("name_value");
        Organizationforattendee = getIntent().getStringExtra("organization_value");
        EmailPlaceholder = findViewById(R.id.EmailPlaceholder);
        NamePlaceholder = findViewById(R.id.NamePlaceholder);
        IndustryPlaceholder = findViewById(R.id.IndustryPlaceholder);
        OrganizationPlaceholder = findViewById(R.id.OrganizationPlaceholder);
        ImagePlaceholder = findViewById(R.id.ImagePlaceholder);

        EmailPlaceholder.setText(Emailforattendee);
        NamePlaceholder.setText(Nameforattendee);
        IndustryPlaceholder.setText(Industryforattendee);
        OrganizationPlaceholder.setText(Organizationforattendee);




        StorageReference ref = mStorageRef.child("images/" + Emailforattendee +".jpg");
        try {
            final File localFile = File.createTempFile("Images", "jpg");
            ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener< FileDownloadTask.TaskSnapshot >() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    my_image = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    ImagePlaceholder.setImageBitmap(my_image);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SmartProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }



        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        m = new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //String ue = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                for (DataSnapshot datas1:dataSnapshot.getChildren()){

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };





*/
    }
/*
    public void onStart(){
        super.onStart();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.orderByChild("Email").equalTo(Emailforattendee).addListenerForSingleValueEvent(m);
    }
    public void onStop(){
        super.onStop();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        if(myRef != null) {
            myRef.removeEventListener(m);
        }
    }*/



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
