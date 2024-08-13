package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ChildFragment32 extends Fragment implements OnMapReadyCallback,GoogleMap.OnCameraIdleListener,GoogleMap.OnMarkerClickListener{


    MapView mapView;
    GoogleMap googleMap1;
    ArrayList<ShopList> shop = new ArrayList<>();
    Context ct;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ct = container.getContext();
        View v = inflater.inflate(R.layout.fragment_child32, container, false);
        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);



        return v;
    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap1 = googleMap;
        MapsInitializer.initialize(this.getActivity());

// Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(35.193778507517, 128.0828737746), 12);


        googleMap1.animateCamera(cameraUpdate);
        googleMap1.setOnCameraIdleListener(this);
        new BackgroundTask1().execute();
        googleMap1.setOnInfoWindowClickListener(infoWindowClickListener);
        //googleMap1.setOnMarkerClickListener(this);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCameraIdle() {

    }
    GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            String markerId = marker.getTitle();
            Toast.makeText(ct, markerId+" 가게를 선택하셨습니다 ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ct,DetailActivity2.class);
            intent.putExtra("c",marker.getTitle());
            intent.putExtra("c1","aaa");
            startActivity(intent);
        }
    };
    @Override
    public boolean onMarkerClick(Marker marker) {
        return true;
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


            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray(("response"));

                String shopId;
                String shopName;
                String shopAddress;
                String shopHash;
                String shopUrl;
                String shopContext;
                String shopCall;


                for (int i = 0;i < jsonArray.length();i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    shopId = object.getString("shopId");
                    shopName = object.getString("shopName");
                    shopAddress = object.getString("shopAddress");
                    shopHash = object.getString("shopHash");
                    shopUrl = object.getString("shopUrl");
                    shopContext = object.getString("shopContext");
                    shopCall = object.getString("shopCall");
                    shop.add(new ShopList(shopId,shopName,shopAddress,shopHash,shopUrl,shopContext,shopCall));
                    Geocoder mCoder = new Geocoder(ct);
                    Double Lat;
                    Double Lon;
                    System.out.println(shop.get(i).getShopAddress());
                    BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.xltucm);
                    Bitmap b=bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false);
                        try {
                            //주소값을 통하여 로케일을 받아온다
                            Lat = mCoder.getFromLocationName(shop.get(i).getShopAddress(), 1).get(0).getLatitude();
                            Lon = mCoder.getFromLocationName(shop.get(i).getShopAddress(), 1).get(0).getLongitude();
                                                        //해당 로케일로 좌표를 구성한다
                        } catch (Exception e) {
                            return;
                        }
                        LatLng t1 = new LatLng(Lat, Lon);

                        googleMap1.addMarker(new MarkerOptions()
                                .position(t1)
                                .title(shop.get(i).getShopName())
                                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)

                                ) );

                    }
                    //mMap.moveCamera(CameraUpdateFactory.newLatLng(shopName1));


            } catch (Exception e) {
                e.printStackTrace();

            }
        }


    }

}
