package com.anilxpert.food.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.activity.OrderSummryActivity;
import com.anilxpert.food.models.SummeryModel;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 13-Dec-17.
 */

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.MyViewHolder> {

    private List<SummeryModel.Datum> data;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtQty;
        public TextView txtPrice;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtQty = (TextView) view.findViewById(R.id.txtQty);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);


        }
    }


    public OrderDetailsAdapter(Context context, List<SummeryModel.Datum> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public OrderDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_row, parent, false);

        return new OrderDetailsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderDetailsAdapter.MyViewHolder holder, final int position) {
        final SummeryModel.Datum dataResponce = data.get(position);
        holder.txtName.setText(dataResponce.name.toUpperCase());
        holder.txtQty.setText(String.valueOf(dataResponce.qty));
        holder.txtPrice.setText(context.getResources().getString(R.string.price) + " " + String.valueOf(dataResponce.price));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
