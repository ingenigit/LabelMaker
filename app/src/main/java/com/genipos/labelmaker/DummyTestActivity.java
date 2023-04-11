package com.genipos.labelmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.genipos.labelmaker.adapter.DummyAdapter;
import com.genipos.labelmaker.common.SharePerfer;

import java.util.ArrayList;

public class DummyTestActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SharePerfer sharedPreferences;
    ArrayList<SharePerfer.AllTogether> arrayList;
    DummyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_test);
        sharedPreferences = new SharePerfer(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = sharedPreferences.getFormate();
        String stringF = sharedPreferences.getTestFormate();
        adapter = new DummyAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        System.out.println(stringF + "KKMLKML "+ arrayList.size()  +" "+ arrayList.get(0).height + arrayList.get(1).height);
    }
}