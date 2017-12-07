package com.anilxpert.food.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.models.OrderSelectModel;
import com.anilxpert.food.utils.SharedPref;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 11/30/2017.
 */

public class SpicinessAdapter extends RecyclerView.Adapter<SpicinessAdapter.MyViewHolder> {

    private List<OrderSelectModel.SpicinessLevel> spicinessLevelList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public ImageView imgReight;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtName);
            imgReight = (ImageView) view.findViewById(R.id.imgReight);

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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final OrderSelectModel.SpicinessLevel spicinessLevel = spicinessLevelList.get(position);
        holder.txtName.setText(spicinessLevel.productName);
        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCheck();
                spicinessLevelList.get(position).selectItme = true;
                SharedPref.putSP(ConstantField.SPICINESSLEVEL_ID, spicinessLevelList.get(position).id);
                SharedPref.putSP(ConstantField.SPICINESSLEVEL_NAME, spicinessLevelList.get(position).productName);
                notifyDataSetChanged();
            }
        });
        if (spicinessLevel.id.equals(SharedPref.getSP(ConstantField.SPICINESSLEVEL_ID))) {
            holder.imgReight.setVisibility(View.VISIBLE);
        } else {
            holder.imgReight.setVisibility(spicinessLevel.selectItme ? View.VISIBLE : View.INVISIBLE);
        }

    }

    public void setCheck() {
        for (int i = 0; spicinessLevelList.size() > i; i++) {
            spicinessLevelList.get(i).selectItme = false;
        }

    }

    @Override
    public int getItemCount() {
        return spicinessLevelList.size();
    }
}
