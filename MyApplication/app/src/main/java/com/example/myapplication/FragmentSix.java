package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class FragmentSix extends Fragment {
    FragmentTransaction ft;
    FragmentManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    Context ct;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_six, container, false);
        ct = container.getContext();
        GlobalApplication GG = (GlobalApplication) getActivity().getApplicationContext();
        manager = getFragmentManager();
        ft = manager.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        ft.addToBackStack(null);
        ft.add(R.id.child_fragment, new ChildFragment61());
        ft.commit();
        //getFragmentManager().beginTransaction().add(R.id.child_fragment, new ChildFragment61()).commit();
        ImageButton subButton1 = (ImageButton) v.findViewById(R.id.subButton1);
        ImageButton subButton2 = (ImageButton) v.findViewById(R.id.subButton2);

        TextView subButton11 = (TextView) v.findViewById(R.id.subButton11);
        TextView subButton22 = (TextView) v.findViewById(R.id.subButton22);

        subButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft = manager.beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
                ft.addToBackStack(null);
                ft.replace(R.id.child_fragment, new ChildFragment61());

                ft.commit();
                //getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment61()).commit();
                // ((Activity) ct).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });
        subButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft = manager.beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
                ft.addToBackStack(null);
                ft.replace(R.id.child_fragment, new ChildFragment61());

                ft.commit();
                //getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment61()).commit();
                // ((Activity) ct).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });
        subButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft = manager.beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
                ft.addToBackStack(null);
                ft.replace(R.id.child_fragment, new ChildFragment62());

                ft.commit();
                //getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment52()).commit();
                //((Activity) ct).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });
        subButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft = manager.beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
                ft.addToBackStack(null);
                ft.replace(R.id.child_fragment, new ChildFragment62());

                ft.commit();
                //getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment62()).commit();
                //((Activity) ct).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}