package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class test extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent = getIntent();

        String url= intent.getStringExtra("url");
        TextView tx = findViewById(R.id.tx);
        tx.setText(url);

        Uri uri=Uri.parse(url);
        image = findViewById(R.id.image);
        image.setImageURI(uri);

    }
}