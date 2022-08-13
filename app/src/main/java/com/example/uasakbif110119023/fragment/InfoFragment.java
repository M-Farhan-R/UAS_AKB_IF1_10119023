package com.example.uasakbif110119023.fragment;
/*
Nama: Muhammad Farhan R
NIM : 10119023
Kelas : IF-1
tanggal : 13/08/2022
 */
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.uasakbif110119023.R;
import com.example.uasakbif110119023.adapter.VpAdapter;


public class InfoFragment extends Fragment {

    ViewPager vp;
    int[] layouts;
    VpAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        vp = view.findViewById(R.id.vp);
        layouts = new int[]{
                R.layout.open1,
                R.layout.open2
        };

        adapter = new VpAdapter(getContext(), layouts);
        vp.setAdapter(adapter);

        return view;
    }

//    ViewPager.OnPageChangeListener viewPagerChangeListener = new ViewPager.OnPageChangeListener() {
//        @Override
//        public void onPageScrolled(int i, float v, int i1) {
//
//        }
//
//        @Override
//        public void onPageSelected(int i) {
//            if(i == layouts.length -1 ){
//                btn_vp.setText("Mulai");
//            }else {
//                btn_vp.setText("Lanjut");
//            }
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//
//        }
//    };
}