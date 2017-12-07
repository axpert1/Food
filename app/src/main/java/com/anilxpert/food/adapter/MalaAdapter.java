package com.anilxpert.food.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.models.OrderSelectModel;
import com.anilxpert.food.utils.SharedPref;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 11/30/2017.
 */

public class MalaAdapter extends RecyclerView.Adapter<MalaAdapter.MyViewHolder> {

    private List<OrderSelectModel.MalaXiangGuo> malaXiangGuos;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtMalaRowPrice;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtMalaRowName);
            txtMalaRowPrice = (TextView) view.findViewById(R.id.txtMalaRowPrice);

        }
    }


    public MalaAdapter(Context context, List<OrderSelectModel.MalaXiangGuo> malaXiangGuos1) {
        this.malaXiangGuos = malaXiangGuos1;
        this.context = context;
    }

    @Override
    public MalaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mala_row, parent, false);
        return new MalaAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MalaAdapter.MyViewHolder holder,final int position) {
        OrderSelectModel.MalaXiangGuo malaXiangGuo = malaXiangGuos.get(position);
        holder.txtName.setText(malaXiangGuo.productName);
        holder.txtMalaRowPrice.setText(" $ " + malaXiangGuo.price);


    }



    @Override
    public int getItemCount() {
        return malaXiangGuos.size();
    }
}
