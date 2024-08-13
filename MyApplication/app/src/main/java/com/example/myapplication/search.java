package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import java.text.BreakIterator;

public class search extends AppCompatActivity {

    EditText searched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        MapView mapView = new MapView(this);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        //mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true);

// 줌 레벨 변경
        //mapView.setZoomLevel(7, true);

// 중심점 변경 + 줌 레벨 변경
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(35.1530112, 128.116011), 9, true);

// 줌 인
        mapView.zoomIn(true);

// 줌 아웃
        MapPoint MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(35.1530112, 128.116011);
        mapView.zoomOut(true);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("드림 하우스");
        marker.setTag(0);
        marker.setMapPoint(MARKER_POINT1);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker);

        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ImageButton menu_2 = findViewById(R.id.menu_2);
        menu_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //화면전환
                Intent intent = new Intent(search.this, MainActivity.class);
                startActivity(intent);
            }
        });
        searched = findViewById(R.id.searched);
        Button button_id1 = findViewById(R.id.button_id1);
        button_id1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searched.setText("#미니멀");
            }
        });
        Button button_id2 = findViewById(R.id.button_id2);
        button_id2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searched.setText("#캐쥬얼");
            }
        });
        Button button_id4 = findViewById(R.id.button_id4);
        button_id4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searched.setText("#가좌동");
            }
        });




    }



    }
