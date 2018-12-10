package com.example.to426.dayofgoc;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Register extends Activity implements View.OnClickListener {

    private int PICK_IMAGE_REQUEST = 1;
    private Button finish,select;
    private EditText editName, editLink, editOrganization;
    private Spinner editIndustry;
    private ImageView imageView;
    private StorageReference mStorageRef;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        select = findViewById(R.id.select);

        finish = findViewById(R.id.finish);
        editName = findViewById(R.id.editName);
        editLink = findViewById(R.id.editLink);
        editOrganization = findViewById(R.id.editOrganization);
        editIndustry = findViewById(R.id.editIndustry);

        String[] industryChoices = new String[]{"Please Choose One","Aerospace","Agriculture",
                "Chemical","Computer","Construction","Consulting","Education","Energy",
                "Entertainment","Finance","Health Care","Information",
                "Manufacturing","Mass Media","Telecommunications","Transport","Other"};

        ArrayAdapter<String> industryAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,industryChoices);
        editIndustry.setAdapter(industryAdapter);
        finish.setOnClickListener(this);
        select.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v == finish){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            String userid = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            String[] separateduserid = userid.split("\\.");

            DatabaseReference myRef = database.getReference(separateduserid[0]+" "+separateduserid[1]);
            /*String name = editName.getText().toString();
            String org = editOrganization.getText().toString();
            String ind = editIndustry.getSelectedItem().toString();
            String link = editLink.getText().toString();




            Attendees newattendee = new Attendees(name,org,ind,link,userid);

            myRef.child(userid).push().setValue(newattendee);
            Toast.makeText(Register.this,"Profile Created!",Toast.LENGTH_SHORT).show();
            Intent go = new Intent(Register.this, ConferenceIntro.class);
            startActivity(go);*/


            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (editName.getText().toString().equals("")){
                        dataSnapshot.child("Name").getRef().setValue("Attendee");
                    }else{
                        dataSnapshot.child("Name").getRef().setValue(editName.getText().toString());
                    }
                    if (editOrganization.getText().toString().equals("")){
                        dataSnapshot.child("Organization").getRef().setValue("Organization");
                    }else{
                        dataSnapshot.child("Organization").getRef().setValue(editOrganization.getText().toString());
                    }
                    if (editIndustry.getSelectedItem().toString().equals("")){
                        dataSnapshot.child("Industry").getRef().setValue("Please Choose One");
                    }else{
                        dataSnapshot.child("Industry").getRef().setValue(editIndustry.getSelectedItem().toString());
                    }
                    if (editLink.getText().toString().equals("http://")){
                        dataSnapshot.child("Linkedin").getRef().setValue("Not Available");
                    }else{
                        dataSnapshot.child("Linkedin").getRef().setValue(editLink.getText().toString());
                    }
                    dataSnapshot.child("Email").getRef().setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    Toast.makeText(Register.this,"Profile Created!",Toast.LENGTH_SHORT).show();
                    Intent go = new Intent(Register.this, ConferenceIntro.class);
                    startActivity(go);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            uploadImage(userid);

        } else if(v==select){
            selectImages();
        }
    }

    private void selectImages(){
        Intent select = new Intent();
        select.setType("image/*");
        select.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(select,"Select Picture"),PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData()!= null){
            Uri uri = data.getData();
            selectedImageUri = uri;
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageView = findViewById(R.id.imageView5);
                imageView.setImageBitmap(bitmap);
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public void uploadImage(String user){

        StorageReference sRef = mStorageRef.child("images/" + user +".jpg");
        if(selectedImageUri!=null){
        sRef.putFile(selectedImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Toast.makeText(Register.this,"Uploaded",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
        }else{
            Uri uri = Uri.parse("android.resource://com.example.to426.dayofgoc/drawable/sample");

            sRef.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Get a URL to the uploaded content
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                        }
                    });
        }
    }


}
