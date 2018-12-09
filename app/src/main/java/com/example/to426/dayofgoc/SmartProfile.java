package com.example.to426.dayofgoc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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


public class SmartProfile extends Activity implements View.OnClickListener{
    String Emailforattendee,Nameforattendee,Organizationforattendee,Industryforattendee,Linkforattendee;
    TextView NamePlaceholder,IndustryPlaceholder,OrganizationPlaceholder,EmailPlaceholder;
    ImageView ImagePlaceholder;
    private ImageButton linkedinbutton;
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
        Linkforattendee = getIntent().getStringExtra("link_value");
        EmailPlaceholder = findViewById(R.id.EmailPlaceholder);
        NamePlaceholder = findViewById(R.id.NamePlaceholder);
        IndustryPlaceholder = findViewById(R.id.IndustryPlaceholder);
        OrganizationPlaceholder = findViewById(R.id.OrganizationPlaceholder);
        ImagePlaceholder = findViewById(R.id.ImagePlaceholder);
        linkedinbutton = findViewById(R.id.linkedinbutton);

        EmailPlaceholder.setText(Emailforattendee);
        NamePlaceholder.setText(Nameforattendee);
        IndustryPlaceholder.setText(Industryforattendee);
        OrganizationPlaceholder.setText(Organizationforattendee);
        linkedinbutton.setOnClickListener(this);


        StorageReference ref = mStorageRef.child("images/" + Emailforattendee + ".jpg");
        try {
            final File localFile = File.createTempFile("Images", "jpg");
            ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
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


    }

    @Override
    public void onClick(View v) {
        Intent linkedin = new Intent (Intent.ACTION_VIEW, Uri.parse("https://"+Linkforattendee));
        startActivity(linkedin);
    }

}
