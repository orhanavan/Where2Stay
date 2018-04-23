package com.w2s.orhan.where2stay.Chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.w2s.orhan.where2stay.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    private UserListAdapter mUserAdapter;
    public List<ChatList> mUsers = new ArrayList<>();
    private String mCurrentUserID;
    public RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Chat");
        }

        mRecyclerView = findViewById(R.id.chat_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAuth = FirebaseAuth.getInstance();
        mCurrentUserID = mAuth.getCurrentUser().getUid();

        mUserAdapter = new UserListAdapter(mUsers);
        mRecyclerView.setAdapter(mUserAdapter);

        mRootRef = FirebaseDatabase.getInstance().getReference("messages").child(mCurrentUserID);
        mRootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Log.e("orhan44",dataSnapshot.toString() );
                String s4 = dataSnapshot.getKey().toString();
                ChatList chatList = new ChatList(s4);
                mUsers.add(chatList);

                mUserAdapter = new UserListAdapter(mUsers);
                mRecyclerView.setAdapter(mUserAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
