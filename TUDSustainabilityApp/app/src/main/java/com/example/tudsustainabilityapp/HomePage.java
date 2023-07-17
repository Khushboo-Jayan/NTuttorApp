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

public class HomePage extends AppCompatActivity{
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawer = findViewById(R.id.drawlayout);
        navigationView = findViewById(R.id.navBar);

        auth = FirebaseAuth.getInstance();

        actionBarDrawerToggle = new ActionBarDrawerToggle(HomePage.this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                //intent to page offer to make a ride offer
                if(id == R.id.creditScoreBarcode){
                    Intent generateCode = new Intent(HomePage.this, BarCodeGenerator.class);
                    startActivity(generateCode);
                }

                //intent to page find to search for a ride using to source and destination
                else if(id == R.id.find){
                    Toast.makeText(HomePage.this, "Moving to page to find a ride", Toast.LENGTH_SHORT).show();
                }

                else if(id == R.id.updateRide){
                    Toast.makeText(HomePage.this, "Pending requests to be accepted", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.delete){
                    Toast.makeText(HomePage.this, "Requested rides by the user", Toast.LENGTH_SHORT).show();
                }
                //logout and return to login page removing data in textview
                else if(id == R.id.logout){
                    auth.signOut();
                    Intent logout = new Intent(HomePage.this, MainActivity.class);
                    startActivity(logout);
                    finish();
                    Toast.makeText(HomePage.this, "LOGGED OUT SUCCESSFULLY!!", Toast.LENGTH_SHORT).show();
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