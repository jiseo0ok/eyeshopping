package com.example.myapplication;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class ShopListAdapter extends BaseAdapter {
    Context context;
    private List<ShopList> shopList;
    private List<ShopList> shopSave;
    private Activity parentActivity;
        //GlobalApplication GuserID = (GlobalApplication) parentActivity.getApplication();
    String c;


    String a;
        public ShopListAdapter(Context context, List<ShopList> shopList, Activity parentActivity, List<ShopList> shopSave) {
        this.context = context;
        this.shopList = shopList;
        this.parentActivity = parentActivity;
        this.shopSave = shopSave;

    }


    @Override
    public int getCount() {
        return shopList.size();
    }

    @Override
    public Object getItem(int position) {
        return shopList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.shoplist, null);
        TextView shopId = (TextView) v.findViewById(R.id.shopId);
        TextView shopName = (TextView) v.findViewById(R.id.shopName);
        TextView shopAddress = (TextView) v.findViewById(R.id.shopAddress);
        TextView shopHash = (TextView) v.findViewById(R.id.shopHash);
        TextView shopUrl = (TextView) v.findViewById(R.id.shopUrl);
        TextView shopContext = (TextView) v.findViewById(R.id.shopContext);
        TextView shopCall = (TextView) v.findViewById(R.id.shopCall);

        ImageButton image1 = (ImageButton) v.findViewById(R.id.image1);



        shopId.setText(shopList.get(i).getShopId());
        shopName.setText(shopList.get(i).getShopName());
        shopAddress.setText(shopList.get(i).getShopAddress());
        shopHash.setText(shopList.get(i).getShopHash());
        shopUrl.setText(shopList.get(i).getShopUrl());
        shopContext.setText(shopList.get(i).getShopContext());

        shopCall.setText(shopList.get(i).getShopCall());

        //Uri uri1 = Uri.parse(shopList.get(i).getShopUrl());
        Glide.with(context).load("http://182.31.216.12:1234/img/"+shopList.get(i).getShopUrl()).into(image1);




        //Bitmap bm = LoadImage( "http://182.31.216.12:1234/img/blog.jpg" ) ;



       // ImageView iv = (ImageView) v.findViewById(R.id.image1);

       // Bitmap bm = LoadImage( "https://t1.daumcdn.net/cfile/tistory/1946B11A4C5606ED3C" ) ;

       //Bitmap resize = Bitmap.createScaledBitmap(bm, 30, 40,true);

       //iv.setImageBitmap( resize ) ;

        String s1,s2,s3,s4,s5,s6,s7;
        s1=shopList.get(i).getShopId();
        s2=shopList.get(i).getShopName();
        s3=shopList.get(i).getShopAddress();
        s4=shopList.get(i).getShopHash();
        s5=shopList.get(i).getShopUrl();
        s6=shopList.get(i).getShopContext();
        s7=shopList.get(i).getShopCall();


        String photo="http://182.31.216.12:1234/img/"+shopList.get(i).getShopUrl();;

        v.setTag(shopList.get(i).getShopId());
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=shopList.get(i).getShopId();
                Toast.makeText(context.getApplicationContext(), c+"입니다.",Toast.LENGTH_SHORT).show();
//                Toast.makeText(context.getApplicationContext(), a,Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(context,DetailActivity.class);
                intent1.putExtra("c",c);



                intent1.putExtra("s1", s1);
                intent1.putExtra("s2", s2);
                intent1.putExtra("s3", s3);
                intent1.putExtra("s4", s4);
                intent1.putExtra("s5", s5);
                intent1.putExtra("s6", s6);
                intent1.putExtra("s7", s7);
                intent1.putExtra("photo",photo);

                GlobalApplication GshopID = (GlobalApplication) parentActivity.getApplication();
               GshopID.setGshopID(s1);
                GlobalApplication GshopName = (GlobalApplication) parentActivity.getApplication();
                GshopName.setGshopName(s2);


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                 //new BackgroundTask3().execute();
                   //  intent1.putExtra("a", a);
                       context.startActivity(intent1.addFlags(FLAG_ACTIVITY_NEW_TASK));
                    }
                }, 2000); //딜레이 타임 조절


            }
        });
        return v;
    }





}