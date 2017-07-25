package com.example.nikita.custom.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nikita.custom.Activity.MainActivity;
import com.example.nikita.custom.Model.AndroidVersions;
import com.example.nikita.custom.R;

import java.util.ArrayList;

/*** Created by nikita on 24/7/17.
 */

public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.ViewHolder> {
    private ArrayList<AndroidVersions> androidVersionsArrayList;
    private Context context;
    private final OnItemClickListener listener;


    public AndroidAdapter(Context context, ArrayList<AndroidVersions> android, OnItemClickListener listener) {
        this.context = context;
        this.androidVersionsArrayList = android;
        this.listener = listener;
        Log.d("Lenth", "@@" + androidVersionsArrayList.size());
    }



    @Override
    public AndroidAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AndroidAdapter.ViewHolder holder, int position) {
        AndroidVersions versions = androidVersionsArrayList.get(position);
        holder.tv_android.setText(versions.getAndroidVersionName());
        Glide.with(context).load(versions.getAndroidImageUrl())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img_android);
        holder.bind(androidVersionsArrayList.get(position), listener);


    }

    @Override
    public int getItemCount() {
        return androidVersionsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_android;
        private ImageView img_android;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_android = (TextView) itemView.findViewById(R.id.tv_android);
            img_android = (ImageView) itemView.findViewById(R.id.img_android);
        }

        public void bind(final AndroidVersions versions, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.onItemClick(versions);

                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(AndroidVersions versions);
    }
}
