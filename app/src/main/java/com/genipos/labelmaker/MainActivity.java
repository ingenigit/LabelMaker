package com.genipos.labelmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AppEnv gAppEnv;
    Context mContext;
    Menu mMenu = null;

    private ListView listview;
    ArrayList<String> menuList = new ArrayList<String>() {{
        add("Manage Products");
        add("Print Label");
        add("Connect Printer");
        add("Settings");
        add("Import Product Database");
        add("History");
        //add("Configure Printer");
    }};//new ArrayList <String>{"Connect Printer", "Print Label", "Configure Printer"};
    private ArrayAdapter<String> listAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        gAppEnv = (AppEnv) getApplicationContext();
        gAppEnv.Init();

        mContext = this;

        listview = (ListView)findViewById(R.id.productmanagerlistview);


        listAdapter = new ArrayAdapter<String>(this, R.layout.menu_list_row, menuList);
        listview.setAdapter(listAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0)
                {
                    //Intent connactivity = new Intent(MainActivity.this, ProductsActivity.class);
                    /// itemdetailsactivity.putExtra("reportmonth", repmonth);//, value)("orderid", test[0]);
                    //startActivity(connactivity);
                }
                else if (position == 1)
                {
                    Intent connactivity = new Intent(MainActivity.this, PrintLabelActivity.class);
                    /// itemdetailsactivity.putExtra("reportmonth", repmonth);//, value)("orderid", test[0]);
                    startActivity(connactivity);
                }
                else if (position == 2)
                {
                    Intent connactivity = new Intent(MainActivity.this, PrinterConnectActivity.class);
                    /// itemdetailsactivity.putExtra("reportmonth", repmonth);//, value)("orderid", test[0]);
                    startActivity(connactivity);
                }
                else if (position == 3)
                {
                    Intent connactivity = new Intent(MainActivity.this, SettingActivity.class);
                    startActivity(connactivity);
//                    Intent connactivity = new Intent(MainActivity.this, AppPreferenceActivity.class);
//                    startActivity(connactivity);

                }
                else if (position == 4)
                {
                    startActivity(new Intent(MainActivity.this, DummyTestActivity.class));
                }
                else if (position == 5)
                {
                    Intent connactivity = new Intent(MainActivity.this, HistoryActivity.class);
                    startActivity(connactivity);
                }


            }

        });

    }
}