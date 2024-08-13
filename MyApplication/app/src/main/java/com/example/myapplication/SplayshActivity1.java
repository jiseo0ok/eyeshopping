package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class SplayshActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splaysh);
        ImageView main = (ImageView) findViewById(R.id.main);
        Glide.with(this).load(R.drawable.main).into(main);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent main1 = new Intent(SplayshActivity1.this,MainActivity.class);
                startActivity(main1);
                finish();
            }
        }, 4000);
    }

}