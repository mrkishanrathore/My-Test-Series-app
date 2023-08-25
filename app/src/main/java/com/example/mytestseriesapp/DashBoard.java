package com.example.mytestseriesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class DashBoard extends AppCompatActivity {


    TextView username;

    View headerView;


    //    Toolbar setup
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        loadFragment(new HomeFragment());

//        Getting view
        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);


        headerView = navigationView.getHeaderView(0); // Get the header view

        username = headerView.findViewById(R.id.username);

// Update the text of the TextView
        // Replace with the new username
        username.setText(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName());

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if(id == R.id.home){
                Log.d("home", "onNavigationItemSelected: cliked");
                loadFragment(new HomeFragment());
            } else if (id == R.id.settings) {
                Toast.makeText(DashBoard.this,"setting",Toast.LENGTH_SHORT).show();
            } else if (id == R.id.contact) {
                loadFragment(new AFragment());
            }else if (id == R.id.about) {
                Toast.makeText(DashBoard.this, "about is open", Toast.LENGTH_SHORT).show();
            }else if (id == R.id.logout) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(DashBoard.this, Login.class));
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if(id == R.id.bottomHome){
                loadFragment(new HomeFragment());
            } else if (id == R.id.bottomSettings) {
                Toast.makeText(DashBoard.this,"setting",Toast.LENGTH_SHORT).show();
            } else if (id == R.id.bottomProfile) {
                loadFragment(new AFragment());
            }
            return true;
        });
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.container,fragment);
//        ft.addToBackStack(null); // Optional: Add to back stack for back navigation

        ft.commit();
    }
}