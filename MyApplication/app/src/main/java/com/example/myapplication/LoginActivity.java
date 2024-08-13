package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    private final static String TAG = "유저";
    private Button kakaoAuth, googleAuth;
    public static Context mContext;
    private SharedPreferences sharedPreferences;
    private User currentUser;
    private String userImageString = "";
    private Bitmap mBitmap;
    SharedPreferences.Editor editor;
    private Boolean isTrue = false;
    private Boolean nextIntent = false;
    private String meetingId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken != null) {
                    Log.i("user", oAuthToken.getAccessToken() + " " + oAuthToken.getRefreshToken());
                }
                if (throwable != null) {
                    // TBD
                    Log.w(TAG, "invoke: " + throwable.getLocalizedMessage());
                }
                updateKakaoLoginUi();

                return null;
            }
        };

        ImageButton kakaoAuth = findViewById(R.id.kakao_auth_button);  // 저는 카카오톡 로그인 버튼을 만들어서 했습니다.

        kakaoAuth.setOnClickListener(new View.OnClickListener() {   // 로그인 버튼 클릭 시
            @Override
            public void onClick(View v) {
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)) {
                    // 카카오톡이 있을 경우?
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callback);
                } else {
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this, callback);
                }
            }
        });
        updateKakaoLoginUi();
    }

    public void updateKakaoLoginUi() {
        // 카카오 UI 가져오는 메소드 (로그인 핵심 기능)
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if (user != null) {

                    // 유저 정보가 정상 전달 되었을 경우
                    Log.i(TAG, "id " + user.getId());   // 유저의 고유 아이디를 불러옵니다.
                    Log.i(TAG, "invoke: nickname=" + user.getKakaoAccount().getProfile().getNickname());  // 유저의 닉네임을 불러옵니다.
                    Log.i(TAG, "userimage " + user.getKakaoAccount().getProfile().getProfileImageUrl());
                    // 유저의 이미지 URL을 불러옵니다.
                    Intent intent = new Intent(LoginActivity.this,LoginActivity2.class);
                    intent.putExtra("id", ""+user.getId());
                    intent.putExtra("nickname", ""+user.getKakaoAccount().getProfile().getNickname());
                    intent.putExtra("photo", ""+ user.getKakaoAccount().getProfile().getProfileImageUrl());
                    Toast.makeText(getApplicationContext(), user.getKakaoAccount().getProfile().getNickname()+" 로그인 성공!", Toast.LENGTH_LONG).show();

                    startActivity(intent);

                    // 이 부분에는 로그인이 정상적으로 되었을 경우 어떤 일을 수행할 지 적으면 됩니다.
                }
                if (throwable != null) {
                    // 로그인 시 오류 났을 때
                    // 키해시가 등록 안 되어 있으면 오류 납니다.
                    Log.w(TAG, "invoke: " + throwable.getLocalizedMessage());

                }


                return null;

            }
        });
    }
}