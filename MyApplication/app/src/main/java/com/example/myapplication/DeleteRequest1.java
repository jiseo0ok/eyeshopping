package com.example.myapplication;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteRequest1 extends StringRequest {

    final static private String URL = "http://182.31.216.12:1234/Delete.php";
    private Map<String, String> parameters;

    public DeleteRequest1(String userID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID",userID);
    }

    @Override
    public Map <String, String> getParams() {
        return parameters;
    }
}