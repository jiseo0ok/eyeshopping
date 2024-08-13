package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class DetailWrite extends AppCompatActivity {

    TextView userID,shopName;
    EditText title,content;
    ImageButton imagebt;
    ImageView image;
    Button reg;
    Bitmap bitmap;
    String encodeImageString;
    String userid1;
    String shopname1;
    private static final String url = "http://182.31.216.12:1234/dwrite.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_write);
        Intent intent = getIntent();
        String hh = intent.getStringExtra("hh");

        userID = findViewById(R.id.userID);
        shopName = findViewById(R.id.shopName);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        image = findViewById(R.id.image);
        imagebt = findViewById(R.id.imagebt);
        GlobalApplication GG = (GlobalApplication) getApplication();
        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        userid1=GG.getGuserID();
        userID.setText(userid1);
        shopname1= hh;
        shopName.setText(shopname1);
        reg = findViewById(R.id.reg);
        imagebt.setOnClickListener(view -> {

            Dexter.withActivity(DetailWrite.this)
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

        reg.setOnClickListener(view -> {
            if(!title.getText().equals("")&&!encodeImageString.equals("")&&!content.getText().equals("")){
            uploadDataToDB();}
            else{
                Toast.makeText(this, "공백은 안됩니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadDataToDB() {
        final String titlephp = title.getText().toString().trim();
        final String contentphp = content.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                title.setText("");
                content.setText("");
                //image.setImageResource(R.mipmap.ic_launcher_round);

                Toast.makeText(DetailWrite.this, "글 작성 완료", Toast.LENGTH_SHORT).show();

            //    Intent intent = new Intent(DetailWrite.this, DetailActivity.class);
              //  intent.putExtra("url","http://182.31.216.12:1234/img/");
             //   startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailWrite.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                // 1번 인자는 PHP 파일의 $_POST['']; 부분과 똑같이 해줘야 한다
                map.put("userID",userid1);
                map.put("shopName",shopname1);
                map.put("title", titlephp);
                map.put("content", contentphp);
                map.put("image", encodeImageString);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri filePath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
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

