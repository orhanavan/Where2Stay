package com.w2s.orhan.where2stay.Chat;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.w2s.orhan.where2stay.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conversation extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    EditText chatView;
    Button sendButton;
    String chatUserID;
    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    private String mCurrentUserID;
    private RecyclerView mMessagesList;
    private final List<Messages> messagesList = new ArrayList<>();
    private LinearLayoutManager mLinearLayout;
    private MessageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        chatView = findViewById(R.id.chatView);
        sendButton = findViewById(R.id.sendButton);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        chatUserID = intent.getStringExtra("user_id");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(chatUserID);
        }

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mCurrentUserID = mAuth.getCurrentUser().getUid();

        mAdapter = new MessageAdapter(messagesList);

        mMessagesList = findViewById(R.id.messages_list);
        mLinearLayout = new LinearLayoutManager(this);
        mMessagesList.setHasFixedSize(true);
        mMessagesList.setLayoutManager(mLinearLayout);

        mMessagesList.setAdapter(mAdapter);
        
        loadMessages();

    }

    private void loadMessages() {
        mRootRef.child("messages").child(mCurrentUserID).child(chatUserID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Messages message = dataSnapshot.getValue(Messages.class);
                messagesList.add(message);
                mAdapter.notifyDataSetChanged();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view) {
        String message = chatView.getText().toString();
        if (!TextUtils.isEmpty(message)) {
            String current_user_ref = "messages/" + mCurrentUserID + "/" + chatUserID;
            String chat_user_ref    = "messages/" + chatUserID + "/" + mCurrentUserID;

            DatabaseReference user_message_push = mRootRef.child("messages").child(mCurrentUserID).child(chatUserID).push();
            String push_id = user_message_push.getKey();

            Map messageMap = new HashMap();
            messageMap.put("time", ServerValue.TIMESTAMP);
            messageMap.put("message", message);
            messageMap.put("from", mCurrentUserID);


            Map messageUserMap = new HashMap();
            messageUserMap.put(current_user_ref + "/" + push_id, messageMap);
            messageUserMap.put(chat_user_ref + "/" + push_id, messageMap);

            mRootRef.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                }
            });
        }

        chatView.setText("");
    }
}
