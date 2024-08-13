package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity2 extends AppCompatActivity {
    EditText id,pw;
    Button login, register;
    private Context mContext;
    String rid,rpw;
    String userID,userPass;

    String url = "http://182.31.216.12:1234/fhrmdls.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        mContext = this; // 이거 필수!
        ImageView main = (ImageView) findViewById(R.id.main);
        Glide.with(this).load(R.drawable.main).into(main);
        id =findViewById(R.id.id);

        pw =findViewById(R.id.pw);
        login =findViewById(R.id.login);
        register =findViewById(R.id.register);;



    register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity2.this, RegisterActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        }
    });

             login.setOnClickListener(new View.OnClickListener() {
            @Override
              public void onClick(View view) {
                if(id.getText().toString().equals("")||pw.getText().toString().equals("")){
            // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                    Toast.makeText(mContext, "로그인 오류 아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                    return;

                }
                else{
                    ContentValues values = new ContentValues();
                    values.put("userID", id.getText().toString());
                    values.put("userPassword", pw.getText().toString());

                    HttpUtil networkTask = new HttpUtil(url, values);
                    networkTask.execute(); }
        }
    });


}


    public class HttpUtil extends AsyncTask<Void, Void, String> {

        String url;
        ContentValues values;

        HttpUtil(String url, ContentValues values){
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
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray(("response"));
                String userID = null;
                String userPass = null;
                String userName = null;
                String Grade = null;
                String userNickname = null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    userID = object.getString("userID");
                    userPass = object.getString("userPassword");
                    userName = object.getString("userName");
                    Grade = object.getString("Grade");
                    userNickname = object.getString("userNickname");
                }
                if (userID.equals("") && userPass.equals("") && userName.equals("") && Grade.equals("") && userNickname.equals("")) {
                    Toast.makeText(mContext, "존재하지 않는 회원입니다.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
//
                    Toast.makeText(getApplicationContext(), "[" + Grade + "]" + userName + "님 안녕하세요.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity2.this, test111.class);
                    intent.putExtra("userID", userID);
                    intent.putExtra("userPass", userPass);

                    intent.putExtra("Grade", Grade);
                    GlobalApplication GG = (GlobalApplication) getApplication();
                    GG.setGuserID(userID);
                    GG.setGGrade(Grade);
                    GG.setGuserName(userName);
                    GG.setGuserPass(userPass);
                    GG.setGuserNickname(userNickname);


                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();

                } }catch(JSONException e){
                    e.printStackTrace();
                }


        }}}



