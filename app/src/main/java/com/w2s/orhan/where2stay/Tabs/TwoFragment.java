package com.w2s.orhan.where2stay.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.w2s.orhan.where2stay.R;

public class TwoFragment extends Fragment{

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("orhan33", "frag2 oluşturuldu");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("orhan33", "frag2 gösteriliyor");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("orhan33", "frag2 yok edildi");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("orhan33", "frag2 başladı");
    }

}
