package com.example.studentsrecognitionandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_lecture);



        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);


        Intent intent = getIntent();

        int value = sharedPreferences.getInt("id",0);

        String em = sharedPreferences.getString("email",null);



//        welcome = findViewById(R.id.textView2);
//
//        welcome.setText("Welcome home");


        //start of setting up the navigation drawer

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.navigationview);

        navigationView.setNavigationItemSelectedListener(this);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        //end of setting the navigation drawer


        //the following is the beginning of setting the default fragment to be loaded when this activity is loaded

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container,new MainFragment());
        fragmentTransaction.commit();




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        drawerLayout.closeDrawer(GravityCompat.START);

        if(menuItem.getItemId() == R.id.home){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction =fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container,new MainFragment());
            fragmentTransaction.commit();

        }

        if(menuItem.getItemId() == R.id.Supervise){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction =fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container,new ChooseUnit());
            fragmentTransaction.commit();

        }

        if(menuItem.getItemId() == R.id.reports){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction =fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container,new Reports());
            fragmentTransaction.commit();

        }


        return true;
    }
}
