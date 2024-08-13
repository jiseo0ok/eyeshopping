package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class test111 extends AppCompatActivity implements View.OnClickListener{
    FragmentManager manager;
    FragmentTransaction ft;

    FragmentOne fragmentOne;
    FragmentTwo fragmentTwo;
    FragmentThree fragmentThree;
    FragmentFour fragmentFour;
    FragmentFive fragment5;
    FragmentSix fragment6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test111);
        manager = getSupportFragmentManager();
        GlobalApplication GG = (GlobalApplication) getApplication();
        ImageButton one = findViewById(R.id.menu_1);
        ImageButton two = findViewById(R.id.menu_2);
        ImageButton three = findViewById(R.id.menu_3);
        ImageButton four = findViewById(R.id.menu_4);
        ImageButton five = findViewById(R.id.menu_5);
        ImageButton six = findViewById(R.id.menu_6);

        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();
        fragmentFour = new FragmentFour();
        fragment5 = new FragmentFive();
        fragment6 = new FragmentSix();

        ft = manager.beginTransaction();
        ft.add(R.id.fragment_container, fragmentOne);
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        ft.addToBackStack(null);

        ft.commit();

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        if(GG.getGGrade().equals("Admin"))
        {
            six.setVisibility(View.VISIBLE);

        }

        five.setOnClickListener(this);
        six.setOnClickListener(this);
    }
        @Override
        public void onClick(View v) {
            ft = manager.beginTransaction();
            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
            ft.addToBackStack(null);
            int id = v.getId();
            switch (id) {
                case R.id.menu_1:

                    ft.replace(R.id.fragment_container, fragmentOne);

                    ft.commit();
                    break;
                case R.id.menu_2:

                    ft.replace(R.id.fragment_container, fragmentTwo);
                    ft.commit();
                    break;
                case R.id.menu_3:

                    ft.replace(R.id.fragment_container, fragmentThree);
                    ft.commit();
                    break;
                case R.id.menu_4:

                    ft.replace(R.id.fragment_container, fragmentFour);
                    ft.commit();
                    break;
                case R.id.menu_5:
                    ft.replace(R.id.fragment_container, fragment5);
                    ft.commit();
                    break;
                case R.id.menu_6:
                    ft.replace(R.id.fragment_container, fragment6);
                    ft.commit();
                    break;
            }
        }
    }