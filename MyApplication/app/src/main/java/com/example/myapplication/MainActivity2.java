package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ListView listView;
    private ShopListAdapter adapter;
    private List<ShopList> shopList;
    private List<ShopList> saveList;
    String Grade;
    String shopid1, shopname1;
    String  result;
    String url = "http://182.31.216.12:1234/img/image1662285517.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        listView = (ListView) findViewById(R.id.listView);
        shopList = new ArrayList<ShopList>();
        saveList = new ArrayList<ShopList>();
        Grade = intent.getStringExtra("Grade");
        adapter = new ShopListAdapter(getApplicationContext(), shopList, this, saveList);
        result = intent.getStringExtra("ShopList");


        listView.setAdapter(adapter);
        shopid1 = intent.getStringExtra("shopID1");

        ImageButton menu_2 = findViewById(R.id.menu_2);
        menu_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(MainActivity2.this, Main.class);

                startActivity(intent1);


            }
        });

        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray(("response"));
            int count = 0;


            while (count < jsonArray.length()) {
                String shopId, shopName, shopAddress, shopHash, shopUrl, shopContext, shopCall;

                JSONObject object = jsonArray.getJSONObject(count);
                shopId = object.getString("shopId");
                shopName = object.getString("shopName");
                shopAddress = object.getString("shopAddress");
                shopHash = object.getString("shopHash");
                shopUrl = object.getString("shopUrl");
                shopContext = object.getString("shopContext");
                shopCall = object.getString("shopCall");

                 ShopList shop = new ShopList(shopId, shopName, shopAddress, shopHash, shopUrl, shopContext, shopCall);

                shopList.add(shop);
                 saveList.add(shop);

                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }




        EditText search = (EditText) findViewById(R.id.search11);
        search.addTextChangedListener(new TextWatcher() {
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

       // new BackgroundTask1();
    }

    public void searchUser(String search) {
        shopList.clear();
        for (int i = 0; i < saveList.size(); i++) {
            if (saveList.get(i).getShopHash().contains(search)) {
                shopList.add(saveList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

   


    }
