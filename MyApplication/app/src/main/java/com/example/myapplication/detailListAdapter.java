package com.example.myapplication;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class detailListAdapter extends BaseAdapter {

    private Context context;
    private List<detailList> detailList;
    private Activity parentActivity;
    private List<detailList> saveList;

    String a="http://182.31.216.12:1234/img/";
    public detailListAdapter(Context context, List<detailList> detailList,Activity parentActivity){
        this.context = context;
        this.detailList = detailList;
        this.parentActivity = parentActivity;


    }
    

    @Override
    public int getCount() {
        return detailList.size();
    }

    @Override
    public Object getItem(int position) {
        return detailList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int i, View convertView, final ViewGroup parent) {
        View v = View.inflate(context, R.layout.dlist, null);
        TextView contentNo = (TextView) v.findViewById(R.id.contentNo);
        TextView userID = (TextView) v.findViewById(R.id.userID);
        TextView title = (TextView) v.findViewById(R.id.title);
        TextView content = (TextView) v.findViewById(R.id.content);
        TextView shopName = (TextView) v.findViewById(R.id.shopName);
        TextView image = (TextView) v.findViewById(R.id.image);
        ImageButton image1 = (ImageButton) v.findViewById(R.id.image1);

        TextView time = (TextView) v.findViewById(R.id.time);

        contentNo.setText(detailList.get(i).getContentNo());
        userID.setText(detailList.get(i).getUserID());
        title.setText(detailList.get(i).getTitle());
        content.setText(detailList.get(i).getContent());
        shopName.setText(detailList.get(i).getShopName());
        image.setText(detailList.get(i).getImage());
        time.setText(detailList.get(i).getTime());
        Glide.with(context).load("http://182.31.216.12:1234/img/"+detailList.get(i).getImage()).into(image1);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent intent = new Intent(context,MainActivity4.class);
            intent.putExtra("contentNo", detailList.get(i).getContentNo());
                intent.putExtra("userID", detailList.get(i).getUserID());
                intent.putExtra("title", detailList.get(i).getTitle());
                intent.putExtra("content", detailList.get(i).getContent());
                intent.putExtra("shopName", detailList.get(i).getShopName());
                intent.putExtra("image", detailList.get(i).getImage());
                intent.putExtra("time", detailList.get(i).getTime());
                context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));

            }
        });




        return v;
    }
}