package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

public class show extends AppCompatActivity {
    boolean i = false;
    private static final String url1 = "http://182.31.216.12:1234/zzim.php";
    private static final String url2 = "http://182.31.216.12:1234/zzim2.php";
    private static final String url3 = "http://182.31.216.12:1234/dl.php";
    String a1;
    String a2;
    String a7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent intent =getIntent();
         a1=intent.getStringExtra("contentNo");
        a2 =intent.getStringExtra("shopName");
        String a3 =intent.getStringExtra("title");
        String a4 =intent.getStringExtra("content");
        String a5 =intent.getStringExtra("time");
        String a6 =intent.getStringExtra("image");

         a7=intent.getStringExtra("userID");
        TextView time= (TextView) findViewById(R.id.time);
        TextView title= (TextView) findViewById(R.id.title);
        TextView userID= (TextView) findViewById(R.id.userID);
        Button shopName= (Button) findViewById(R.id.shopName);
        shopName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(show.this, DetailActivity2.class);
                intent1.putExtra("c",a2);
                intent1.putExtra("c1",a7);
                startActivity(intent1);
            }});

                TextView content= (TextView) findViewById(R.id.content);
        ImageView image= (ImageView) findViewById(R.id.image);
        Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.gg);
        image.startAnimation(startAnimation);
        ImageButton back = (ImageButton) findViewById(R.id.back);
        ImageButton good = (ImageButton) findViewById(R.id.good);
        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == false){
                    good.setImageResource(R.drawable.ic_baseline_check2);
                    Toast.makeText(show.this, " 상품 찜 하였습니다.", Toast.LENGTH_SHORT).show();
                    i = true;
                    zzim();

                }else {
                    good.setImageResource(R.drawable.ic_baseline_check1);
                    Toast.makeText(show.this, "취소하였습니다..", Toast.LENGTH_SHORT).show();

                    i = false;
                    zzim2();
                }
            }
        });
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
        }});
        ImageButton delete = (ImageButton) findViewById(R.id.delete);
        GlobalApplication GG = (GlobalApplication) getApplicationContext();
        if(GG.getGuserID().equals(a7)||GG.getGGrade().equals("Admin")){
            delete.setVisibility(View.VISIBLE);
        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del();
                finish();
            }
        });
        time.setText(a5);
        title.setText(a3);
        userID.setText(a7);
        shopName.setText(a2);
        content.setText(a4);
        Glide.with(this).load("http://182.31.216.12:1234/img/"+a6).into(image);


    }
    private void zzim() {
        GlobalApplication GG = (GlobalApplication) getApplication();
        final String userID1 = GG.getGuserID();
        final String contentNo1 = a1;
        final String zzim1 = "TRUE";


        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(show.this, response.toString(), Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(show.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                // 1번 인자는 PHP 파일의 $_POST['']; 부분과 똑같이 해줘야 한다
                map.put("contentNo",contentNo1);
                map.put("userID", userID1);
                map.put("zzim", zzim1);

                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }
    private void zzim2() {
        GlobalApplication GG = (GlobalApplication) getApplication();
        final String userID1 = GG.getGuserID();
        final String contentNo1 = a1;



        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(show.this, response.toString(), Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(show.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                // 1번 인자는 PHP 파일의 $_POST['']; 부분과 똑같이 해줘야 한다
                map.put("contentNo",contentNo1);
                map.put("userID", userID1);


                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }
    private void del() {
        GlobalApplication GG = (GlobalApplication) getApplication();

        final String contentNo1 = a1;



        StringRequest request = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(show.this, response.toString(), Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(show.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                // 1번 인자는 PHP 파일의 $_POST['']; 부분과 똑같이 해줘야 한다
                map.put("contentNo",contentNo1);



                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }
}