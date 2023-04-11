package com.genipos.labelmaker.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.genipos.labelmaker.DummyLayout;
import com.genipos.labelmaker.R;
import com.genipos.labelmaker.common.SharePerfer;

import java.util.ArrayList;

public class DummyAdapter extends RecyclerView.Adapter<DummyAdapter.ModeuleViewHolder> {
    Context context;
    ArrayList<SharePerfer.AllTogether> arrayList;

    public DummyAdapter(Context context, ArrayList<SharePerfer.AllTogether> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ModeuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dummy_test, parent, false);
        return new ModeuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModeuleViewHolder holder, int position) {
        int i = position;
        SharePerfer.AllTogether together = arrayList.get(i);
        holder.textView.setText(together.height + "*" + together.width);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DummyLayout.class);
                intent.putExtra("position", i);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ModeuleViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;
        public ModeuleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textViewFrame);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}
