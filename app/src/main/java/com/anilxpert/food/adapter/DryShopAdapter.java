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

public class DryShopAdapter extends RecyclerView.Adapter<DryShopAdapter.MyViewHolder> {

    private List<OrderSelectModel.DrySoup> drySoups;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtDryRowName);

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
    public void onBindViewHolder(DryShopAdapter.MyViewHolder holder, int position) {
        OrderSelectModel.DrySoup drySoup = drySoups.get(position);
        holder.txtName.setText(drySoup.productName);

    }

    @Override
    public int getItemCount() {
        return drySoups.size();
    }
}

