package com.example.tudsustainabilityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//main activity used as log in page by default
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //dummy user = test@mytudublin.ie and password = testing
    private FirebaseAuth auth;

    EditText username, password;
    Button login, signinRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the values and layout on creating the activity
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        signinRedirect = (Button) findViewById(R.id.signRedirect);


        login.setOnClickListener(this);
        //user does not have an account create one
        signinRedirect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.login:
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(!user.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(user).matches()){
                    if(!pass.isEmpty()){
                        auth.signInWithEmailAndPassword(user,pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(MainActivity.this, HomePage.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                                    }
                                });
                    } else{
                        password.setError("Password cannot be empty");
                    }
                } else if(user.isEmpty()){
                    username.setError("Username cannot be empty");
                } else {
                    username.setError("Username cannot be empty");
                }
                break;
            case R.id.signRedirect:
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }
}