package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Community extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter2 adapter;
    private SwipeRefreshLayout mysrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        ImageButton back = findViewById(R.id.back);
        ImageButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Community.this,CommunityW.class);
                startActivity(intent);
            }
        });
        new BackgroundTask7().execute();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mysrl = findViewById(R.id.content_srl);
        mysrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 새로고침시 동작
                new BackgroundTask7().execute();

                // 종료
                mysrl.setRefreshing(false);
            }
        });

    }
    class BackgroundTask7 extends AsyncTask<Void, Void, String> {
        String target;
        Intent intent = getIntent();
       // String c = intent.getExtras().getString("c");

        @Override
        protected void onPreExecute() {
            target = "http://182.31.216.12:1234/dd.php";
        }


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result) {
            System.out.println(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray(("response"));

                String No;
                String userID;
                String hash;
                String content;
                String image;
                String date;
                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

                recyclerView.setLayoutManager(new LinearLayoutManager(Community.this, RecyclerView.VERTICAL, false)) ; // 상하 스크롤
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)) ; // 좌우 스크롤

                adapter = new Adapter2();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    No = object.getString("No");
                    userID = object.getString("userID");
                    hash = object.getString("hash");
                    content = object.getString("content");
                    image = object.getString("image");
                    date = object.getString("date");

//                item=();

                    adapter.setArrayData(new AdapterList2(No, userID, hash, content, image, date));


                }

                recyclerView.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}