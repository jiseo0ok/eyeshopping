package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

class Adapter2ViewHolder extends RecyclerView.ViewHolder {
    public TextView image;
    public ImageView image1;
    //public TextView No;
    public TextView userID;
    public TextView hash;
    public TextView content;
    public TextView reply;
    public TextView date;
    String url = "http://182.31.216.12:1234/tkrwprhd.php";
    public ImageButton good;

    Adapter2ViewHolder(Context context, View itemView) {
        super(itemView);
        GlobalApplication GG = (GlobalApplication) context.getApplicationContext();





        userID = itemView.findViewById(R.id.userID);
       // image = itemView.findViewById(R.id.image);
        hash = itemView.findViewById(R.id.hash);
        content = itemView.findViewById(R.id.content);
        //No = itemView.findViewById(R.id.No);
       // reply = itemView.findViewById(R.id.reply);
        date = itemView.findViewById(R.id.date);
        image1 = itemView.findViewById(R.id.image1);
        if(GG.getGGrade().equals("Admin")){
            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ContentValues values = new ContentValues();
                    values.put("hash", hash.getText().toString());

                  HttpUtil networkTask = new HttpUtil(url, values);
                    networkTask.execute();
                    Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });

        }
        good = itemView.findViewById(R.id.good);




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
