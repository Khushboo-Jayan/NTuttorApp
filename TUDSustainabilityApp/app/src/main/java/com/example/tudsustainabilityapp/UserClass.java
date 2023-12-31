package com.example.tudsustainabilityapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserClass extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseFirestore fstore;
    private Context context;

    public UserClass(Context context){
        this.context = context;
    }

    public  void getUserDetails(FirebaseAuth auth, FirebaseFirestore fstore){
        this.auth = auth;
        this.fstore = fstore;
        final Map<String, String> userDetailList = new HashMap<>();

        fstore.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        if(d.getId().equals(auth.getUid())){
                            userDetailList.put("FullName", (String) d.get("FullName"));
                            userDetailList.put("PhoneNumber", (String) d.get("PhoneNumber"));
                            userDetailList.put("EmailID", (String) d.get("UserEmail"));
                            userDetailList.put("Role", (String) d.get("UserRole"));
                        }else {
                            Toast.makeText(getParent(), "Fullname not found", Toast.LENGTH_LONG).show();
                        }//end else
                    }
                }
            }
        });
    }
}
