package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    String nickname;
    String photo;
    String kakaoid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        //TextView idid = findViewById(R.id.idid);
        //idid.setText(nickname);

       ImageView idid2 = findViewById(R.id.idid2);
       String photo2 = photo;
        //Glide.with(this).load(photo2).into(idid2);

        ImageButton search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //화면전환
                Intent intent = new Intent(MainActivity.this, search.class);
                startActivity(intent);

            }
        });
        ImageButton menu_1 = findViewById(R.id.menu_1);
        menu_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //화면전환
                Intent intent = new Intent(MainActivity.this, Shop.class);
                startActivity(intent);

            }
        });

        ImageButton profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, profile.class);

                intent.putExtra("nickname", ""+nickname);
                intent.putExtra("photo", ""+photo);
                startActivity(intent);

            }
        });
        ImageButton menu_4 = findViewById(R.id.menu_4);
        menu_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, community2.class);
                intent.putExtra("nickname", ""+nickname);
                intent.putExtra("photo", ""+photo);
                startActivity(intent);

            }
        });

    }

    }

