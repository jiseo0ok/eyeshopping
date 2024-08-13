package com.example.myapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class zzim extends StringRequest {
    final static private String URL = "http://182.31.216.12:1234/zzim.php";
    private Map<String, String> map;


    public zzim(String userID, String contentNo, String zzim, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        // if()
        map = new HashMap<>();
        map.put("userID", userID);
        map.put("contentNo", contentNo);
        map.put("zzim", zzim);


    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
