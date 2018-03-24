package com.w2s.orhan.where2stay.Tabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.w2s.orhan.where2stay.R;

public class FourFragment extends Fragment{

    public FourFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("orhan33", "frag4 oluşturuldu");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("orhan33", "frag4 gösteriliyor");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_four, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("orhan33", "frag4 yok edildi");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("orhan33", "frag4 başladı");
    }

}