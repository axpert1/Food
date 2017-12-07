package com.anilxpert.food.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class DryShopAdapter extends RecyclerView.Adapter<DryShopAdapter.MyViewHolder> {

    private List<OrderSelectModel.DrySoup> drySoups;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public ImageView imgReight;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtDryRowName);
            imgReight = (ImageView) view.findViewById(R.id.imgReight);
        }
    }


    public DryShopAdapter(Context context, List<OrderSelectModel.DrySoup> drySoups) {
        this.drySoups = drySoups;
        this.context = context;
    }

    @Override
    public DryShopAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dry_shop_row, parent, false);

        return new DryShopAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DryShopAdapter.MyViewHolder holder, final int position) {
        final OrderSelectModel.DrySoup drySoup = drySoups.get(position);
        holder.txtName.setText(drySoup.productName);
        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCheck();
                drySoups.get(position).selectItme = true;
                SharedPref.putSP(ConstantField.DRYSHOP_ID, drySoups.get(position).id);
                SharedPref.putSP(ConstantField.DRYSHOP_NAME, drySoups.get(position).productName);
                notifyDataSetChanged();

            }
        });
        if (drySoup.id.equals(SharedPref.getSP(ConstantField.DRYSHOP_ID))) {
            holder.imgReight.setVisibility(View.VISIBLE);
        } else {
            holder.imgReight.setVisibility(drySoup.selectItme ? View.VISIBLE : View.INVISIBLE);
        }

    }

    public void setCheck() {
        for (int i = 0; drySoups.size() > i; i++) {
            drySoups.get(i).selectItme = false;
        }

    }


    @Override
    public int getItemCount() {
        return drySoups.size();
    }
}

