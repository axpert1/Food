package com.anilxpert.food.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.activity.OrderSelectActivity;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_home, container, false);
        slidingImage(getList(), view);
        selectOutlet = (TextView) view.findViewById(R.id.select_outlet);

        selectOutlet.setOnClickListener(this);
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
                Utils.startActivity(getContext(), OrderSelectActivity.class);

                break;
        }
    }
}
