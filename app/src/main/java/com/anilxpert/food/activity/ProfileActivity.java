package com.anilxpert.food.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.dilogs.DilogCustom;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.utils.SharedPref;


/**
 * Created by this pc on 11/23/2017.
 */

public class ProfileFragment extends Fragment {

    private View view;
    private Context mContext;
    private DilogCustom dilogCustom;
    private View.OnClickListener retryClick;


    private TextView txtFName;
    private TextView txtLName;
    private TextView txtEmail;
    private TextView txtPhone;

    private LinearLayout mainLayout;
    private LinearLayout sininLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile, null);
        mContext = getActivity();
        dilogCustom = new DilogCustom();
        initialize();

        return view;
    }

    private void initialize() {
        txtFName = (TextView) view.findViewById(R.id.txtFName);
        txtLName = (TextView) view.findViewById(R.id.txtLName);
        txtEmail = (TextView) view.findViewById(R.id.txtEmail);
        txtPhone = (TextView) view.findViewById(R.id.txtMobile);

        mainLayout = (LinearLayout) view.findViewById(R.id.mainLayout);
        sininLayout = (LinearLayout) view.findViewById(R.id.sininLayout);
        setDataFromSP();
    }

    protected void setDataFromSP() {
        if (SharedPref.getSP(ConstantField.USER_ID) != null) {
            txtFName.setText(SharedPref.getSP(ConstantField.USER_F_NAME));
            txtLName.setText(SharedPref.getSP(ConstantField.USER_L_NAME));
            txtEmail.setText(SharedPref.getSP(ConstantField.USER_EMAIL));
            txtPhone.setText(SharedPref.getSP(ConstantField.USER_MOBILE));

        }

    }

}