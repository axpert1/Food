package com.anilxpert.food.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anilxpert.food.R;
import com.anilxpert.food.activity.OrderSelectActivity;
import com.anilxpert.food.adapter.DryShopAdapter;
import com.anilxpert.food.adapter.SpicinessAdapter;
import com.anilxpert.food.loopjServcice.JsonDeserializer;
import com.anilxpert.food.models.OrderSelectModel;

/**
 * Created by AnilXpert 9887230800 on 11/28/2017.
 */

public class DryShopFragment extends Fragment implements OrderSelectActivity.onCallback {

    private RecyclerView recyclerDryShop;
    private DryShopAdapter mAdapter;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.dry_shop, null);


        recyclerDryShop = (RecyclerView) v.findViewById(R.id.recyclerDryShop);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerDryShop.setLayoutManager(mLayoutManager);
        recyclerDryShop.setItemAnimator(new DefaultItemAnimator());

       ((OrderSelectActivity) getActivity()).dryShopLevel(this);

        return v;
    }

    @Override
    public void onData(boolean success, String response) {
        OrderSelectModel orderSelectModel = JsonDeserializer.deserializeJson(response, OrderSelectModel.class);
        if (orderSelectModel.status == 1) {
            if (orderSelectModel.drySoup != null && orderSelectModel.drySoup.size() > 0) {

                mAdapter = new DryShopAdapter(mContext, orderSelectModel.drySoup);
                recyclerDryShop.setAdapter(mAdapter);
            }


        }
    }
}