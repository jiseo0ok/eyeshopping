package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class FragmentTwo extends Fragment {


    Context ct;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_two, container, false);
        ct = container.getContext();
        //처음 childfragment 지정
        getFragmentManager().beginTransaction().add(R.id.child_fragment, new ChildFragment1()).commit();

        GlobalApplication GG = (GlobalApplication) getActivity().getApplicationContext();

        //하위버튼
        ImageButton subButton1 = (ImageButton) v.findViewById(R.id.subButton1);
        ImageButton subButton2 = (ImageButton) v.findViewById(R.id.subButton2);
        ImageButton subButton3 = (ImageButton) v.findViewById(R.id.subButton3);
        ImageButton subButton4 = (ImageButton) v.findViewById(R.id.subButton4);
        TextView subButton11 = (TextView) v.findViewById(R.id.subButton11);
        TextView subButton22 = (TextView) v.findViewById(R.id.subButton22);
        TextView subButton33 = (TextView) v.findViewById(R.id.subButton33);
        TextView subButton44 = (TextView) v.findViewById(R.id.subButton44);

        //클릭 이벤트
        subButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment1()).commit();
                Toast.makeText(ct, GG.getGuserNickname()+"님이 찜한 상품", Toast.LENGTH_SHORT).show();

            }
        });
        subButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment1()).commit();
                Toast.makeText(ct, GG.getGuserNickname()+"님이 찜한 상품", Toast.LENGTH_SHORT).show();

            }
        });
        subButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment2()).commit();
                Toast.makeText(ct, "상의 리스트를 불러옵니다.", Toast.LENGTH_SHORT).show();

            }
        });
        subButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment2()).commit();
                Toast.makeText(ct, "상의 리스트를 불러옵니다.", Toast.LENGTH_SHORT).show();

            }
        });
        subButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment3()).commit();
                Toast.makeText(ct, "하의 리스트를 불러옵니다.", Toast.LENGTH_SHORT).show();

            }
        });
        subButton33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment3()).commit();
                Toast.makeText(ct, "하의 리스트를 불러옵니다.", Toast.LENGTH_SHORT).show();

            }
        });
        subButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment4()).commit();
                Toast.makeText(ct, "악세사리 리스트를 불러옵니다.", Toast.LENGTH_SHORT).show();

            }
        });
        subButton44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment4()).commit();
                Toast.makeText(ct, "악세사리 리스트를 불러옵니다.", Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }


}