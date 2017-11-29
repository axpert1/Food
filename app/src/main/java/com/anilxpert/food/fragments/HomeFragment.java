package com.anilxpert.food.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.activity.DashBordActivity;
import com.anilxpert.food.activity.LogRegActivity;
import com.anilxpert.food.activity.OrderSelectActivity;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.utils.SharedPref;
import com.anilxpert.food.utils.Utils;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ANDROID.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private SliderLayout mDemoSlider;
    private TextView selectOutlet;
    private Context mContext;
    private Button btnProceed;
    private RelativeLayout relative1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_home, container, false);
        mContext = getActivity();
        slidingImage(getList(), view);
        selectOutlet = (TextView) view.findViewById(R.id.select_outlet);
        selectOutlet.setOnClickListener(this);
        relative1 = (RelativeLayout) view.findViewById(R.id.relative1);
        btnProceed = (Button) view.findViewById(R.id.btnProceed);
        btnProceed.setOnClickListener(this);
        if (SharedPref.getboolSP(ConstantField.FIND_US)) {
            relative1.setVisibility(View.VISIBLE);
            btnProceed.setVisibility(View.VISIBLE);
        } else {
            relative1.setVisibility(View.GONE);
            btnProceed.setVisibility(View.GONE);
        }
        return view;
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("http://thesmartlocal.com/images/easyblog_images/2088/Burger-Grp-Shot_4601.jpg");
        list.add("http://thesmartlocal.com/images/easyblog_images/2088/Burger-Grp-Shot_4601.jpg");
        list.add("http://thesmartlocal.com/images/easyblog_images/2088/Burger-Grp-Shot_4601.jpg");
        list.add("http://thesmartlocal.com/images/easyblog_images/2088/Burger-Grp-Shot_4601.jpg");

        return list;
    }

    public void slidingImage(List<String> datums, View view) {
        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);
        mDemoSlider.removeAllSliders();
        for (int i = 0; i < datums.size(); i++) {
            DefaultSliderView textSliderView = new DefaultSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(null)
                    .image(datums.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            //    textSliderView.setOnSliderClickListener(this);
            textSliderView.getBundle()
                    .putString("extra", "anil");
            mDemoSlider.addSlider(textSliderView);

        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setCustomIndicator((PagerIndicator) view.findViewById(R.id.custom_indicator));
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(5000);
        // mDemoSlider.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_outlet:
                // Utils.startActivity(getContext(), OrderSelectActivity.class);
                ((DashBordActivity) getActivity()).gotoNextScreen(new FindFragment(), mContext.getString(R.string.n_find_us));
                break;
            case R.id.btnProceed:
                Utils.startActivity(getContext(), OrderSelectActivity.class);
                break;
        }
    }
}
