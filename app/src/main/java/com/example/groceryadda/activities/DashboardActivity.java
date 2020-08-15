package com.example.groceryadda.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.groceryadda.R;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView imageView,card;
    TextView nav_profile,nav_moreSetting,payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawar);

        toolbar = findViewById(R.id.toolbar_dashboard);
        drawerLayout = findViewById(R.id.drawarLayout);
        navigationView=findViewById(R.id.navigatioView);
        imageView = findViewById(R.id.image_drawer_toggle);
        nav_profile = findViewById(R.id.tv_nav_profile);
        nav_moreSetting = findViewById(R.id.tv_nav_moreSetting);
        payment=findViewById(R.id.payment);

        card=findViewById(R.id.appleCard);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(DashboardActivity.this,drawerLayout,R.string.openDrawar,R.string.closeDrawar);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                   drawerLayout.closeDrawer(GravityCompat.START);
               }else{
                   drawerLayout.openDrawer(GravityCompat.START);
               }
            }
        });

        nav_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,profileActivity.class);
                startActivity(intent);
            }
        });
        nav_moreSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });


        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,ItemActivity.class);
                startActivity(intent);
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DashboardActivity.this,payment.class);
                startActivity(i);
            }
        });

    }

}
