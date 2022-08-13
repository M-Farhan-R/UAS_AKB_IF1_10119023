package com.example.uasakbif110119023.fragment;
/*
Nama: Muhammad Farhan R
NIM : 10119023
Kelas : IF-1
tanggal : 13/08/2022
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

public class UpdateCatatanFragment extends Fragment {

    EditText judul, desc;
    Button btn_update, btn_delete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_catatan, container, false);

        judul = view.findViewById(R.id.title2U);
        desc = view. findViewById(R.id.descriptionU);
        btn_update = view.findViewById(R.id.updateButton);
        btn_delete = view.findViewById(R.id.deleteButton);

        judul.setText(getArguments().getString("judul"));
        desc.setText(getArguments().getString("desc"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM, yyyy hh.mm aa", Locale.ENGLISH);
        Date date = new Date();
        final String modifiedDate = formatter.format(date);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(getActivity());
                db.updateData(getArguments().getString("id"), judul.getText().toString().trim(), desc.getText().toString().trim(),
                        modifiedDate);
                getParentFragmentManager().popBackStack();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete Confirmation")
                        .setMessage("Yakin ingin menghapus catatan ini?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBHelper db = new DBHelper(getActivity());
                                db.deleteData(getArguments().getString("id"));
                                getParentFragmentManager().popBackStack();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        return view;
    }

    void setData(){

    }
}