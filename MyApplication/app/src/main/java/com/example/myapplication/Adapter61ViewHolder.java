package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

class Adapter61ViewHolder extends RecyclerView.ViewHolder {

    public ImageView image1;
    private static final String url = "http://182.31.216.12:1234/tkrwp61.php";
    public TextView userID,userPassword,userName,userNickname,Grade;
    public Button deleteButton;

    Adapter61ViewHolder(Context context, View itemView) {
        super(itemView);
        userID = itemView.findViewById(R.id.userID);

        userPassword = itemView.findViewById(R.id.userPassword);

        userName = itemView.findViewById(R.id.userName);
        userNickname = itemView.findViewById(R.id.userNickname);
        deleteButton = itemView.findViewById(R.id.deleteButton);
        Grade = itemView.findViewById(R.id.Grade);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("userID", userID.getText().toString());

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
