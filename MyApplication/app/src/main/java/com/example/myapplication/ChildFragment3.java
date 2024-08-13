package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ChildFragment3 extends Fragment {
    RecyclerView recyclerView;
    Adapter adapter;
    private SwipeRefreshLayout mysrl;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mysrl = view.findViewById(R.id.content_srl);
        mysrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new BackgroundTask6().execute();

                // 종료
                mysrl.setRefreshing(false);
            }});
    }


    Context ct;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ct = container.getContext();
        new BackgroundTask6().execute();

        return inflater.inflate(R.layout.fragment_child3, container, false);

    }
    class BackgroundTask6 extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://182.31.216.12:1234/qkwl.php";
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

                recyclerView.setLayoutManager(new GridLayoutManager(ct,3));
                //recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false)) ; // 상하 스크롤
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)) ; // 좌우 스크롤

                adapter = new Adapter();

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

