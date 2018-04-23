package com.w2s.orhan.where2stay.Chat;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.w2s.orhan.where2stay.R;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private List<ChatList> mUsers;

    public UserListAdapter( List<ChatList> mUsers) {
        this.mUsers = mUsers;
    }

    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new UserViewHolder(v);

    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView username;

        public UserViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.chat_username);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final ChatList chatList = mUsers.get(position);
        holder.username.setText(chatList.getUsername());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
