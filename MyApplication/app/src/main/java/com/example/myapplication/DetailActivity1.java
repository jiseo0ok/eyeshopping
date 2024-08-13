package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetailActivity1 extends AppCompatActivity {
    private SwipeRefreshLayout mysrl;

    RecyclerView recyclerView;
    Adapter adapter;
    String hh;
    String shopName;

    String shopAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);
        Intent intent = getIntent();


        String c = intent.getExtras().getString("c");
        new BackgroundTask6().execute();
        new BackgroundTask7().execute();
        mysrl = findViewById(R.id.content_srl);
        mysrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new BackgroundTask6().execute();

                // 종료
                mysrl.setRefreshing(false);
            }});


        ImageButton add = findViewById(R.id.add);
        ImageButton back = findViewById(R.id.back);

        ImageButton menu_1 = findViewById(R.id.menu_1);
        menu_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(DetailActivity1.this, Main.class);

                startActivity(intent1);
                finish();
            }
        });
        ImageButton menu_4 = findViewById(R.id.menu_4);
        menu_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(DetailActivity1.this, Community.class);

                startActivity(intent1);
                finish();
            }
        });
        ImageButton menu_2 = findViewById(R.id.menu_2);
        menu_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(DetailActivity1.this, Main.class);

                startActivity(intent1);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DetailActivity1.this, DetailWrite.class);
    intent1.putExtra("hh",hh);
                startActivity(intent1);

            }
        });

        GlobalApplication GG = (GlobalApplication) getApplication();
        if(GG.getGuserID().equals(c)||GG.getGGrade().equals("Admin")){
            add.setVisibility(View.VISIBLE);
        }

//        String str = shopAddress;
//
//        Geocoder mCoder = new Geocoder(this);
//        Double Lat;
//        Double Lon;
//        try {
//            //주소값을 통하여 로케일을 받아온다
//
//            Lat = mCoder.getFromLocationName(str, 1).get(0).getLatitude();
//            Lon = mCoder.getFromLocationName(str, 1).get(0).getLongitude();
//            //해당 로케일로 좌표를 구성한다
//
//        } catch (Exception e) {
//            return;
//        }
//        MapView mapView = new MapView(this);
//
//        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
//        mapViewContainer.addView(mapView);
//        //apView.setPOIItemEventListener((MapView.POIItemEventListener) this);
//        // 중심점 변경
//        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(Lat, Lon), true);
//
//// 줌 레벨 변경
//        mapView.setZoomLevel(1, true);
//
//// 중심점 변경 + 줌 레벨 변경
//        //mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 9, true);
//
//// 줌 인
//        mapView.zoomIn(true);
//
//// 줌 아웃
//        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(Lat, Lon);
//        mapView.zoomOut(true);
//        MapPOIItem marker = new MapPOIItem();
//        marker.setItemName(shopName);
//        marker.setTag(0);
//        marker.setMapPoint(MARKER_POINT);
//        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
//        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
//
//        mapView.addPOIItem(marker);



    }
    class BackgroundTask7 extends AsyncTask<Void, Void, String>
    {
        String target;
        Intent intent = getIntent();
        String c = intent.getExtras().getString("c");
        @Override
        protected void onPreExecute() {
            target = "http://182.31.216.12:1234/dlist1.php?userID="+c;
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

                String image;
                String time;
                recyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
                recyclerView.setLayoutManager(new GridLayoutManager(DetailActivity1.this,3));
                //recyclerView.setLayoutManager(new GridLayoutManager(this, 2)) ;
                //recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false)) ; // 상하 스크롤
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)) ; // 좌우 스크롤

                adapter = new Adapter();

                for (int i = 0; i < jsonArray.length();i++) {
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
        }}

        class BackgroundTask6 extends AsyncTask<Void, Void, String>
        {
            String target;
            Intent intent = getIntent();
            String c= intent.getExtras().getString("c");
            @Override
            protected void onPreExecute() {
                target = "http://182.31.216.12:1234/shopList1.php?shopId="+c;
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

                    String shopName = null;
                    String shopContext= null;
                    String shopCall = null;

                    String shopUrl= null;
                    String shopHash= null;

                    for (int i = 0; i < jsonArray.length();i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        shopName = object.getString("shopName");
                        shopContext = object.getString("shopContext");
                        shopAddress = object.getString("shopAddress");
                        shopCall = object.getString("shopCall");

                        shopUrl = object.getString("shopUrl");
                        shopHash = object.getString("shopHash");

//                item=();



                    }
                    TextView shopName1 = findViewById(R.id.shopName1);
            TextView shopAddress1 = findViewById(R.id.shopAddress);
            TextView shopCall1 = findViewById(R.id.shopCall);
            TextView shopContext1 = findViewById(R.id.shopContext);
            TextView shopHash1 = findViewById(R.id.shopHash);
            shopName1.setText(shopName);
            shopAddress1.setText(shopAddress);
             shopCall1.setText(shopCall);
             shopHash1.setText(shopHash);
             hh=shopName;
            shopContext1.setText(shopContext);
            ImageView photo1 = findViewById(R.id.photo);
            Glide.with(DetailActivity1.this).load("http://182.31.216.12:1234/img/"+shopUrl).into(photo1);



                } catch (Exception e) {
                    e.printStackTrace();

                }
            }



    }



//    class BackgroundTask3 extends AsyncTask<Void, Void, String>
//    {
//        String target;
//
//        @Override
//        protected void onPreExecute() {
//            target = "http://182.31.216.12:1234/dlist.php";
//        }
//
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            try {
//                URL url  = new URL(target);
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                String temp;
//                StringBuilder stringBuilder = new StringBuilder();
//                while((temp = bufferedReader.readLine()) != null)
//                {
//                    stringBuilder.append(temp +"\n");
//                }
//                bufferedReader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//                return stringBuilder.toString().trim();
//
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        public void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        public void onPostExecute(String result){
//            System.out.println(result);
//            try {
//                JSONObject jsonObject = new JSONObject(result);
//                JSONArray jsonArray = jsonObject.getJSONArray(("response"));
//
//                String contentNo;
//                String userID;
//                String title;
//                String content;
//                String shopName;
//                String image;
//                String time;
//                recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
//                recyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this, RecyclerView.HORIZONTAL, false));
//                //recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false)) ; // 상하 스크롤
////        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)) ; // 좌우 스크롤
//
//                adapter = new Adapter();
//
//                for (int i = 0;i < jsonArray.length();i++) {
//                    JSONObject object = jsonArray.getJSONObject(i);
//                    contentNo = object.getString("contentNo");
//                    userID = object.getString("userID");
//                    title = object.getString("title");
//                    content = object.getString("content");
//                    shopName = object.getString("shopName");
//                    image = object.getString("image");
//                    time = object.getString("time");
//
////                item=();
//
//                    adapter.setArrayData(new detailList(contentNo,userID,title,content,shopName,image,time));
//
//
//                }
//
//                recyclerView.setAdapter(adapter);
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            }
//        }
//
//    }
}