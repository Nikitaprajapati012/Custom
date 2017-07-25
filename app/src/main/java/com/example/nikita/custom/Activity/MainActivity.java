package com.example.nikita.custom.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.nikita.custom.Adapter.AndroidAdapter;
import com.example.nikita.custom.Adapter.MainViewPager;
import com.example.nikita.custom.Model.AndroidVersions;
import com.example.nikita.custom.R;

import java.util.ArrayList;

import com.example.nikita.custom.Interface.OnItemClickListener;
import com.example.nikita.custom.Slide;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    public OnItemClickListener listener;
    public AndroidVersions versions;
    public MainViewPager viewPager;


    private final String android_version_names[] = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"
    };

    private final String android_image_urls[] = {
            "https://api.learn2crack.com/android/images/donut.png",
            "https://api.learn2crack.com/android/images/eclair.png",
            "https://api.learn2crack.com/android/images/froyo.png",
            "https://api.learn2crack.com/android/images/ginger.png",
            "https://api.learn2crack.com/android/images/honey.png",
            "https://api.learn2crack.com/android/images/icecream.png",
            "https://api.learn2crack.com/android/images/jellybean.png",
            "https://api.learn2crack.com/android/images/kitkat.png",
            "https://api.learn2crack.com/android/images/lollipop.png",
            "https://api.learn2crack.com/android/images/marshmallow.png"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilizeTheAdaptertwithRecyclerview();
    }

    private void initilizeTheAdaptertwithRecyclerview() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager
                (getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        final ArrayList<AndroidVersions> androidVersions = prepareData();
        recyclerView.setAdapter(
                new AndroidAdapter(MainActivity.this, androidVersions, new AndroidAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(AndroidVersions versions) {
                        Toast.makeText(MainActivity.this, "" + versions.getAndroidVersionName(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Slide.class);
                        Gson gson = new Gson();
                        intent.putExtra("image", gson.toJson(androidVersions));
                        intent.setDataAndType(Uri.parse(versions.getAndroidImageUrl()), "image/*");
                        startActivity(intent);
                    }
                }));

    }

    private ArrayList<AndroidVersions> prepareData() {
        ArrayList<AndroidVersions> android_version = new ArrayList<>();
        for (int i = 0; i < android_version_names.length; i++) {
            AndroidVersions androidVersion = new AndroidVersions();
            androidVersion.setAndroidVersionName(android_version_names[i]);
            androidVersion.setAndroidImageUrl(android_image_urls[i]);
            android_version.add(androidVersion);
        }
        return android_version;
    }
}
