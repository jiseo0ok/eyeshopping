package com.example.myapplication;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.webkit.CookieManager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PHPComm extends Activity {

    // serverURL : JSON 요청을 받는 서버의 URL
    // postParams : POST 방식으로 전달될 입력 데이터
    // 반환 데이터 : 서버에서 전달된 JSON 데이터
    public static String getJson(String serverUrl, String postParams) throws Exception {

        BufferedReader bufferedReader = null;
        try {
            Thread.sleep(100);
            URL url = new URL(serverUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 세션 쿠키 전달
            String cookieString = CookieManager.getInstance().getCookie("http://182.31.216.12:1234/dlist");

            StringBuilder sb = new StringBuilder();

            if(conn != null){ // 연결되었으면
                //add request header
                conn.setRequestMethod("POST");
                conn.setRequestProperty("USER-AGENT", "Mozilla/5.0");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                if (cookieString != null) {
                    conn.setRequestProperty("Cookie", cookieString);
                    Log.e("PHP_getCookie", cookieString);
                }
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
                conn.setUseCaches(false);
                conn.setDefaultUseCaches(false);
                conn.setDoOutput(true); // POST 로 데이터를 넘겨주겠다는 옵션
                conn.setDoInput(true);

                // Send post request
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(postParams);
                wr.flush();
                wr.close();

                int responseCode = conn.getResponseCode();
                System.out.println("GET Response Code : " + responseCode);
                if(responseCode == HttpURLConnection.HTTP_OK){ // 연결 코드가 리턴되면
                    bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json + "\n");
                    }
                }
                bufferedReader.close();
            }
            System.out.println("PHP Comm Out : " + sb.toString());
            return sb.toString().trim();
            // 수행이 끝나고 리턴하는 값은 다음에 수행될 onProgressUpdate 의 파라미터가 된다
        } catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    public static boolean isExists(String URLName) {
        // 서버에 파일의 존재 유무 파악
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static Bitmap autoresize_decodeResource(Resources res, int resId, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInAutoSize(options, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInAutoSize(BitmapFactory.Options options, int reqHeight) {
        // 원본 이미지의 높이와 너비
        final int height = options.outHeight;
        final int width = options.outWidth;

        float ratio = width / height;
        int reqWidth = Math.round((float) ratio * width);

        int inSampleSize = 1;
        if (height > reqHeight) {
            final int halfHeight = height / 2;
            while ((halfHeight / inSampleSize) > reqHeight) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
