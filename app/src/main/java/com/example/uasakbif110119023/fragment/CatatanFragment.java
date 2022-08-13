package com.example.uasakbif110119023.fragment;
/*
Nama: Muhammad Farhan R
NIM : 10119023
Kelas : IF-1
tanggal : 12/08/2022
 */
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasakbif110119023.adapter.CatatanAdapter;
import com.example.uasakbif110119023.db.DBHelper;
import com.example.uasakbif110119023.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CatatanFragment extends Fragment {

    FloatingActionButton addButton;
    RecyclerView recyclerView;

    DBHelper db;
    ArrayList<String> id, judul, desc, date;

    CatatanAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_catatan, container, false);

        recyclerView = view.findViewById(R.id.recycleView);
        addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view){
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddCatatanFragment()).addToBackStack("tag").commit();
            }
        });

        db = new DBHelper(getContext());
        id = new ArrayList<>();
        judul = new ArrayList<>();
        desc = new ArrayList<>();
        date = new ArrayList<>();
        displayData();

        adapter = new CatatanAdapter(getContext(),id, judul, desc, date);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    void displayData(){
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                judul.add(cursor.getString(1));
                desc.add(cursor.getString(2));
                date.add(cursor.getString(3));
            }
        }
    }


}