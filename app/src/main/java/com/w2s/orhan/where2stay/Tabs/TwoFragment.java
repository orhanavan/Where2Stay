package com.w2s.orhan.where2stay.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.w2s.orhan.where2stay.Advert.Upload;
import com.w2s.orhan.where2stay.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TwoFragment extends Fragment{

    public RecyclerView mRecyclerView;
    public ImageAdapter mAdapter;
    public DatabaseReference mDatabaseRef;
    public List<Upload> mUploads;

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        mUploads = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Posts").child("satılık");
        mDatabaseRef.keepSynced(true);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();
                    String s1 = hashMap.get("title");
                    String s2 = hashMap.get("cost");
                    String s3 = hashMap.get("downloadurl");
                    String s4 = ds.getKey();

                    Upload upload = new Upload(s1, s2, s3, s4);

                    mUploads.add(upload);

                }

                mAdapter = new ImageAdapter(getActivity(), mUploads);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
