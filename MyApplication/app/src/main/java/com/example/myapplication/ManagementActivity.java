package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

public class ManagementActivity extends AppCompatActivity {
    private ListView listView;
    private userListAdapter adapter;
    private List<user> userList;
    private List<user> saveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        Intent intent = getIntent();
        listView = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<user>();
        saveList = new ArrayList<user>();

        adapter = new userListAdapter(getApplicationContext(), userList,this,saveList);
        listView.setAdapter(adapter);

        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            JSONArray jsonArray = jsonObject.getJSONArray(("response"));
            int count =0;
            String userID, userPassword, userName, userNickname, Grade;
            while(count < jsonArray.length())
            {
                JSONObject object = jsonArray.getJSONObject(count);
                userID = object.getString("userID");
                userPassword = object.getString("userPassword");
                userName = object.getString("userName");
                userNickname = object.getString("userNickname");
                Grade = object.getString("Grade");
                user user = new user(userID, userPassword, userName, userNickname,Grade);

                    userList.add(user);
                    saveList.add(user);  // ## 추가된 코드 ##


                count++;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        EditText search22 = (EditText) findViewById(R.id.search22);
        search22.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                searchUser(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void searchUser(String search) {
        userList.clear();
        for(int i =0; i<saveList.size();   i++) {
            if(saveList.get(i).getUserID().contains(search))
            {
                userList.add(saveList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }
}