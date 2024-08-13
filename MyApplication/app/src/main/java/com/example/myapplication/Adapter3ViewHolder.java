package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

class Adapter3ViewHolder extends RecyclerView.ViewHolder {

    public ImageView image1;

    public TextView shopId,shopName,shopAddress,shopHash,shopUrl,shopContext,shopCall;

    Adapter3ViewHolder(Context context, View itemView) {
        super(itemView);
        shopId = itemView.findViewById(R.id.shopId);
       // image = itemView.findViewById(R.id.image);
        shopName = itemView.findViewById(R.id.shopName);
        shopAddress = itemView.findViewById(R.id.shopAddress);
        //No = itemView.findViewById(R.id.No);
        shopUrl = itemView.findViewById(R.id.shopUrl);
        shopHash = itemView.findViewById(R.id.shopHash);
        shopCall = itemView.findViewById(R.id.shopCall);
        image1 = itemView.findViewById(R.id.image1);
        shopContext = itemView.findViewById(R.id.shopContext);
        image1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               Intent intent = new Intent(context,DetailActivity.class);

               intent.putExtra("shopId", shopId.getText().toString());
               intent.putExtra("shopName", shopName.getText().toString());
               intent.putExtra("shopAddress", shopAddress.getText().toString());
               intent.putExtra("shopUrl", shopUrl.getText().toString());
               intent.putExtra("shopHash", shopHash.getText().toString());
               intent.putExtra("shopCall", shopCall.getText().toString());
               intent.putExtra("shopContext", shopContext.getText().toString());
               intent.putExtra("photo", "http://182.31.216.12:1234/img/"+shopUrl.getText().toString());
               intent.putExtra("c",shopId.getText().toString());
               Toast.makeText(context.getApplicationContext(), shopName.getText().toString()+"입니다.",Toast.LENGTH_SHORT).show();
//                Toast.makeText(context.getApplicationContext(), a,Toast.LENGTH_SHORT).show();

               //(Activity)context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                context.startActivity(intent);
               ((Activity) context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);



           }
       });



    }


}
