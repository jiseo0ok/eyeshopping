package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

class Adapter3ViewHolder1 extends RecyclerView.ViewHolder {

    private static final String url = "http://182.31.216.12:1234/tkrwp62.php";
    public ImageView image1;
    Context context;
    public TextView shopId,shopName,shopAddress,shopHash,shopUrl,shopContext,shopCall;

    Adapter3ViewHolder1(Context context, View itemView) {
        super(itemView);
        shopId = itemView.findViewById(R.id.shopId);
        // image = itemView.findViewById(R.id.image);
        shopName = itemView.findViewById(R.id.shopName);
        shopAddress = itemView.findViewById(R.id.shopAddress);
        //No = itemView.findViewById(R.id.No);
        shopUrl = itemView.findViewById(R.id.shopUrl);
        shopHash = itemView.findViewById(R.id.shopHash);
        shopCall = itemView.findViewById(R.id.shopCall);
        image1 = itemView.findViewById(R.id.image1);
        shopContext = itemView.findViewById(R.id.shopContext);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //uploadDataToDB();
                ContentValues values = new ContentValues();
                values.put("shopName", shopName.getText().toString());

                HttpUtil networkTask = new HttpUtil(url, values);
                networkTask.execute();
                Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });
    }
        public class HttpUtil extends AsyncTask<Void, Void, String> {

            String url;
            ContentValues values;

            HttpUtil(String url, ContentValues values) {
                this.url = url;
                this.values = values;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //progress bar를 보여주는 등등의 행위
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
                String result = requestHttpURLConnection.postRequest(url, values);
                return result; // 아래 onPostExecute()의 파라미터로 전달됩니다.
            }

            @Override
            protected void onPostExecute(String result) {



            }


        }
    }