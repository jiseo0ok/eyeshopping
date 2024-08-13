package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter61 extends RecyclerView.Adapter<Adapter61ViewHolder>  {
    private ArrayList<user> items =new ArrayList<>();



    @NonNull
    @Override
    public Adapter61ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.user, parent, false);

        Adapter61ViewHolder viewholder = new Adapter61ViewHolder(context, view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter61ViewHolder holder, int position) {
        user item = items.get(position);
//        String text = arrayList.get(position);
        //holder.title.setText(arrayList.getTitle());
        //holder.textView.setText(text);

        holder.userID.setText(item.getUserID());
        holder.userPassword.setText(item.getUserPassword());
        holder.userName.setText(item.getUserName());
        holder.userNickname.setText(item.getUserNickname());
        holder.Grade.setText(item.getGrade());
        holder.deleteButton.setText(item.getUserID()+"삭제");


        //holder.image.setText(item.getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    // 데이터를 입력
   /* public void setArrayData(user item) {
        items.add(item);
        notifyDataSetChanged();
    }*/
    public void setArrayData(ArrayList<user> item) {
        items = item;
        notifyDataSetChanged();
    }
    public void setItems(ArrayList<user> list){
        items = list;
        notifyDataSetChanged();
    }

}
