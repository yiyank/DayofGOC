package com.example.to426.dayofgoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends Activity {

    private static final String TAG = "loginPage";
    private Button LogInButton, RegisterButton;
    private EditText LogInEmail, LogInPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user !=null){
                    //User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" +user.getUid());
                }else{
                    //User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        LogInButton = findViewById(R.id.LogInButton);
        RegisterButton = findViewById(R.id.RegisterButton);
        LogInEmail = findViewById(R.id.LogInEmail);
        LogInPassword = findViewById(R.id.LogInPassword);

        LogInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(TextUtils.isEmpty(LogInEmail.getText().toString()) | TextUtils.isEmpty(LogInPassword.getText().toString())){
                    Toast.makeText(LoginPage.this, "Email or Password Missing!", Toast.LENGTH_SHORT).show();
                }
                else {
                    signIn(LogInEmail.getText().toString(), LogInPassword.getText().toString());
                }


            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(TextUtils.isEmpty(LogInEmail.getText().toString()) | TextUtils.isEmpty(LogInPassword.getText().toString())){
                    Toast.makeText(LoginPage.this, "Email or Password Missing!", Toast.LENGTH_SHORT).show();
                }
                else {
                    createAccount(LogInEmail.getText().toString(), LogInPassword.getText().toString());
                }
            }
        });

    }

    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void onStop(){
        super.onStop();
        if(mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                if (!task.isSuccessful()) {
                    Toast.makeText(LoginPage.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
                else if(task.isSuccessful()) {
                    Toast.makeText(LoginPage.this,"Register Successful",Toast.LENGTH_SHORT).show();
                    Intent intentRegister = new Intent(LoginPage.this, Register.class);
                    startActivity(intentRegister);}
                }


        });
    }

    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithEmail:onComplete:" +task.isSuccessful());
                if(!task.isSuccessful()){
                    Toast.makeText(LoginPage.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LoginPage.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                    Intent intentCreate = new Intent(LoginPage.this, ConferenceIntro.class);
                    startActivity(intentCreate);}
            }
        });
    }

}

