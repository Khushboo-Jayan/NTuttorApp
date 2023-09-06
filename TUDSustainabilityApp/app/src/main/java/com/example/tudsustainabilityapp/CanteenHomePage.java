package com.example.tudsustainabilityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class CanteenHomePage extends AppCompatActivity {

    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private FirebaseAuth auth;
    private FirebaseFirestore fstore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen_home_page);

        drawer = findViewById(R.id.drawlayout);
        navigationView = findViewById(R.id.navBar);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        actionBarDrawerToggle = new ActionBarDrawerToggle(CanteenHomePage.this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Toast.makeText(CanteenHomePage.this, "Login successful", Toast.LENGTH_SHORT).show();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.creditScoreBarcode){
                    Intent generateCode = new Intent(CanteenHomePage.this, CreditScore.class);
                    startActivity(generateCode);                }
                //logout and return to login page removing data in textview
                else if(id == R.id.logout){
                    auth.signOut();
                    Intent logout = new Intent(CanteenHomePage.this, MainActivity.class);
                    startActivity(logout);
                    finish();
                    Toast.makeText(CanteenHomePage.this, "LOGGED OUT SUCCESSFULLY!!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}