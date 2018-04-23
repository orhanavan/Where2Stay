package com.w2s.orhan.where2stay.Chat;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.w2s.orhan.where2stay.R;

import java.security.PublicKey;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Messages> mMessageList;

    public MessageAdapter(List<Messages> mMessageList) {
        this.mMessageList = mMessageList;
    }

    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new MessageViewHolder(v);
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{
        public TextView messageText;

        public MessageViewHolder(View view) {
            super(view);

            messageText = view.findViewById(R.id.msg_item_text);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

            Messages c = mMessageList.get(position);


            holder.messageText.setBackgroundResource(R.drawable.message_text_theme_to);

            holder.messageText.setText(c.getMessage());


    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }
}
