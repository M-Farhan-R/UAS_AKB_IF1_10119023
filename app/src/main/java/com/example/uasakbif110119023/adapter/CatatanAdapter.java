package com.example.uasakbif110119023.adapter;
/*
Nama: Muhammad Farhan R
NIM : 10119023
Kelas : IF-1
tanggal : 12/08/2022
 */
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasakbif110119023.R;
import com.example.uasakbif110119023.fragment.AddCatatanFragment;
import com.example.uasakbif110119023.fragment.CatatanFragment;
import com.example.uasakbif110119023.fragment.UpdateCatatanFragment;

import java.util.ArrayList;

public class CatatanAdapter extends RecyclerView.Adapter<CatatanAdapter.CatatanViewHolder> {

    Context context;
    ArrayList id,judul,desc,date;



    public CatatanAdapter(Context context, ArrayList id, ArrayList judul, ArrayList desc, ArrayList date) {
        this.context = context;
        this.id = id;
        this.judul = judul;
        this.desc = desc;
        this.date = date;
    }

    @NonNull
    @Override
    public CatatanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.catatan_layout, parent, false);
        return  new CatatanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatatanViewHolder holder, int position) {
        holder.txt_judul.setText(String.valueOf(judul.get(position)));
        holder.txt_desc.setText(String.valueOf(desc.get(position)));
        holder.txt_date.setText(String.valueOf(date.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateCatatanFragment frag = new UpdateCatatanFragment();
                Bundle data = new Bundle();
                data.putString("id",id.get(holder.getAdapterPosition()).toString());
                data.putString("judul",judul.get(holder.getAdapterPosition()).toString());
                data.putString("desc",desc.get(holder.getAdapterPosition()).toString());
                frag.setArguments(data);
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        frag).addToBackStack("tag").commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return judul.size();
    }


    public class CatatanViewHolder extends RecyclerView.ViewHolder {

        TextView txt_judul, txt_desc, txt_date;
        RelativeLayout catatanLayout;

        public CatatanViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_judul = itemView.findViewById(R.id.txt_judul);
            txt_desc = itemView.findViewById(R.id.txt_desc);
            txt_date = itemView.findViewById(R.id.date);
            catatanLayout = itemView.findViewById(R.id.catatanLayout);
        }
    }
}
