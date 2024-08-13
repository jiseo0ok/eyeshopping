package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter1 extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<detailList> items =new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dlist1, parent, false);

        ViewHolder viewholder = new ViewHolder(context, view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    detailList item = items.get(position);
//        String text = arrayList.get(position);
        //holder.title.setText(arrayList.getTitle());
        //holder.textView.setText(text);
        Glide.with(holder.itemView.getContext()).load("http://182.31.216.12:1234/img/"+item.getImage()).into(holder.image1);
        holder.title.setText(item.getTitle());
        holder.content.setText(item.getContent());
        holder.contentNo.setText(item.getContentNo());
        holder.userID.setText(item.getUserID());
        holder.time.setText(item.getTime());
        holder.image.setText(item.getImage());
        holder.shopName.setText(item.getShopName());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // 데이터를 입력
    public void setArrayData(detailList item) {
        items.add(item);
    }

}
