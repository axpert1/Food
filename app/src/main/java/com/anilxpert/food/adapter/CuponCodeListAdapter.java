package com.anilxpert.food.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.models.CuponCodeListModel;
import com.anilxpert.food.models.OrderSelectModel;
import com.anilxpert.food.utils.SharedPref;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 13-Dec-17.
 */

public class CuponCodeListAdapter extends RecyclerView.Adapter<CuponCodeListAdapter.MyViewHolder> {

    private List<CuponCodeListModel.CouponDetail> couponDetails;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCuponCode;


        public MyViewHolder(View view) {
            super(view);
            txtCuponCode = (TextView) view.findViewById(R.id.txtCuponCode);

        }
    }


    public CuponCodeListAdapter(Context context, List<CuponCodeListModel.CouponDetail> couponDetails) {
        this.couponDetails = couponDetails;
        this.context = context;
    }

    @Override
    public CuponCodeListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coupon_row, parent, false);

        return new CuponCodeListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CuponCodeListAdapter.MyViewHolder holder, final int position) {
        final CuponCodeListModel.CouponDetail coupon = couponDetails.get(position);
        holder.txtCuponCode.setText(coupon.promoCode);

    }


    @Override
    public int getItemCount() {
        return couponDetails.size();
    }
}

