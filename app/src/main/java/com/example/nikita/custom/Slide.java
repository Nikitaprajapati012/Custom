package com.example.nikita.custom;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.nikita.custom.Adapter.AndroidAdapter;
import com.example.nikita.custom.Adapter.MainViewPager;
import com.example.nikita.custom.Model.AndroidVersions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Slide extends AppCompatActivity {
    AndroidVersions androidVersions;
    ArrayList<AndroidVersions> androidVersionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        if (getIntent().getExtras() != null) {
            String str = getIntent().getExtras().getString("image");
            Gson gson = new Gson();
            androidVersionsList = gson.fromJson(str, new TypeToken<ArrayList<AndroidVersions>>() {
            }.getType());
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MainViewPager(this, androidVersionsList));
    }
}
