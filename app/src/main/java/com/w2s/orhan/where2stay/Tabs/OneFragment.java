package com.w2s.orhan.where2stay.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.w2s.orhan.where2stay.MainActivity;
import com.w2s.orhan.where2stay.R;

import java.util.ArrayList;
import java.util.List;


public class OneFragment extends Fragment{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private List<Person> personList;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("orhan33", "frag1 oluşturuldu");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        personList = new ArrayList<>();
        for (int index=0 ; index < 15 ; index++){
            Person myPerson = new Person("orhan"+index,"programmer");
            personList.add(myPerson);
        }

        recyclerViewAdapter = new MyRecyclerViewAdapter(personList, getContext());
        recyclerView.setAdapter(recyclerViewAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("orhan33", "frag1 yok edildi");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("orhan33", "frag1 başladı");
    }
}
