package com.genipos.labelmaker.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.genipos.labelmaker.model.PrintSetting;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharePerfer {
    Context mContext;
    SharedPreferences sharedPreferences;
    SharedPreferences.OnSharedPreferenceChangeListener listener;

    public SharePerfer(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    //save height
    public boolean setHeight(String height) {
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("height", height);
        editor.commit();
        return true;
    }
    public String getHeight() {
        return sharedPreferences.getString("height", "");
    }
    //save width
    public boolean setWidth(String width) {
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("width", width);
        editor.commit();
        return true;
    }
    public String getWidth() {
        return sharedPreferences.getString("width", "");
    }

    //save formate
    //formate
    public class AllTogether{
        public String height, width;
        public ArrayList<PrintSetting> arrayListB, arrayListD;
        public AllTogether(String height, String width, ArrayList<PrintSetting> arrayListB, ArrayList<PrintSetting> arrayListD) {
            this.height = height;
            this.width = width;
            this.arrayListB = arrayListB;
            this.arrayListD = arrayListD;
        }
    }
    public boolean setFormate(String height, String width, ArrayList<PrintSetting> arrayListBar, ArrayList<PrintSetting> arrayListdata){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        List<AllTogether> allTogetherList = new ArrayList<AllTogether>();
        allTogetherList = getFormate();
        allTogetherList.add(new AllTogether(height, width, arrayListBar, arrayListdata));
        Gson gson = new Gson();
        String json = gson.toJson(allTogetherList);
        editor.putString("formate", json);
        editor.commit();
        return true;
    }
    //test
    public String getTestFormate(){
        return sharedPreferences.getString("formate", "");
    }
    public ArrayList<AllTogether> getFormate(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString("formate", "");
        Type type = new TypeToken<List<AllTogether>>(){}.getType();
        return gson.fromJson(json,type);
    }

}
