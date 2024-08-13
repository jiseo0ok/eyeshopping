package com.example.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;



class ViewHolder extends RecyclerView.ViewHolder {

    Activity activity;
    public TextView image;
    public ImageButton image1;
    public TextView contentNo;
    public TextView shopName;
    public TextView userID;
    public TextView content;
    public TextView time;
    public TextView title;

    ViewHolder(Context context, View itemView) {
        super(itemView);


        userID = itemView.findViewById(R.id.userID);
        image = itemView.findViewById(R.id.image);
        title = itemView.findViewById(R.id.title);
        content = itemView.findViewById(R.id.content);
        contentNo = itemView.findViewById(R.id.contentNo);
        shopName = itemView.findViewById(R.id.shopName);
        time = itemView.findViewById(R.id.time);
        image1 = itemView.findViewById(R.id.image1);


        image1.setOnClickListener(new View.OnClickListener() {
            public Activity mContext;

            @Override
            public void onClick(View v) {
                String strText = title.getText().toString();
                Toast.makeText(context, title.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, show.class);
                intent.putExtra("title", title.getText().toString());
                intent.putExtra("image", image.getText().toString());
                intent.putExtra("userID", userID.getText().toString());
                intent.putExtra("content", content.getText().toString());
                intent.putExtra("contentNo", contentNo.getText().toString());
                intent.putExtra("shopName", shopName.getText().toString());
                intent.putExtra("time", time.getText().toString());

                //(Activity)context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
               // ((Activity) context).finish();

               // mContext = (Activity) ViewHolder.itemView.getContext();
                //mContext.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
               // v.getApplicationContext()

            }
        });
    }

}
