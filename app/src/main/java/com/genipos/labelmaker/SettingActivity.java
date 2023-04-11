package com.genipos.labelmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.utils.widget.MockView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.genipos.labelmaker.common.SharePerfer;
import com.genipos.labelmaker.model.PrintSetting;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {
    Context context;
    //share perferance
    SharePerfer sharedPreferences;
    //show
    LinearLayout linearlayout;
    RelativeLayout linearlayout2;
    CardView cardView;
    EditText editTextName, editTextValue, editTextX, editTextY, editTextSize;
    EditText editTextBarCode, editTextXaxis, editTextYaxis, editTextHeight, editTextWide;
    TextView textViewName, textViewValue;
    MockView mockView;
    TextView textViewBarCode;
    ImageView imageViewCancel, imageViewClear;
    Button buttonAddBar, buttonAddNew, buttonSaveData;
    //save
    ArrayList<PrintSetting> barArrayList;
    ArrayList<PrintSetting> dataArrayList;
    PrintSetting printSetting;
    PrintSetting printSetting1;
    //dialog
    MaterialButton Abutton, Cbutton;
    TextInputEditText textInputEditTextH, textInputEditTextW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        context = this;
        showDailog();
        sharedPreferences = new SharePerfer(this);
        init();
    }

    private void init() {
        buttonAddBar = (Button) findViewById(R.id.button_add_bar);
        buttonAddNew = (Button) findViewById(R.id.button_add);
        buttonSaveData = (Button) findViewById(R.id.save_data);
        linearlayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearlayout2 = (RelativeLayout) findViewById(R.id.relativeLayout);
        cardView = (CardView) findViewById(R.id.cardView_);
    }

    private void showDailog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dummy_layout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        Abutton = (MaterialButton) dialog.findViewById(R.id.add_h_w);
        Cbutton = (MaterialButton) dialog.findViewById(R.id.cancelButton);
        textInputEditTextH = (TextInputEditText) dialog.findViewById(R.id.height);
        textInputEditTextW = (TextInputEditText) dialog.findViewById(R.id.width);
        Abutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.setHeight(textInputEditTextH.getText().toString());
                sharedPreferences.setWidth(textInputEditTextW.getText().toString());
                ShowAddForm();
                dialog.dismiss();
            }
        });
        Cbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showList();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showList() {

    }

    private void ShowAddForm() {
//        arrayList = new ArrayList<>();
//        uiAdapter = new PrintUIAdapter(context, arrayList);
//        listView.setAdapter(uiAdapter);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        String height = sharedPreferences.getHeight();
        String width = sharedPreferences.getWidth();
        if (height != "" && width != ""){
            buttonAddBar.setVisibility(View.VISIBLE);
            buttonAddNew.setVisibility(View.VISIBLE);
            buttonSaveData.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
            int heighty = (int) ((1/0.15875) * Integer.parseInt(height));
            int widthy = (int) ((1/0.15875) * Integer.parseInt(width));
            float factor = cardView.getContext().getResources().getDisplayMetrics().density;
            ViewGroup.LayoutParams params = cardView.getLayoutParams();
            params.height = (int) (heighty * factor);
            params.width = (int) (widthy * factor);
            cardView.setLayoutParams(params);

            buttonAddBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    barArrayList = new ArrayList<PrintSetting>();
                    final View viee = getLayoutInflater().inflate(R.layout.add_bar, null, false);
                    final View vieee = getLayoutInflater().inflate(R.layout.add_barcode, null, false);
                    editTextBarCode = (EditText) viee.findViewById(R.id.editTextBarCode__);
                    editTextXaxis = (EditText) viee.findViewById(R.id.editTextX__);
                    editTextYaxis = (EditText) viee.findViewById(R.id.editTextY__);
                    editTextHeight = (EditText) viee.findViewById(R.id.editTextHeight__);
                    editTextWide = (EditText) viee.findViewById(R.id.editTextWide__);
                    imageViewClear = (ImageView) viee.findViewById(R.id.imageView__);
                    LinearLayout relativeLayout = (LinearLayout) vieee.findViewById(R.id.relative_layout);
                    mockView = (MockView) vieee.findViewById(R.id.mockView);
                    textViewBarCode = (TextView) vieee.findViewById(R.id.textViewName);
                    imageViewClear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            linearlayout.removeView(viee);
                            linearlayout2.removeView(vieee);
                        }
                    });
