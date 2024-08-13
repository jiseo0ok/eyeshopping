package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class profile extends AppCompatActivity {
    String nickname;
    String photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();

        nickname = intent.getStringExtra("nickname"); // id가져오기
        photo = intent.getStringExtra("photo");
        TextView idid = findViewById(R.id.idid);
        idid.setText(nickname);

        ImageView idid2 = findViewById(R.id.idid2);
        Glide.with(this).load(photo).into(idid2);

        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}