package com.genipos.labelmaker;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.utils.widget.MockView;

import com.genipos.labelmaker.model.PrintSetting;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class DataTextWatcher implements TextWatcher {
    Context mContext;
    TextInputEditText textInputEditText;
    TextInputEditText textInputEditTextPrev;
    TextView textView;
    //
    int whichOne = 0;
    //
    LinearLayout layout;
    EditText editTextData;
    EditText editTextAnother;
    TextView textViewData;
    TextView textViewDataV;
    //
    LinearLayout relativeLayout;
    EditText editTextName,editTextName1;
    MockView mockView;
    TextView textViewCode;

    public DataTextWatcher(Context context, TextInputEditText editPrevTextData, TextInputEditText editTextData, TextView textViewData) {
        this.mContext = context;
        this.textInputEditTextPrev =editPrevTextData;
        this.textInputEditText = editTextData;
        this.textView = textViewData;
        this.whichOne = 1;
    }

    public DataTextWatcher(LinearLayout layout, Context context, EditText editTextName, EditText editTextAnother, TextView textViewName, TextView textViewValue) {
        this.mContext = context;
        this.layout = layout;
        this.editTextData = editTextName;
        this.editTextAnother = editTextAnother;
        this.textViewData = textViewName;
        this.textViewDataV = textViewValue;
        this.whichOne = 2;
    }

    public DataTextWatcher(LinearLayout relativeLayout, Context context, EditText editTextXaxis, EditText editTextYaxis, MockView mockView, TextView textViewBarCode) {
        this.mContext = context;
        this.relativeLayout = relativeLayout;
        this.editTextName = editTextXaxis;
        this.editTextName1 = editTextYaxis;
        this.mockView = mockView;
        this.textViewCode = textViewBarCode;
        this.whichOne = 3;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        System.out.println(charSequence.toString() + i + i1 + i2);
        if (whichOne == 1){
            switch (textInputEditText.getId()){
                case R.id.edtcompanyname:
                case R.id.edtproductname:
                case R.id.edtproductdesc:
                case R.id.edtmfgdt:
                case R.id.edtuseby:
                case R.id.edtmfgat:
                case R.id.edtlicno:
                case R.id.edtbatchno:
                case R.id.edtnetwt:
                    textView.setText(charSequence);
                    break;
                case R.id.edtprice:
                    textView.setText("Rs "+charSequence);
                    break;
                case R.id.edtunit:
                    textView.setText(textInputEditTextPrev.getText().toString().trim() + " " + charSequence);
                    break;
            }
        }
        else if(whichOne == 2){
            switch (editTextData.getId()){
                case R.id.editTextName_:
                    if (charSequence.length() == 0){
                        textViewData.setVisibility(View.GONE);
                    }else {
                        textViewData.setVisibility(View.VISIBLE);
                        textViewData.setText(charSequence);
                    }
                    break;
                case R.id.editText_:
                    textViewData.setText(charSequence);
                    break;
                case R.id.editTextX_:
                    float factorl = 1.4f;
                    String stringValue = editTextAnother.getText().toString().trim();
                    if (charSequence.length() == 0 && stringValue.isEmpty())
                        layout.setPadding(0, 0, 0, 0);
                    else if (charSequence.length() == 0 && stringValue != "")
                        layout.setPadding(0, (int)(Integer.parseInt(stringValue)*factorl), 0, 0);
                    else if (charSequence.length() != 0 && stringValue.isEmpty())
                        layout.setPadding((int) (Integer.parseInt(charSequence.toString()) * factorl), 0, 0, 0);
                    else
                        layout.setPadding((int) (Integer.parseInt(charSequence.toString()) * factorl), (int) (Integer.parseInt(stringValue.toString()) * factorl), 0, 0);
                    break;
                case R.id.editTextY_:
                    float factort = 1.3f;
                    String stringValues = editTextAnother.getText().toString().trim();
                    if (charSequence.length() == 0 && stringValues.isEmpty())
                        layout.setPadding(0, 0, 0, 0);
                    else if (charSequence.length() == 0 && stringValues != "")
                        layout.setPadding((int)(Integer.parseInt(stringValues)*factort), 0, 0, 0);
                    else if (charSequence.length() != 0 && stringValues.isEmpty())
                        layout.setPadding(0, (int)(Integer.parseInt(charSequence.toString())*factort), 0, 0);
                    else
                        layout.setPadding((int)(Integer.parseInt(stringValues)*factort), (int)(Integer.parseInt(charSequence.toString())*factort), 0, 0);
                    break;
                case R.id.editTextSize_:
                    if (charSequence.length() == 0){
                        textViewData.setTextSize(11);
                        textViewDataV.setTextSize(11);
                    }else {
                        textViewData.setTextSize(Float.parseFloat(charSequence.toString()) * 6);
                        textViewDataV.setTextSize(Float.parseFloat(charSequence.toString()) * 6);
                    }
            }
        }
        else if(whichOne == 3){
            switch (editTextName.getId()){
                case R.id.editTextBarCode__:
                    textViewCode.setText(charSequence);
                    break;
                case R.id.editTextX__:
                    float factorl = 1.4f;
                    String stringValue = editTextName1.getText().toString().trim();
                    if (charSequence.length() == 0 && stringValue.isEmpty())
                        relativeLayout.setPadding(0, 0, 0, 0);
                    else if (charSequence.length() == 0 && stringValue != "")
                        relativeLayout.setPadding(0, (int)(Integer.parseInt(stringValue)*factorl), 0, 0);
                    else if (charSequence.length() != 0 && stringValue.isEmpty())
                        relativeLayout.setPadding((int) (Integer.parseInt(charSequence.toString()) * factorl), 0, 0, 0);
                    else
                        relativeLayout.setPadding((int) (Integer.parseInt(charSequence.toString()) * factorl), (int) (Integer.parseInt(stringValue.toString()) * factorl), 0, 0);
                    break;
                case R.id.editTextY__:
                    float factort = 1.3f;
                    String stringValues = editTextName1.getText().toString().trim();
                    if (charSequence.length() == 0 && stringValues.isEmpty())
                        relativeLayout.setPadding(0, 0, 0, 0);
                    else if (charSequence.length() == 0 && stringValues != "")
                        relativeLayout.setPadding((int)(Integer.parseInt(stringValues)*factort), 0, 0, 0);
                    else if (charSequence.length() != 0 && stringValues.isEmpty())
                        relativeLayout.setPadding(0, (int)(Integer.parseInt(charSequence.toString())*factort), 0, 0);
                    else
                        relativeLayout.setPadding((int)(Integer.parseInt(stringValues)*factort), (int)(Integer.parseInt(charSequence.toString())*factort), 0, 0);
                    break;
                case R.id.editTextHeight__:
                    ViewGroup.LayoutParams params = mockView.getLayoutParams();
                    if (charSequence.length() == 0)
                        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    else
                        params.height = Integer.parseInt(charSequence.toString());
                    mockView.setLayoutParams(params);
                    break;
                case R.id.editTextWide__:
                    ViewGroup.LayoutParams param = mockView.getLayoutParams();
                    if (charSequence.length() == 0)
                        param.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    else
                        param.width = Integer.parseInt(charSequence.toString());
                    mockView.setLayoutParams(param);
                    break;
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {}
}
