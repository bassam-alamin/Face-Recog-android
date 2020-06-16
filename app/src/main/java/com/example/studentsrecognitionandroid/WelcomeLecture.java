package com.example.studentsrecognitionandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class WelcomeLecture extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
//    private TextView welcome;
    private SharedPreferences sharedPreferences;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_lecture);


        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);


        Intent intent = getIntent();

        int value = sharedPreferences.getInt("id",0);

        String em = sharedPreferences.getString("email",null);



//        welcome = findViewById(R.id.welcome);
//
//        welcome.setText(value+"Welcome home");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.navigationview);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId() == R.id.home){

        }


        if(menuItem.getItemId() == R.id.Supervise){

        }

        if(menuItem.getItemId() == R.id.reports){

        }


        return true;
    }
}
