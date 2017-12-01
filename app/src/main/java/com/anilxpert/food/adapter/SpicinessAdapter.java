package com.anilxpert.food.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.models.OrderSelectModel;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 11/30/2017.
 */

public class SpicinessAdapter extends RecyclerView.Adapter<SpicinessAdapter.MyViewHolder> {

    private List<OrderSelectModel.SpicinessLevel> spicinessLevelList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName, year, genre;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtName);

        }
    }


    public SpicinessAdapter(Context context, List<OrderSelectModel.SpicinessLevel> spicinessLevelList) {
        this.spicinessLevelList = spicinessLevelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spiciness_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OrderSelectModel.SpicinessLevel spicinessLevel = spicinessLevelList.get(position);
        holder.txtName.setText(spicinessLevel.productName);

    }

    @Override
    public int getItemCount() {
        return spicinessLevelList.size();
    }
}
