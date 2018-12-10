package com.example.to426.dayofgoc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
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
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UpdateProfile extends Activity implements View.OnClickListener {


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
        setContentView(R.layout.activity_update_profile);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        select = findViewById(R.id.select);
        finish = findViewById(R.id.button);
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
            String ue = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            DatabaseReference myRef = database.getReference();

            myRef.orderByChild("Email").equalTo(ue).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot datas1:dataSnapshot.getChildren()){
                            if(!editName.getText().toString().equals("")){
                                datas1.child("Name").getRef().setValue(editName.getText().toString());
                            }
                            if(!editOrganization.getText().toString().equals("")){
                                datas1.child("Organization").getRef().setValue(editOrganization.getText().toString());
                            }
                            if(!editIndustry.getSelectedItem().toString().equals("Please Choose One")){
                                datas1.child("Industry").getRef().setValue(editIndustry.getSelectedItem().toString());
                            }
                            if(!editLink.getText().toString().equals("http://")){
                                datas1.child("Linkedin").getRef().setValue(editLink.getText().toString());
                            }
                        }
                    Toast.makeText(UpdateProfile.this,"Information Updated!",Toast.LENGTH_SHORT).show();
                    Intent go = new Intent(UpdateProfile.this, ConferenceIntro.class);
                    startActivity(go);

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            uploadImage(ue);
        }  else if(v==select){
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
//
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
                            Toast.makeText(UpdateProfile.this,"Uploaded",Toast.LENGTH_LONG).show();
                        }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });}
    }
}
