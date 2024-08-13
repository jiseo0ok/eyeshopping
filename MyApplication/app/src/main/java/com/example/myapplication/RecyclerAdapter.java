package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    // context.
    private Context context;
    // 데이터를 받아올 ArrayList
    private ArrayList<detailList> detailList;

    // Adapter 생성자
    public RecyclerAdapter(Context context, ArrayList<detailList> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 셀 레이아웃을 불러오는 역할.
        View view = LayoutInflater.from(context).inflate(R.layout.dlist, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, @SuppressLint("RecyclerView") int position1) {
        // cell의 모든 View에 데이터를 알맞게 넣어준다.
        //holder.image.setText(detailList.get(position).image);
        holder.title.setText(detailList.get(position1).title);
        holder.image.setText("http://182.31.216.12:1234/img/"+detailList.get(position1).image);
        String s1 = "http://182.31.216.12:1234/img/"+detailList.get(position1).image;
        Glide.with(context).load(s1).into(holder.image1);



        // 각 셀을 클릭 시 작업
        holder.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 여기서는 각 셀을 클릭 시 셀에 해당하는 이름 데이터를 Toast message로 띄운다.
                Toast.makeText(context, "Clicked "+detailList.get(position1).title, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // RecyclerView의 아이템 갯수를 반환하는 함수.
    @Override
    public int getItemCount() {
        if(detailList == null)
            return 0;
        else
            return detailList.size();
    }

    // cell에 대한 ViewHolder
    public static class Holder extends RecyclerView.ViewHolder {
        protected ConstraintLayout clCell;
        protected ImageButton image1;
        protected TextView image;
        protected TextView title;

        public Holder(View view) {
            super(view);
            this.title = view.findViewById(R.id.title);
            this.image = view.findViewById(R.id.image);
            this.image1 = view.findViewById(R.id.image1);

        }
    }
}