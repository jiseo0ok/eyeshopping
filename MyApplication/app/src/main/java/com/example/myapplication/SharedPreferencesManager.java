package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SharedPreferencesManager {
    private static final String PREFERENCES_NAME = "my_preferences";

    public static SharedPreferences getPreferences(Context mContext){
        return mContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
    public static void setLoginInfo(Context context, String id, String pw){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("id", id);
        editor.putString("pw", pw);

        editor.apply();
    }

    public static Map<String, String> getLoginInfo(Context context){
        SharedPreferences prefs = getPreferences(context);
        Map<String, String> LoginInfo = new HashMap<>();
        String id = prefs.getString("id", "");
        String pw = prefs.getString("pw", "");

        LoginInfo.put("id", id);
        LoginInfo.put("pw", pw);

        return LoginInfo;
    }
    public static void clearPreferences(Context context){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}
