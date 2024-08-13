package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter2 extends RecyclerView.Adapter<Adapter2ViewHolder> {
    private ArrayList<AdapterList2> items =new ArrayList<>();



    @NonNull
    @Override
    public Adapter2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.community_item, parent, false);

        Adapter2ViewHolder viewholder = new Adapter2ViewHolder(context, view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter2ViewHolder holder, int position) {
        AdapterList2 item = items.get(position);
//        String text = arrayList.get(position);
        //holder.title.setText(arrayList.getTitle());
        //holder.textView.setText(text);

        holder.hash.setText(item.getHash());
        holder.content.setText(item.getContent());
        holder.userID.setText(item.getUserID());
        holder.date.setText(item.getDate());
        Glide.with(holder.itemView.getContext()).load("http://182.31.216.12:1234/img/"+item.getImage()).into(holder.image1);
        //holder.image.setText(item.getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // 데이터를 입력
    public void setArrayData(AdapterList2 item) {
        items.add(item);
    }

}
