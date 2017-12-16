package com.anilxpert.food.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.activity.OrderSelectActivity;
import com.anilxpert.food.activity.OrderSummryActivity;
import com.anilxpert.food.models.FindUsModel;
import com.anilxpert.food.models.SummeryModel;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 11-Dec-17.
 */

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.MyViewHolder> {

private List<SummeryModel.Datum> data;
private Context context;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView txtName;
    public Button btnRemove;
    public TextView txtQty;
    public Button btnAdd;
    public TextView txtPrice;

    public MyViewHolder(View view) {
        super(view);
        txtName = (TextView) view.findViewById(R.id.txtName);
        btnRemove = (Button) view.findViewById(R.id.btnRemove);
        txtQty = (TextView) view.findViewById(R.id.txtQty);
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        txtPrice = (TextView) view.findViewById(R.id.txtPrice);


    }
}


    public OrderSummaryAdapter(Context context, List<SummeryModel.Datum> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public OrderSummaryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.summery_row, parent, false);

        return new OrderSummaryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderSummaryAdapter.MyViewHolder holder, final int position) {
        final SummeryModel.Datum dataResponce = data.get(position);
        holder.txtName.setText(dataResponce.name.toUpperCase());
        holder.txtQty.setText(String.valueOf(dataResponce.qty));
        holder.txtPrice.setText(context.getResources().getString(R.string.price) + " " + String.valueOf(dataResponce.price));
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataResponce.qty > 1) {
                    ((OrderSummryActivity) context).setAmountFroAdapter(2, data.get(position).price);
                    data.get(position).qty = data.get(position).qty - 1;

                    notifyDataSetChanged();
                } else {

                }

            }
        });
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataResponce.qty < 100) {
                    ((OrderSummryActivity) context).setAmountFroAdapter(1, data.get(position).price);
                    data.get(position).qty = data.get(position).qty + 1;

                    notifyDataSetChanged();
                } else {

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
