package com.chalenge.exchangerate.utils;

import android.app.Activity;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.chalenge.exchangerate.R;
import com.chalenge.exchangerate.data.model.Currency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CurrencyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;
    private List<Pair<Currency, String>> currencyRates;
    private View.OnClickListener mOnClickListener;

    public CurrencyAdapter(Activity context) {
        this.context = context;
        this.currencyRates = new ArrayList<Pair<Currency, String>>();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        rootView.setOnClickListener(mOnClickListener);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Pair<Currency,String> currency = currencyRates.get(position);
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;

        viewHolder.txtView_title.setText(currency.first.name());
        viewHolder.txtView_description.setText(currency.second);
    }

    @Override
    public int getItemCount() {
        return currencyRates.size();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView_icon;
        TextView txtView_title;
        TextView txtView_description;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView_icon = itemView.findViewById(R.id.imgView_icon);
            txtView_title = itemView.findViewById(R.id.txtView_title);
            txtView_description = itemView.findViewById(R.id.txtView_description);
        }
    }
    /**
     * Updates the content of the list.
     *
     * @param models the new list
     */
    public void replaceAll(Map<Currency,String> models) {
        currencyRates.clear();
        for (Map.Entry<Currency, String> entry : models.entrySet()) {
            currencyRates.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        notifyDataSetChanged();
    }

    public void setItemClickListener(View.OnClickListener listener){
        this.mOnClickListener = listener;
    }
}