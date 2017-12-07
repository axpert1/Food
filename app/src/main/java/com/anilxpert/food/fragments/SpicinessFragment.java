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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.anilxpert.food.R;
import com.anilxpert.food.activity.DashBordActivity;
import com.anilxpert.food.activity.OrderSelectActivity;
import com.anilxpert.food.adapter.SpicinessAdapter;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.loopjServcice.JsonDeserializer;
import com.anilxpert.food.models.OrderSelectModel;
import com.anilxpert.food.utils.SharedPref;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 11/28/2017.
 */

public class SpicinessFragment extends Fragment implements View.OnClickListener, OrderSelectActivity.onCallback {
    private Context mContext;

    private RelativeLayout relative_tab;
    private RecyclerView recyclerSpicess;
    private ImageView error;
    private View v;

    private SpicinessAdapter mAdapter;
    private List<OrderSelectModel.SpicinessLevel> spicinessLevelList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        v = inflater.inflate(R.layout.spiciness, null);

        mContext = getActivity();

        relative_tab = (RelativeLayout) v.findViewById(R.id.relative_tab);
        error = (ImageView) v.findViewById(R.id.error);


        relative_tab.setOnClickListener(this);

        recyclerSpicess = (RecyclerView) v.findViewById(R.id.recyclerSpicess);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerSpicess.setLayoutManager(mLayoutManager);
        recyclerSpicess.setItemAnimator(new DefaultItemAnimator());

        ((OrderSelectActivity) getActivity()).spicinessLevel(this);

        return v;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_tab:
                break;
        }
    }

    @Override
    public void onData(boolean success, String response) {
        OrderSelectModel orderSelectModel = JsonDeserializer.deserializeJson(response, OrderSelectModel.class);
        if (orderSelectModel.status == 1) {
            if (orderSelectModel.spicinessLevel != null && orderSelectModel.spicinessLevel.size() > 0) {
                spicinessLevelList = orderSelectModel.spicinessLevel;
                mAdapter = new SpicinessAdapter(mContext, spicinessLevelList);
                recyclerSpicess.setAdapter(mAdapter);
            }


        }
    }

    public boolean setImage() {
        if (SharedPref.getSP(ConstantField.SPICINESSLEVEL_ID) != null) {
            error.setVisibility(View.GONE);
            return true;
        } else {
            error.setVisibility(View.VISIBLE);
            return false;
        }

    }
}