package com.anilxpert.food.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.models.FindUsModel;
import com.anilxpert.food.models.OrderSelectModel;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 11/30/2017.
 */

public class FindUsAdapter extends RecyclerView.Adapter<FindUsAdapter.MyViewHolder> {

    private List<FindUsModel.LocationsData> locations;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtAddressLineOne;
        public TextView txtOpen;

        public MyViewHolder(View view) {
            super(view);
            txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            txtAddressLineOne = (TextView) view.findViewById(R.id.txtAddressLineOne);
            txtOpen = (TextView) view.findViewById(R.id.txtOpen);


        }
    }


    public FindUsAdapter(Context context, List<FindUsModel.LocationsData> locations) {
        this.locations = locations;
        this.context = context;
    }

    @Override
    public FindUsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.findus_row, parent, false);

        return new FindUsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FindUsAdapter.MyViewHolder holder, int position) {
        FindUsModel.LocationsData malaXiangGuo = locations.get(position);
        holder.txtTitle.setText(malaXiangGuo.title.toUpperCase());
        holder.txtAddressLineOne.setText(malaXiangGuo.landMark + ", " + malaXiangGuo.location + ", " + malaXiangGuo.pincode + "\n" + malaXiangGuo.city + ", " + malaXiangGuo.state + ", " + malaXiangGuo.country);
        holder.txtOpen.setText(malaXiangGuo.openDay.toUpperCase() + " - " + malaXiangGuo.closeDay.toUpperCase() + " : " + malaXiangGuo.openTime + " - " + malaXiangGuo.closeTime);


    }

    @Override
    public int getItemCount() {
        return locations.size();
    }
}
