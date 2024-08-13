package com.example.myapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest1 extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://182.31.216.12:1234/reg1.php";
    private Map<String, String> map;


    public RegisterRequest1(String userID, String userPassword, String userName, String userNickname, String Grade, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
       // if()
        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        map.put("userName", userName);
        map.put("userNickname", userNickname );
        map.put("Grade",Grade);


    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
