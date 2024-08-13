package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ChildFragment52 extends Fragment {

    Context ct;
    String c;
    TextView shopId;
    EditText shopName;
    EditText shopAddress ;
    EditText shopHash;
    EditText shopContext;
    EditText shopCall;
    private static final String url = "http://182.31.216.12:1234/tnwjd52.php";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ct = container.getContext();
        View v = inflater.inflate(R.layout.fragment_child52, container, false);



        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button go = (Button) view.findViewById(R.id.go);
        GlobalApplication GG = (GlobalApplication) getActivity().getApplicationContext();
        c = GG.getGuserID();
        new BackgroundTask6().execute();
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DetailActivity3.class);
                intent.putExtra("c",c);
                ct.startActivity(intent);
            }
        });
        shopId = view.findViewById(R.id.shopId);
        shopName = view.findViewById(R.id.shopName);
       shopAddress = view.findViewById(R.id.shopAddress);
        shopHash = view.findViewById(R.id.shopHash);
        shopContext = view.findViewById(R.id.shopContext);
        shopCall = view.findViewById(R.id.shopCall);
        Button tnwjd = (Button) view.findViewById(R.id.tnwjd);
        tnwjd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDataToDB();
            }
        });
    }
    private void uploadDataToDB() {

        final String shopId1 = shopId.getText().toString().trim();

        final String shopName1 = shopName.getText().toString().trim();
        final String shopAddress1 = shopAddress.getText().toString().trim();
        final String shopHash1 = shopHash.getText().toString().trim();
        final String shopContext1 = shopContext.getText().toString().trim();
        final String shopCall1 = shopCall.getText().toString().trim();



        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(ct, response.toString(), Toast.LENGTH_SHORT).show();
                ((Activity) ct).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ct, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                // 1번 인자는 PHP 파일의 $_POST['']; 부분과 똑같이 해줘야 한다
                map.put("shopId", shopId1);
                map.put("shopName", shopName1);
                map.put("shopAddress", shopAddress1);
                map.put("shopHash", shopHash1);
                map.put("shopContext", shopContext1 );
                map.put("shopCall", shopCall1 );

                return map;
            }
        };
        if(!shopName.getText().toString().equals("")&&!shopAddress.getText().toString().equals("")&&!shopHash.getText().toString().equals("")&&!shopContext.getText().toString().equals("")&&!shopCall.getText().toString().equals("")) {
            RequestQueue queue = Volley.newRequestQueue(ct.getApplicationContext());
            queue.add(request);
        }else Toast.makeText(ct, "공백은 불가합니다.", Toast.LENGTH_SHORT).show();

    }
    class BackgroundTask6 extends AsyncTask<Void, Void, String>
    {
        String target;

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

                String shopName1 = null;
                String shopContext1= null;
                String shopAddress1 = null;
                String shopCall1 = null;

                //String shopUrl= null;
                String shopHash1= null;

                for (int i = 0; i < jsonArray.length();i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    shopName1 = object.getString("shopName");
                    shopContext1 = object.getString("shopContext");
                    shopAddress1 = object.getString("shopAddress");
                    shopCall1 = object.getString("shopCall");

                    //shopUrl = object.getString("shopUrl");
                    shopHash1 = object.getString("shopHash");

//                item=();



                }
                shopId.setText(c);
                shopName.setText(shopName1);
                shopAddress.setText(shopAddress1);
                shopCall.setText(shopCall1);
                shopHash.setText(shopHash1);
                shopContext.setText(shopContext1);
                //ImageView photo1 = findViewById(R.id.photo);
                //Glide.with(DetailActivity1.this).load("http://182.31.216.12:1234/img/"+shopUrl).into(photo1);



            } catch (Exception e) {
                e.printStackTrace();

            }
        }}
}