package com.anilxpert.food.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.activity.DashBordActivity;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.utils.SharedPref;


/**
 * Created by this pc on 11/23/2017.
 */

public class FindFragment extends Fragment implements View.OnClickListener {
    private TextView temp_one_et;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.find, null);
        mContext = getActivity();

        temp_one_et = (TextView) v.findViewById(R.id.temp_one_et);
        temp_one_et.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.temp_one_et:
                SharedPref.putboolSP(ConstantField.FIND_US, true);
                ((DashBordActivity) getActivity()).gotoNextScreen(new HomeFragment(), mContext.getString(R.string.n_home));
                break;
        }
    }
}