package com.w2s.orhan.where2stay.Tabs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.w2s.orhan.where2stay.Advert.DetailedActivity;
import com.w2s.orhan.where2stay.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Upload> mUploads;

    public ImageAdapter(Context mContext, List<Upload> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.content_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        final Upload upload = mUploads.get(position);
        holder.textViewTitle.setText(upload.getTitle());
        holder.textViewCost.setText(upload.getCost());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailedActivity.class);
                intent.putExtra("advertID", upload.getAdvertID());
                mContext.startActivity(intent);
            }
        });
        Picasso.with(mContext)
                .load(upload.getImageURL())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle;
        public TextView textViewCost;
        public ImageView imageView;
        CardView cardView;

        public ImageViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.tv_title_item);
            textViewCost = itemView.findViewById(R.id.tv_cost_item);
            imageView = itemView.findViewById(R.id.iv_item);
            cardView = itemView.findViewById(R.id.content_item);
        }
    }
}
