package com.anilxpert.food.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.activity.OrderSelectActivity;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.models.OrderSelectModel;
import com.anilxpert.food.utils.SharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 11/30/2017.
 */

public class MalaAdapter extends RecyclerView.Adapter<MalaAdapter.MyViewHolder> {
    private List<OrderSelectModel.MalaXiangGuo> malaXiangGuos;
    private Context context;
    int priceTotal = 0;
    int qrtTotal = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtMalaRowPrice;
        public TextView txtItem;
        public View viewSelected;
        public ImageView imgClose;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtMalaRowName);
            txtMalaRowPrice = (TextView) view.findViewById(R.id.txtMalaRowPrice);

            viewSelected = (View) view.findViewById(R.id.viewSelected);

            txtItem = (TextView) view.findViewById(R.id.txtItem);
            imgClose = (ImageView) view.findViewById(R.id.imageClose);

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
    public void onBindViewHolder(final MalaAdapter.MyViewHolder holder, final int position) {
        final OrderSelectModel.MalaXiangGuo malaXiangGuo = malaXiangGuos.get(position);
        holder.txtName.setText(malaXiangGuo.productName);
        holder.txtMalaRowPrice.setText(" $ " + malaXiangGuo.price);

        setSelctedItem(holder, malaXiangGuo.qty);

        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrtTotal = 0;
                priceTotal = 0;
                malaXiangGuo.qty = malaXiangGuo.qty + 1;
                setSelctedItem(holder, malaXiangGuo.qty);
                notifyDataSetChanged();
                getSelectedData();


            }
        });

        holder.imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrtTotal = 0;
                priceTotal = 0;
                malaXiangGuo.qty = malaXiangGuo.qty - 1;
                setSelctedItem(holder, malaXiangGuo.qty);
                notifyDataSetChanged();
                getSelectedData();

            }
        });

        if (malaXiangGuo.qty >= 0) {
            if (malaXiangGuo.price != null)
                getTotalAmount(malaXiangGuo.qty, Integer.parseInt(malaXiangGuo.price));
            getQty(malaXiangGuo.qty);
            ((OrderSelectActivity) context).setQtyAndTotalPrice(qrtTotal, priceTotal);
        }
        // ((OrderSelectActivity) context).getSelectedData(malaXiangGuos);

    }

    private int getQty(int qty) {
        qrtTotal = qrtTotal + qty;
        return qrtTotal;

    }

    private int getTotalAmount(int qty, int price) {
        priceTotal = priceTotal + (qty * price);
        return priceTotal;

    }

    private void setSelctedItem(MalaAdapter.MyViewHolder holder, int qty) {
        if (qty > 0) {
            setVisable(holder, String.valueOf(qty));
        } else {
            setInVisable(holder);
        }
    }

    private void setVisable(MalaAdapter.MyViewHolder holder, String qty) {
        holder.txtItem.setVisibility(View.VISIBLE);
        holder.txtItem.setText(qty);
        holder.imgClose.setVisibility(View.VISIBLE);
        holder.viewSelected.setVisibility(View.VISIBLE);
    }

    private void setInVisable(MalaAdapter.MyViewHolder holder) {
        holder.txtItem.setVisibility(View.INVISIBLE);
        holder.imgClose.setVisibility(View.INVISIBLE);
        holder.viewSelected.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return malaXiangGuos.size();
    }

    public String getSelectedData() {
        JSONObject dataObj = new JSONObject();
        JSONArray product = new JSONArray();
        if (malaXiangGuos != null)
            for (int i = 0; i < malaXiangGuos.size(); i++) {
                if (malaXiangGuos.get(i).qty > 0) {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("id", malaXiangGuos.get(i).id);
                        obj.put("name", malaXiangGuos.get(i).productName);
                        obj.put("qty", malaXiangGuos.get(i).qty);
                        obj.put("price", malaXiangGuos.get(i).price);
                        product.put(obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        try {
            dataObj.put("data", product);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Log.e("SAVE_SP", product.toString());
        SharedPref.putSP(ConstantField.USER_ORDER, dataObj.toString());
        return dataObj.toString();
    }
}