//                    dummyBararrayList.add(new PrintSetting(Integer.parseInt(editTextXaxis.getText().toString()), Integer.parseInt(editTextYaxis.getText().toString()), editTextType.getText().toString(), Integer.parseInt(editTextHeight.getText().toString()), 1, 0, 3, Integer.parseInt(editTextWide.getText().toString()), editTextBarCode.getText().toString()));
                    editTextBarCode.addTextChangedListener(new DataTextWatcher(relativeLayout, SettingActivity.this,editTextBarCode,editTextBarCode, mockView, textViewBarCode));
                    editTextXaxis.addTextChangedListener(new DataTextWatcher(relativeLayout, SettingActivity.this,editTextXaxis,editTextYaxis, mockView, textViewBarCode));
                    editTextYaxis.addTextChangedListener(new DataTextWatcher(relativeLayout, SettingActivity.this,editTextYaxis,editTextXaxis, mockView, textViewBarCode));
                    editTextHeight.addTextChangedListener(new DataTextWatcher(relativeLayout, SettingActivity.this,editTextHeight,editTextHeight, mockView, textViewBarCode));
                    editTextWide.addTextChangedListener(new DataTextWatcher(relativeLayout, SettingActivity.this,editTextWide,editTextWide, mockView, textViewBarCode));
                    linearlayout.addView(viee);
                    linearlayout2.addView(vieee);
                }
            });

            buttonAddNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataArrayList = new ArrayList<PrintSetting>();
                    final View view1 = getLayoutInflater().inflate(R.layout.add_item, null, false);
                    final View view2 = getLayoutInflater().inflate(R.layout.add_subitem, null, false);
                    editTextName = (EditText) view1.findViewById(R.id.editTextName_);
                    editTextValue = (EditText) view1.findViewById(R.id.editText_);
                    editTextX = (EditText) view1.findViewById(R.id.editTextX_);
                    editTextY = (EditText) view1.findViewById(R.id.editTextY_);
                    editTextSize = (EditText) view1.findViewById(R.id.editTextSize_);
                    imageViewCancel = (ImageView) view1.findViewById(R.id.imageView_);
                    LinearLayout linearLayoutHolder = (LinearLayout) view2.findViewById(R.id.linearLayoutTextHolder);
                    textViewName = (TextView) view2.findViewById(R.id.textView_name);
                    textViewValue = (TextView) view2.findViewById(R.id.textView_value);
                    imageViewCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            linearlayout.removeView(view1);
                            linearlayout2.removeView(view2);
                        }
                    });
                    editTextName.setText("Label");
                    editTextName.addTextChangedListener(new DataTextWatcher(linearLayoutHolder, SettingActivity.this,editTextName,editTextName, textViewName, textViewName));
                    editTextValue.addTextChangedListener(new DataTextWatcher(linearLayoutHolder, SettingActivity.this,editTextValue,editTextValue, textViewValue, textViewValue));
                    editTextX.addTextChangedListener(new DataTextWatcher(linearLayoutHolder, SettingActivity.this,editTextX, editTextY, textViewName, textViewName));
                    editTextY.addTextChangedListener(new DataTextWatcher(linearLayoutHolder, SettingActivity.this,editTextY, editTextX, textViewName, textViewName));
                    editTextSize.addTextChangedListener(new DataTextWatcher(linearLayoutHolder, SettingActivity.this,editTextSize, editTextSize, textViewName, textViewValue));

                    linearlayout.addView(view1);
                    linearlayout2.addView(view2);
                }
            });

            buttonSaveData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    barArrayList.clear();
                    dataArrayList.clear();
                    EditText textBarc, textBarx, textBary, textBarh, textBarw ,
                            textDatan, textDatav, textDatax, textDatay, textDatas;
                    for (int i = 0; i < linearlayout.getChildCount(); i++){
                        View dataView = linearlayout.getChildAt(i);
                        textBarc = dataView.findViewById(R.id.editTextBarCode__);
                        textBarx = dataView.findViewById(R.id.editTextX__);
                        textBary = dataView.findViewById(R.id.editTextY__);
                        textBarh = dataView.findViewById(R.id.editTextHeight__);
                        textBarw = dataView.findViewById(R.id.editTextWide__);
                        textDatan = dataView.findViewById(R.id.editTextName_);
                        textDatav = dataView.findViewById(R.id.editText_);
                        textDatax = dataView.findViewById(R.id.editTextX_);
                        textDatay = dataView.findViewById(R.id.editTextY_);
                        textDatas = dataView.findViewById(R.id.editTextSize_);
                        if (textDatax == null && textBarx != null)
                            printSetting = new PrintSetting(Integer.parseInt(textBarx.getText().toString()), Integer.parseInt(textBary.getText().toString()), "128", Integer.parseInt(textBarh.getText().toString()), 1, 0, 3, Integer.parseInt(textBarw.getText().toString()), textBarc.getText().toString());
                        if (textBarx == null && textDatax != null) {
                            printSetting1 = new PrintSetting(Integer.parseInt(textDatax.getText().toString()), Integer.parseInt(textDatay.getText().toString()), textDatas.getText().toString(), 0, 1, 1, textDatan.getText().toString(), textDatav.getText().toString());
                        }
                        if (textDatax == null)
                            barArrayList.add(printSetting);
                        if (textBarx == null)
                            dataArrayList.add(printSetting1);
                    }
                    //all ok
                    //save in shareperference
                    System.out.println(height + " / " + width);
                    System.out.println(barArrayList + " / " + dataArrayList);
                    sharedPreferences.setFormate(height, width, barArrayList, dataArrayList);

                }
            });
        }
    }
}