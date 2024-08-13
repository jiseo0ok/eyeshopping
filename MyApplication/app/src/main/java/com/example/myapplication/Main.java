package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main extends AppCompatActivity {

    String Grade,userID,userPass;
    private static final String TAG = "TEST";
    private RequestQueue queue;
    private Gson gson;
    private TextView tv_outPut;

    RecyclerView recyclerView;
    Adapter1 adapter;
    String result1;

    private ViewPager2 sliderViewPager;
    private LinearLayout layoutIndicator;

    private String[] images = new String[] {
            "http://182.31.216.12:1234/img/banner1.jpg",
            "http://182.31.216.12:1234/img/banner2.png",
            "http://182.31.216.12:1234/img/banner3.jpg",
            "http://182.31.216.12:1234/img/banner4.jpg",
            "http://182.31.216.12:1234/img/banner5.jpg"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        sliderViewPager = findViewById(R.id.sliderViewPager);
        layoutIndicator = findViewById(R.id.layoutIndicators);

        sliderViewPager.setOffscreenPageLimit(1);
        sliderViewPager.setAdapter(new ImageSliderAdapter(this, images));

        sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        setupIndicators(images.length);




        ImageButton ManagementButton = (ImageButton) findViewById(R.id.ManagementButton);
        ImageButton ManagementButton1 = (ImageButton) findViewById(R.id.ManagementButton1);
        ImageButton search = (ImageButton) findViewById(R.id.menu_3);
        ImageButton menu_5 = (ImageButton) findViewById(R.id.menu_5);
        menu_5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Main.this,Shop_Add.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();


            }
        });
        ImageButton menu_4 = (ImageButton) findViewById(R.id.menu_4);
        menu_4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Main.this,Community.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();


            }
        });

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        userPass = intent.getStringExtra("userPass");
        Grade = intent.getStringExtra("Grade");
        SharedPreferencesManager.setLoginInfo(this, userID ,userPass);


        String grade1 = Grade;
        String userid = userID;
        GlobalApplication GuserID = (GlobalApplication) getApplication();
        GuserID.setGuserID(userid);
        GlobalApplication GGrade = (GlobalApplication) getApplication();
        GGrade.setGGrade(grade1);

        new BackgroundTask6().execute();
        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new BackgroundTask1().execute();


            }
        });


        if(!Grade.equals("Admin"))
        {
            menu_5.setVisibility(View.GONE);
        }
        if(!Grade.equals("Admin"))
        {
            ManagementButton.setVisibility(View.GONE);
        }
        if(Grade.equals("User")){
            ManagementButton1.setVisibility(View.GONE);
        }

        ManagementButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new BackgroundTask().execute();
            }
        });
        ManagementButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(Main.this,DetailActivity1.class);
                intent.putExtra("c",userID);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();

            }
        });


    }


    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.bg_indicator_inactive));
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0);
    }

    private void setCurrentIndicator(int position) {
        int childCount = layoutIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_indicator_inactive
                ));
            }
        }
    }

    class BackgroundTask2 extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://182.31.216.12:1234/shopList1.php";
        }


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url  = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp +"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){
            Intent intent = new Intent(Main.this, MainActivity2.class);
            intent.putExtra("ShopList", result);

            Main.this.startActivity(intent);

        }

    }
    class BackgroundTask1 extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://182.31.216.12:1234/shopList.php";
        }


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url  = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp +"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){
            Intent intent = new Intent(Main.this,MainActivity2.class);

            //Intent intent = new Intent(Main.this, MainActivity2.class);
            intent.putExtra("ShopList", result);

            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();


        }

    }

    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://182.31.216.12:1234/userList.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url  = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp +"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){
            Intent intent = new Intent(Main.this, ManagementActivity.class);
            intent.putExtra("userList", result);
            Main.this.startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();

        }

    }


    class BackgroundTask6 extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://182.31.216.12:1234/dlist.php";
        }


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url  = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp +"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){
            System.out.println(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray(("response"));

                String contentNo;
                String userID;
                String title;
                String content;
                String shopName;
                String image;
                String time;
                recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(Main.this, RecyclerView.HORIZONTAL, false));
                //recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false)) ; // 상하 스크롤
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)) ; // 좌우 스크롤

                adapter = new Adapter1();

                for (int i = 0;i < jsonArray.length();i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    contentNo = object.getString("contentNo");
                    userID = object.getString("userID");
                    title = object.getString("title");
                    content = object.getString("content");
                    shopName = object.getString("shopName");
                    image = object.getString("image");
                    time = object.getString("time");

//                item=();

                    adapter.setArrayData(new detailList(contentNo,userID,title,content,shopName,image,time));


                }

                recyclerView.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
}
}

