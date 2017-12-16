package com.anilxpert.food.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anilxpert.food.R;
import com.anilxpert.food.activity.OrderSelectActivity;
import com.anilxpert.food.adapter.DryShopAdapter;
import com.anilxpert.food.adapter.MalaAdapter;
import com.anilxpert.food.custom_class.MyDividerItemDecoration;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.loopjServcice.JsonDeserializer;
import com.anilxpert.food.models.OrderSelectModel;
import com.anilxpert.food.utils.DatabaseHandler;
import com.anilxpert.food.utils.SharedPref;

/**
 * Created by AnilXpert 9887230800 on 11/28/2017.
 */

public class MalaFragment extends Fragment implements OrderSelectActivity.onCallback {
    private RecyclerView recyclerMala;
    private MalaAdapter mAdapter;
    private Context mContext;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.mala, null);
        mContext = getContext();
        recyclerMala = (RecyclerView) v.findViewById(R.id.recyclerMala);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerMala.setLayoutManager(mLayoutManager);
        recyclerMala.setItemAnimator(new DefaultItemAnimator());


        // ((OrderSelectActivity) getActivity()).malaXiangGuo(this);
        setdata();
        return v;
    }

    public void setdata() {
        String response = SharedPref.getSP(ConstantField.SELECTION_DATA);
        if (response != null) {
            OrderSelectModel orderSelectModel = JsonDeserializer.deserializeJson(response, OrderSelectModel.class);
            if (orderSelectModel.status == 1) {
                if (orderSelectModel.malaXiangGuo != null && orderSelectModel.malaXiangGuo.size() > 0) {
                   this.mAdapter = new MalaAdapter(mContext, orderSelectModel.malaXiangGuo);
                    recyclerMala.setAdapter(mAdapter);
                }


            }
        } else {
            ((OrderSelectActivity) getActivity()).malaXiangGuo(this);
        }

    }

    @Override
    public void onData(boolean success, String response) {
        OrderSelectModel orderSelectModel = JsonDeserializer.deserializeJson(response, OrderSelectModel.class);
        if (orderSelectModel.status == 1) {
            if (orderSelectModel.malaXiangGuo != null && orderSelectModel.malaXiangGuo.size() > 0) {
               this.mAdapter = new MalaAdapter(mContext, orderSelectModel.malaXiangGuo);
                recyclerMala.setAdapter(mAdapter);
            }


        }
    }


}