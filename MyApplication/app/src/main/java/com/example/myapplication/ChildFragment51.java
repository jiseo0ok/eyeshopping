package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class ChildFragment51 extends Fragment {

    private static final String url = "http://182.31.216.12:1234/tnwjd51.php";
    EditText userPassword, userName, userNickname;
    TextView userID;
Context ct;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ct = container.getContext();
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_child51, container, false);
        return v;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       userID = (TextView) view.findViewById(R.id.userID);
        userPassword = (EditText) view.findViewById(R.id.userPassword);
        userName = (EditText) view.findViewById(R.id.userName);
       userNickname = (EditText) view.findViewById(R.id.userNickname);
        TextView Grade = (TextView) view.findViewById(R.id.Grade);

        GlobalApplication GG = (GlobalApplication) getActivity().getApplicationContext();
        userID.setText(GG.getGuserID());
        userPassword.setText(GG.getGuserPass());
        userName.setText(GG.getGuserName());
        Grade.setText(GG.getGGrade());
        userNickname.setText(GG.getGuserNickname());

        Button tnwjd = (Button) view.findViewById(R.id.tnwjd);
        tnwjd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDataToDB();
            }
        });


    }
    private void uploadDataToDB() {

        final String userID1 = userID.getText().toString().trim();

        final String userPassword1 = userPassword.getText().toString().trim();
        final String userName1 = userName.getText().toString().trim();
        final String userNickname1 = userNickname.getText().toString().trim();



        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(ct, response.toString(), Toast.LENGTH_SHORT).show();
                ((Activity) ct).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ct, error.toString(), Toast.LENGTH_SHORT).show();
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

                return map;
            }
        };
        if(!userPassword.getText().toString().equals("")&&!userName.getText().toString().equals("")&&!userNickname.getText().toString().equals("")) {
            RequestQueue queue = Volley.newRequestQueue(ct.getApplicationContext());
            queue.add(request);
        }else Toast.makeText(ct, "공백은 불가합니다.", Toast.LENGTH_SHORT).show();

    }
}