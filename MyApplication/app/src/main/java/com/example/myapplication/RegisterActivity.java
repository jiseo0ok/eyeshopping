package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText userID,userPassword, userName, userNickname,Grade;
    String nickname1;
    ImageButton back;
    String photo;
    String kakaoId1;
    private static final String url = "http://182.31.216.12:1234/register1.php";
    private Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();
        nickname1 = intent.getStringExtra("nickname"); // id가져오기
        photo = intent.getStringExtra("photo");
        kakaoId1 = intent.getStringExtra("kakaoid");
        userID = findViewById(R.id.id);
        userPassword = findViewById(R.id.pw);
        userName = findViewById(R.id.name);
        userNickname = findViewById(R.id.nickname);
        btn_register = findViewById(R.id.btn_register);
        Grade = findViewById(R.id.Grade);
        back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            finish();
        });

        btn_register.setOnClickListener(view -> {
            uploadDataToDB();
        });
    }
    private void uploadDataToDB() {
        final String userID1 = userID.getText().toString().trim();
        final String userPassword1 = userPassword.getText().toString().trim();
        final String userName1 = userName.getText().toString().trim();
        final String userNickname1 = userNickname.getText().toString().trim();
        final String Grade1 = Grade.getText().toString().trim();


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(RegisterActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                // 1번 인자는 PHP 파일의 $_POST['']; 부분과 똑같이 해줘야 한다
                map.put("userID", userID1);
                map.put("userPassword", userPassword1);
                map.put("userName", userName1);
                map.put("userNickname", userNickname1 );
                map.put("Grade",Grade1);
                return map;
            }
        };
        if(!userID.getText().toString().equals("")&&!userPassword.getText().toString().equals("")&&!userName.getText().toString().equals("")&&!userNickname.getText().toString().equals("")) {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
        }else Toast.makeText(this, "공백은 불가합니다.", Toast.LENGTH_SHORT).show();

    }


}


        /*
        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userID = id.getText().toString();
                String userPassword = pw.getText().toString();
                String userName = name.getText().toString();
                String userNickname = nickname.getText().toString();
                String Grade = grade.getText().toString();





                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 회원등록에 성공한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(RegisterActivity.this, LoginActivity2.class);
                                finish();
                            } else { // 회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(),"회원 등록에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                // 서버로 Volley를 이용해서 요청을 함.
                RegisterRequest registerRequest = new RegisterRequest(userID,userPassword,userName,userNickname,Grade,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }
        });
*/

