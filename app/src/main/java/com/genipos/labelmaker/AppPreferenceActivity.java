package com.genipos.labelmaker;


import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class AppPreferenceActivity extends PreferenceActivity{
	
	OnSharedPreferenceChangeListener listener;
	 //AppEnv gAppEnv;
	 
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

       //gAppEnv = (AppEnv) getApplicationContext();
       /*SharedPreferences prefs = this.getSharedPreferences("appmode_preference", 0);
       listener = new SharedPreferences.OnSharedPreferenceChangeListener() {

           public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
               //int flag = 1;
               gAppEnv.gAppSettings.updateAppmode();
           }
       };
       prefs.registerOnSharedPreferenceChangeListener(listener);
       
   */
       addPreferencesFromResource(R.xml.app_preferences);
   }

}
