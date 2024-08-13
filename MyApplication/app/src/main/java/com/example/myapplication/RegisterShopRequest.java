package com.example.myapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterShopRequest extends StringRequest {





    // 서버 url 설정 (php 파일 연동)
    final static private String URL = "http://182.31.216.12:1234/reg.php"; // "http:// 퍼블릭 DNS 주소/Register.php"
    private Map<String, String> parameters;


    public RegisterShopRequest(String shopId, String shopName, String shopAddress, String shopHash, String shopUrl,String shopContext,String shopCall,Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("shopId", shopId);
        parameters.put("shopName", shopName);
        parameters.put("shopAddress", shopAddress);
        parameters.put("shopHash", shopHash);
        parameters.put("shopUrl", shopUrl);
        parameters.put("shopContext", shopContext);
        parameters.put("shopCall", shopCall);
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}