package com.example.myapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class See1Request extends StringRequest {
    final static private String URL = "http://182.31.216.12:1234/dlist.php";
    private Map<String, String> map;


    public See1Request(Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();


    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
