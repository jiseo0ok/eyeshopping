package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class testAdapter extends BaseAdapter {



    private ArrayList<testdata> list =  new ArrayList<>();
    public testAdapter(){
        list.add(new testdata(R.drawable.man, "kmk"));
        list.add(new testdata(R.drawable.man, "kmk"));
        list.add(new testdata(R.drawable.man, "kmk"));
        list.add(new testdata(R.drawable.man, "kmk"));
        list.add(new testdata(R.drawable.man, "kmk"));
        list.add(new testdata(R.drawable.man, "kmk"));
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    // 상속 받으면서 자동 추가라 쓸거면 쓰고

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());

        View itemView=inflater.inflate(R.layout.listviewitem,viewGroup,false); //커스텀 한거 가지고옴

        TextView textV=itemView.findViewById(R.id.textV);
        ImageView imgV=itemView.findViewById(R.id.imgV);

        String str=((testdata)getItem(i)).getStr();
        int img = ((testdata)getItem(i)).getImage();


        textV.setText(str);
        imgV.setImageResource(img);

        return itemView;

    }
}
