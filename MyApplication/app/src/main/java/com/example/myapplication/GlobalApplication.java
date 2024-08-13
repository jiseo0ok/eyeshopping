package com.example.myapplication;

import android.app.Application;



import com.kakao.sdk.common.KakaoSdk;

public class GlobalApplication extends Application {
    private static String version;
    private static String GuserID;
    private static String GshopID;
    private static String GGrade;
    private static String GshopName;
    private static String GuserName;
    private static String GuserPass;
    private static String GuserNickname;

    public static String getGuserNickname() {
        return GuserNickname;
    }

    public static void setGuserNickname(String guserNickname) {
        GuserNickname = guserNickname;
    }

    public static String getGuserPass() {
        return GuserPass;
    }

    public static void setGuserPass(String guserPass) {
        GuserPass = guserPass;
    }

    private static String ipurl="http://182.31.216.12:1234/";

    public String getIpurl() {
        return ipurl;
    }
    public void setIpurl(String ipurl) {
        this.ipurl = ipurl;
    }
    public String getGuserID() {
        return GuserID;
    }


    public void setGuserID(String guserID) {
        this.GuserID = guserID;
    }

    public String getGshopID() {
        return GshopID;
    }

    public void setGshopID(String gshopID) {
        this.GshopID = gshopID;
    }

    public String getGGrade() {
        return GGrade;
    }

    public void setGGrade(String GGrade) {
        this.GGrade = GGrade;
    }

    public String getGshopName() {
        return GshopName;
    }

    public void setGshopName(String gshopName) {
        this.GshopName = gshopName;
    }

    public static String getGuserName() {
        return GuserName;
    }

    public static void setGuserName(String guserName) {
        GuserName = guserName;
    }

    //클래스를 선언한 뒤, 다른 액티비티에서 사용될 함수 입니다. 이건 verdiosn이라는 글로벌 변수에 flag값을 넣게다는 뜻입니다.
//다른 액티비티에서 선언 방법은 밑에 써드릴게요
    public void setVersion(String flag){this.version = flag;}

    //이것은 저장된 값을 불러오는 함수입니다ㅏ.
    public String getVersion(){return version;}


    @Override
    public void onCreate() {
        super.onCreate();
        version = "";
        GuserID ="";
        KakaoSdk.init(this, "1d5b9d29cb919c98b5e411e41379b77b");
    }


}
