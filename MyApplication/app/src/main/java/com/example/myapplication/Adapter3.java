package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter3 extends RecyclerView.Adapter<Adapter3ViewHolder>  {
    private ArrayList<Adapter3List> items =new ArrayList<>();



    @NonNull
    @Override
    public Adapter3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.shoplist, parent, false);

        Adapter3ViewHolder viewholder = new Adapter3ViewHolder(context, view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter3ViewHolder holder, int position) {
        Adapter3List item = items.get(position);
//        String text = arrayList.get(position);
        //holder.title.setText(arrayList.getTitle());
        //holder.textView.setText(text);

        holder.shopId.setText(item.getShopId());
        holder.shopName.setText(item.getShopName());
        holder.shopAddress.setText(item.getShopAddress());
        holder.shopHash.setText(item.getShopHash());
        holder.shopContext.setText(item.getShopContext());
        holder.shopCall.setText(item.getShopCall());
        holder.shopUrl.setText(item.getShopUrl());
        Glide.with(holder.itemView.getContext()).load("http://182.31.216.12:1234/img/"+item.getShopUrl()).into(holder.image1);
        //holder.image.setText(item.getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    // 데이터를 입력
   /* public void setArrayData(Adapter3List item) {
        items.add(item);
        notifyDataSetChanged();
    }*/
    public void setArrayData(ArrayList<Adapter3List> item) {
        items = item;
        notifyDataSetChanged();
    }
    public void setItems(ArrayList<Adapter3List> list){
        items = list;
        notifyDataSetChanged();
    }

}
