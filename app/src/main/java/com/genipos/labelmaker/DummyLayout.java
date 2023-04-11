package com.genipos.labelmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.genipos.labelmaker.common.SharePerfer;
import com.genipos.labelmaker.model.PrintSetting;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class DummyLayout extends AppCompatActivity {
    SharePerfer sharedPreferences;
    ArrayList<SharePerfer.AllTogether> togetherArrayList;
    ArrayList<PrintSetting> arrayListBar, arrayListData;
    //
    LinearLayout relativeLayout;
    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_layout);
        sharedPreferences = new SharePerfer(this);
        togetherArrayList = sharedPreferences.getFormate();
        Intent intent = getIntent();
        int i = intent.getIntExtra("position", -1);
        init();
        if (i == -1){
            //do nothing
        }else{
            arrayListBar = togetherArrayList.get(i).arrayListB;
            arrayListData = togetherArrayList.get(i).arrayListD;
            System.out.println(arrayListBar.size() + "/" + arrayListData.size());
            final View viewk = getLayoutInflater().inflate(R.layout.dummy_layout_data, null, false);
            textInputLayout = (TextInputLayout) viewk.findViewById(R.id.dummyInputLayout);
            textInputEditText = (TextInputEditText) viewk.findViewById(R.id.edtdummy);
            for (int j = 0; j < arrayListBar.size(); j++){
                relativeLayout.addView(viewk);
            }
//            for (int k = 0; k < arrayListData.size(); k++){
//                relativeLayout.addView(viewk);
//            }
        }

    }

    private void init() {
        relativeLayout = (LinearLayout) findViewById(R.id.relativeLayout);
    }
}