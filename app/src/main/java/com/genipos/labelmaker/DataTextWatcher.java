package com.genipos.labelmaker;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class DataTextWatcher implements TextWatcher {
    Context mContext;
    TextInputEditText textInputEditText;
    TextInputEditText textInputEditTextPrev;
    TextView textView;

    public DataTextWatcher(Context context, TextInputEditText editPrevTextData, TextInputEditText editTextData, TextView textViewData) {
        this.mContext = context;
        this.textInputEditTextPrev =editPrevTextData;
        this.textInputEditText = editTextData;
        this.textView = textViewData;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
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
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {}
}
