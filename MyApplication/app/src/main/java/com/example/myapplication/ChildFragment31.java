package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ChildFragment31 extends Fragment {


    RecyclerView recyclerView;
    Adapter3 adapter;
    private Adapter3 filterAdapter ;
    EditText editText;
    ArrayList<Adapter3List> search_list  = new ArrayList<>();
    ArrayList<Adapter3List> original_list  = new ArrayList<>();
    private SwipeRefreshLayout mysrl;



    @Nullable
    Context ct;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ct = container.getContext();


        return inflater.inflate(R.layout.fragment_child31, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        editText = (EditText)view.findViewById(R.id.editText);
        ArrayList<Adapter3List> search_list = new ArrayList<>();
        // recyclerView에 추가할 아이템 리스트
        ArrayList<Adapter3List> original_list = new ArrayList<>();
        new BackgroundTask1().execute();


                // 종료

        // shopid1 = intent.getStringExtra("shopID1");

    }



    class BackgroundTask1 extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://182.31.216.12:1234/shopList.php";
        }


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url  = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp +"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String searchText = editText.getText().toString();
                    search_list.clear();
                    if(searchText.equals("")){
                        adapter.setItems(original_list);
                    }
                    else {
                        // 검색 단어를 포함하는지 확인
                        for (int a = 0; a < original_list.size(); a++) {
                            if (original_list.get(a).getShopHash().toLowerCase().contains(searchText.toLowerCase())) {
                                search_list.add(original_list.get(a));
                            }
                            adapter.setItems(search_list);
                        }
                    }
                }
            });

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray(("response"));

                String shopId;
                String shopName;
                String shopAddress;
                String shopHash;
                String shopUrl;
                String shopContext;
                String shopCall;

                recyclerView.setLayoutManager(new LinearLayoutManager(ct, RecyclerView.VERTICAL, false));
                //recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false)) ; // 상하 스크롤
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)) ; // 좌우 스크롤

                adapter = new Adapter3();

                for (int i = 0;i < jsonArray.length();i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    shopId = object.getString("shopId");
                    shopName = object.getString("shopName");
                    shopAddress = object.getString("shopAddress");
                    shopHash = object.getString("shopHash");
                    shopName = object.getString("shopName");
                    shopUrl = object.getString("shopUrl");
                    shopContext = object.getString("shopContext");
                    shopCall = object.getString("shopCall");

//                item=();

                    Adapter3List shop = new Adapter3List(shopId, shopName, shopAddress, shopHash, shopUrl, shopContext, shopCall);
                    /*shopList.add(shop);
                    saveList.add(shop);*/
                    //adapter.setArrayData(new Adapter3List(shopId, shopName, shopAddress, shopHash, shopUrl, shopContext, shopCall));
                    search_list.add(shop);
                    original_list.add(shop);
                    adapter.setArrayData(original_list);
                    // adapter.setArrayData(shop);
                }

                // adapter.setArrayData(shop);
                recyclerView.setAdapter(adapter);

            } catch (Exception e) {
                e.printStackTrace();

            }

        }


    }
}

