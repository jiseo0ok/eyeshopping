package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class CommunityW extends AppCompatActivity {
    String encodeImageString;
    private ImageView image;
    Bitmap bitmap;
    private String shopId1, shopName1, shopAddress1, shopHash1,shopUrl1,shopContext1,shopCall1;
    private String userID1,image1,hash1,content1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_w);


        GlobalApplication GuserID = (GlobalApplication) getApplication();
        String us =GuserID.getGuserID();

        ImageButton imagebt = (ImageButton) findViewById(R.id.imagebt);
        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView userID;
        EditText hash, content;
        hash = (EditText) findViewById(R.id.hash);
        content = (EditText) findViewById(R.id.content);
        userID = (TextView) findViewById(R.id.userID);
        userID.setText(us);
        String No0 ,userID0,hash0,content0,image0,date0;

        imagebt.setOnClickListener(view -> {

            Dexter.withActivity(CommunityW.this)
                    .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(Intent.createChooser(intent, "Browse Image"), 1);
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {

                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permissionRequest, PermissionToken token) {
                            token.continuePermissionRequest();
                        }


                    }).check();
        });
        ImageButton addd = (ImageButton) findViewById(R.id.addd);
        addd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 현재 입력된 정보를 string으로 가져오기
               // image2 = userID.getText().toString();
                userID1=userID.getText().toString();
                hash1 = hash.getText().toString();
                content1 = content.getText().toString();
                image1 = encodeImageString;
                //shopHash1 = shopHash.getText().toString();
                //image1 = image.getText().toString();
              //  shopUrl1 = encodeImageString;
               // shopContext1=shopContext.getText().toString();
               // shopCall1=shopCall.getText().toString();


                // 회원가입 절차 시작
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try{
                            // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                            // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){ // 회원가입이 가능한다면
                                Toast.makeText(getApplicationContext(), "글등록 완료되었습니다.", Toast.LENGTH_SHORT).show();

                               // Intent intent = new Intent(CommunityW.this, Community.class );
                               // startActivity(intent);
                                finish();//액티비티를 종료시킴(회원등록 창을 닫음)

                            }else{// 회원가입이 안된다면
                                Toast.makeText(getApplicationContext(), "글등록에 실패했습니다. 다시 한 번 확인해 주세요.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                CommunityWRequest registerRequest = new CommunityWRequest( userID1, hash1, content1, image1,responseListener);
                RequestQueue queue = Volley.newRequestQueue(CommunityW.this);
                queue.add(registerRequest);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri filePath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                ImageView image = (ImageView) findViewById(R.id.image);
                image.setImageBitmap(bitmap);
                encodeBitmapImage(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] bytesOfImage = byteArrayOutputStream.toByteArray();
        encodeImageString = android.util.Base64.encodeToString(bytesOfImage, Base64.DEFAULT);
    }
}