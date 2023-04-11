package com.genipos.labelmaker.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.genipos.labelmaker.R;
import com.genipos.labelmaker.model.PrintUIModel;

import java.util.ArrayList;

public class PrintUIAdapter extends ArrayAdapter<PrintUIModel> {
    private Context mContext;
    private ArrayList<PrintUIModel> arrayList;

    public PrintUIAdapter(Context context, ArrayList<PrintUIModel> arrayList) {
        super(context, 0, arrayList);
        this.mContext = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = LayoutInflater.from(mContext).inflate(R.layout.dummy_layout, parent, false);
//        TextView textView = (TextView) view.findViewById(R.id.textView);
//        RadioButton radioButton = (RadioButton) view.findViewById(R.id.radioButton);
        PrintUIModel printUIModel = arrayList.get(position);
//        textView.setText(printUIModel.getName());
        return view;
    }
}
