package com.anilxpert.food.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anilxpert.food.R;


/**
 * Created by this pc on 11/24/2017.
 */

public class FaqFragment extends Fragment {


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.faq, null);

        getActivity().setTitle("FaqFragment");
        return v;
        }

        }