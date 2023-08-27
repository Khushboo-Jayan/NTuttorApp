package com.example.tudsustainabilityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "Tag";
    private FirebaseAuth auth;
    private FirebaseFirestore fstore;
    EditText username,emailID, password, reenterpasswrod, phonenumber;
    Button signupBtn, loginRedirectButton;
    String userID;

    public boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //set the values and layout on creating the activity
        username = (EditText) findViewById(R.id.username);
        emailID = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        reenterpasswrod = (EditText) findViewById(R.id.passwordreenter);
        phonenumber = (EditText) findViewById(R.id.phonenumber);
        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        signupBtn = (Button) findViewById(R.id.signupButton);
        loginRedirectButton = (Button) findViewById(R.id.loginButton);

        signupBtn.setOnClickListener(this);
        loginRedirectButton.setOnClickListener(this);
    }//end onCreate

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.signupButton:
                String userFullname = username.getText().toString().trim();
                String userPhone = phonenumber.getText().toString().trim();
                String userEmail = emailID.getText().toString().trim();
                String userPass = password.getText().toString().trim();
                String userRepass = reenterpasswrod.getText().toString().trim();

                //no empty field
                inputValidation(username);
                inputValidation(emailID);
                inputValidation(password);
                inputValidation(reenterpasswrod);
                inputValidation(phonenumber);


                if(valid) {
                    auth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = auth.getCurrentUser();
                                Toast.makeText(SignUpActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                userID = auth.getCurrentUser().getUid();
                                DocumentReference documentReference = fstore.collection("Users").document(userID);

                                //store the data
                                Map<String, Object> userInfo = new HashMap<>();
                                userInfo.put("Fullname", userFullname);
                                userInfo.put("UserEmail", userEmail);
                                userInfo.put("Password", userRepass);
                                userInfo.put("PhoneNumber", userPhone);

                                //is student or staff ie user or admin
                                userInfo.put("isUser", "1");

                                //add document failure if the document in db failed
                                documentReference.set(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d(TAG,"onSuccess: user profile is created for "+ userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG,"onFailure: "+ e.toString());
                                    }
                                });

                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, "SignUp Failed" + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.loginButton:
                Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loginIntent);
                break;
        }//end swicth
    }///end onClick

    //input validation
    public boolean inputValidation(EditText field){
        if(field.getText().toString().isEmpty()){
            field.setError("Field cannot be empty");
            valid = false;
        } else{
            valid = true;
        }
        return valid;
    }

}//end class

