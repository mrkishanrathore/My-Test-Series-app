package com.example.mytestseriesapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    Button test ;
    Button studyMaterial ;
//    RecyclerView_Adapter adapter;
    RecyclerView recyclerView;
    ImageButton leftSwipe, rightSwipe;

    String data;

    CustomFlipper customFlipper;

    ArrayList<RecycleViewModal> rvmArr = new ArrayList<>();
    RecycleViewModal rvm;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        test = view.findViewById(R.id.testBtn);
        studyMaterial = view.findViewById(R.id.studyMaterialBtn);
        customFlipper = view.findViewById(R.id.flipper);
        recyclerView = view.findViewById(R.id.recycleView);
        leftSwipe = view.findViewById(R.id.rightSwipeButton);
        rightSwipe = view.findViewById(R.id.leftSwipeButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        leftSwipe.setOnClickListener(view1 -> customFlipper.showPrevious());

        rightSwipe.setOnClickListener(view12 -> customFlipper.showNext());

        for (int i = 1; i <= 20; i++) {
            data = "Test Series "+i;
            rvm = new RecycleViewModal(String.valueOf(i),data);
            rvmArr.add(rvm);
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(),rvmArr);
        recyclerView.setAdapter(adapter);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Test is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        customFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        return view;
    }
}