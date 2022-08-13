package com.example.uasakbif110119023.fragment;
/*
Nama: Muhammad Farhan R
NIM : 10119023
Kelas : IF-1
tanggal : 12/08/2022
 */
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.uasakbif110119023.R;
import com.example.uasakbif110119023.db.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddCatatanFragment extends Fragment {

    EditText title, desc;
    Button addButton2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_catatan, container, false);

        title = view.findViewById(R.id.title2);
        desc = view.findViewById(R.id.description);
        addButton2 = view.findViewById(R.id.addButton2);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM, yyyy hh.mm aa", Locale.ENGLISH);
        Date date = new Date();
        final String modifiedDate = formatter.format(date);

        addButton2.setOnClickListener(view1 -> {
            DBHelper db = new DBHelper(getActivity());
            db.insertData(title.getText().toString().trim(), desc.getText().toString().trim(),
                    modifiedDate);
            getParentFragmentManager().popBackStack();
        });

        return view;
    }
}