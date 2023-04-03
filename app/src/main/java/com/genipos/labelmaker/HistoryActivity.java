package com.genipos.labelmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.genipos.labelmaker.roomdb.ProAdapter;
import com.genipos.labelmaker.roomdb.ProRepository;
import com.genipos.labelmaker.roomdb.Product;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProRepository proRepository;
    Button print0,print, edit;
    EditText editText;
    ImageView imageView;
    TextView textView;
    AppEnv  gAppEnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        gAppEnv = (AppEnv) getApplicationContext();
        proRepository = new ProRepository(getApplication());

        imageView = (ImageView) findViewById(R.id.imageView_status);
        textView = (TextView) findViewById(R.id.textView_status);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_View);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Dialog dialog = new Dialog(this);

        if (!gAppEnv.getTscPrinterManager().isPrinterInitialized()) {
            imageView.setColorFilter(Color.parseColor("#FF0000"));
            textView.setText("Disconnected");
            Toast.makeText(getApplicationContext(), "Printer is Offline", Toast.LENGTH_SHORT).show();
        }else{
            imageView.setColorFilter(Color.parseColor("#00FF22"));
            textView.setText("Connected");
            Toast.makeText(getApplicationContext(), "Printer is Online", Toast.LENGTH_SHORT).show();
        }

        proRepository.getAllProducts().observe(HistoryActivity.this, products -> {
            if (products != null && !products.isEmpty()){
                ProAdapter.PClickListener mListener = new ProAdapter.PClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Toast.makeText(HistoryActivity.this, "hi"+products.get(position), Toast.LENGTH_SHORT).show();
                        dialog.setContentView(R.layout.dialog_print_edit_option);
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        dialog.setCancelable(true);
                        print0 = (Button) dialog.findViewById(R.id.button0);
                        print = (Button) dialog.findViewById(R.id.button);
                        edit = (Button) dialog.findViewById(R.id.button2);
                        editText = (EditText) dialog.findViewById(R.id.edittext);
                        print0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                LabelInfo labelinfo = new LabelInfo();
                                labelinfo.title = products.get(position).getcName();
                                labelinfo.prodname = products.get(position).getpName();
                                labelinfo.prodinfo = products.get(position).getpDesc();
                                labelinfo.mfgdate =products.get(position).getpMfg();
                                labelinfo.expdate = products.get(position).getpExp();
                                labelinfo.netweight = products.get(position).getpWeight();
                                labelinfo.wtunit =products.get(position).getpUnit();
                                labelinfo.batchno =products.get(position).getpBatch();
                                labelinfo.price = products.get(position).getpPrice();
                                labelinfo.mfgsite = products.get(position).getpMfgAt();
                                labelinfo.licno = products.get(position).getpLic();
                                String scnt = editText.getText().toString();
                                Integer lpcnt = Integer.parseInt(scnt);
                                labelinfo.count = lpcnt;
                                //
                                labelinfo.barcode = "1234568952";//prodinfo.barcode;
                                labelinfo.validity = "xxxx";//prodinfo.validity.toString()+ "Days";
                                labelinfo.footer = "Thank You";//gAppEnv.getLabelFooter();
                                gAppEnv.getTscPrinterManager().PrintLabel(labelinfo);
                            }
                        });
                        print.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(HistoryActivity.this, "print", Toast.LENGTH_SHORT).show();
                                edit.setVisibility(View.GONE);
                                print0.setVisibility(View.VISIBLE);
                                print.setVisibility(View.GONE);
                                editText.setVisibility(View.VISIBLE);
                            }
                        });

                        edit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(HistoryActivity.this, "edit" + products.get(position).getPid(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(HistoryActivity.this, PrintLabelActivity.class);
                                intent.putExtra("selected_ID", products.get(position).getPid());
                                startActivity(intent);
                            }
                        });
                        dialog.show();
                    }
                };
                ProAdapter adapter = new ProAdapter(HistoryActivity.this, (ArrayList<Product>)products, mListener);
                System.out.println(adapter.getItemCount());
                recyclerView.setAdapter(adapter);
            }
        });
    }
}