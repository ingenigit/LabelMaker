package com.genipos.labelmaker.roomdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genipos.labelmaker.R;

import java.util.ArrayList;

public class ProAdapter extends RecyclerView.Adapter<ProAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Product> arrayList;
    PClickListener pClickListener;

    public interface PClickListener{
        void onClick(View view, int position);
    }
    public ProAdapter(Context mContext, ArrayList<Product> arrayList, PClickListener pClickListener) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.pClickListener = pClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.printed_ui, parent, false);
        return new ViewHolder(view, pClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = arrayList.get(position);
        holder.textViewCName.setText(product.getcName());
        holder.textViewPName.setText(product.getpName());
        holder.textViewPDesc.setText(product.getpDesc());
        holder.textViewMfgDate.setText(product.getpMfg());
        holder.textViewExpDate.setText(product.getpExp());
        holder.textViewNetWt.setText(product.getpWeight());
        holder.textViewBatchNo.setText(product.getpBatch());
        holder.textViewPrice.setText(product.getpPrice());
        holder.textViewMfgAt.setText(product.getpMfgAt());
        holder.textViewLicNo.setText(product.getpLic());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewCName, textViewPName, textViewPDesc,textViewMfgDate, textViewExpDate, textViewNetWt, textViewBatchNo, textViewPrice,
                textViewMfgAt, textViewLicNo;
        public ViewHolder(@NonNull View itemView, PClickListener pClickListener) {
            super(itemView);
            textViewCName = (TextView) itemView.findViewById(R.id.print_company_name);
            textViewPName = (TextView) itemView.findViewById(R.id.print_product_name);
            textViewPDesc = (TextView) itemView.findViewById(R.id.print_product_info);
            textViewMfgDate = (TextView) itemView.findViewById(R.id.print_mfg_dt_value);
            textViewExpDate = (TextView) itemView.findViewById(R.id.print_use_by_value);
            textViewNetWt = (TextView) itemView.findViewById(R.id.print_net_wt_value);
            textViewBatchNo = (TextView) itemView.findViewById(R.id.print_batch_no_value);
            textViewPrice = (TextView) itemView.findViewById(R.id.print_price_value);
            textViewMfgAt = (TextView) itemView.findViewById(R.id.print_mfg_at_value);
            textViewLicNo = (TextView) itemView.findViewById(R.id.print_fssai_lic_no_value);
            itemView.setOnClickListener(this);
            pClickListener = pClickListener;
        }

        @Override
        public void onClick(View view) {
            pClickListener.onClick(view, getAdapterPosition());
        }
    }
}
