package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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


public class FragmentFour extends Fragment {
    RecyclerView recyclerView;
    Adapter2 adapter;
    private SwipeRefreshLayout mysrl;
    @Nullable
    Context ct;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ct = container.getContext();
        new BackgroundTask7().execute();
        return inflater.inflate(R.layout.fragment_four, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton create = (ImageButton) view.findViewById(R.id.create);
        GlobalApplication GG = (GlobalApplication) getActivity().getApplicationContext();
        if(GG.getGGrade().equals("Admin")){
            create.setVisibility(View.VISIBLE);
        }
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ct,CommunityW.class);
                ct.startActivity(intent);
            }
        });
        mysrl = view.findViewById(R.id.content_srl);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
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


                recyclerView.setLayoutManager(new LinearLayoutManager(ct, RecyclerView.VERTICAL, false)) ; // 상하 스크롤
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

