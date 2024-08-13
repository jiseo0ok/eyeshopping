package com.example.myapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CommunityWRequest extends StringRequest {





    // 서버 url 설정 (php 파일 연동)
    final static private String URL = "http://182.31.216.12:1234/cow.php"; // "http:// 퍼블릭 DNS 주소/Register.php"
    private Map<String, String> parameters;


    public CommunityWRequest(String userID, String hash, String content, String image, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        //parameters.put("No", No);
        parameters.put("userID", userID);
        parameters.put("hash", hash);
        parameters.put("content", content);
        parameters.put("image", image);
        //parameters.put("date", date);
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}