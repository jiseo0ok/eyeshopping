package com.example.myapplication;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class shopr extends StringRequest {

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://182.31.216.12:1234/shopr.php";
    private Map<String, String> map;


    public shopr(String shopId, String shopName, String shopAddress, String shopHash,String shopUrl, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("shopId",shopId);
        map.put("shopName", shopName);
        map.put("shopAddress", shopAddress);
        map.put("shopHash", shopHash);
        map.put("shopUrl",shopUrl);
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
