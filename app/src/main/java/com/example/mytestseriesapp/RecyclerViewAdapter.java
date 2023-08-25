package com.example.mytestseriesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Viewholder> {

    Context context;
    ArrayList<RecycleViewModal> arrModal;
    RecyclerViewAdapter(Context context, ArrayList<RecycleViewModal> arrModal){
        this.context = context;
        this.arrModal = arrModal;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycleview_row, parent, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.sno.setText(arrModal.get(position).index);
        holder.testName.setText(arrModal.get(position).data);
    }

    @Override
    public int getItemCount() {
        return arrModal.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView sno, testName;
        ImageButton startTest;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            sno = itemView.findViewById(R.id.sno);
            testName = itemView.findViewById(R.id.testName);
            startTest = itemView.findViewById(R.id.startTest);
        }
    }
}
