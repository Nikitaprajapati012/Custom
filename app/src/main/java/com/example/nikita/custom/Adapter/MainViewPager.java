package com.example.nikita.custom.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nikita.custom.Model.AndroidVersions;
import com.example.nikita.custom.R;
import com.example.nikita.custom.Slide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by nikita on 24/7/17.
 */

public class MainViewPager extends PagerAdapter {
    ArrayList<AndroidVersions> arrayList;
    Context mContext;
    String string;

    public MainViewPager(Context context, ArrayList<AndroidVersions> arrayList) {
        this.mContext = context;
        this.arrayList = arrayList;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    public Object instantiateItem(ViewGroup collection, final int position) {
        AndroidVersions versions = arrayList.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.pager_item, collection, false);
        ImageView imageView = (ImageView) layout.findViewById(R.id.imageView);
        Picasso.with(mContext).load(versions.getAndroidImageUrl()).into(imageView);

        collection.addView(layout);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "you clicked image is" + (position + 1), Toast.LENGTH_LONG).show();
            }
        });
        return layout;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        AndroidVersions modelObject = arrayList.get(position);
        return mContext.getString(Integer.parseInt(modelObject.getAndroidImageUrl()));
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((LinearLayout) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }
}
