package com.anilxpert.food.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.models.OrderListModel;
import com.anilxpert.food.models.OrderSelectModel;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 13-Dec-17.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> {

    private List<OrderListModel.OrderDetail> orderDetails;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtAmount;
        public TextView txtStaus;


        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtDryRowName);
            txtAmount = (TextView) view.findViewById(R.id.txtAmount);
            txtStaus = (TextView) view.findViewById(R.id.txtStaus);

        }
    }


    public OrderListAdapter(Context context, List<OrderListModel.OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
        this.context = context;
    }

    @Override
    public OrderListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_order_row, parent, false);

        return new OrderListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderListAdapter.MyViewHolder holder, final int position) {
        final OrderListModel.OrderDetail order = orderDetails.get(position);
        holder.txtName.setText("Order no: " + order.orderId);
        holder.txtAmount.setText("Amount : " + context.getResources().getString(R.string.price) + " " + order.restAmount);
        String status = order.banned.equals("0") ? "Panding" : "Delivered";
        holder.txtStaus.setText("Status: " + status);

    }


    @Override
    public int getItemCount() {
        return orderDetails.size();
    }
}


