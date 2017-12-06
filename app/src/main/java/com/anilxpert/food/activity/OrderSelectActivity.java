package com.anilxpert.food.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anilxpert.food.R;
import com.anilxpert.food.custom_class.CustomViewPager;
import com.anilxpert.food.dilogs.DilogCustom;
import com.anilxpert.food.fragments.DryShopFragment;
import com.anilxpert.food.fragments.MalaFragment;
import com.anilxpert.food.fragments.SpicinessFragment;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.loopjServcice.JsonDeserializer;
import com.anilxpert.food.loopjServcice.NetworkManager;
import com.anilxpert.food.models.CommonModel;
import com.anilxpert.food.models.OrderSelectModel;
import com.anilxpert.food.utils.AppUrl;
import com.anilxpert.food.utils.SharedPref;
import com.anilxpert.food.utils.Utils;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.fragment;
import static android.R.attr.type;

/**
 * Created by AnilXpert 9887230800 on 11/28/2017.
 */

public class OrderSelectActivity extends AppCompatActivity implements NetworkManager.onCallback, View.OnClickListener {
    private TextView activity_title;
    private TextView tabOne;
    private TextView tabTwo;
    private TextView tabThree;
    private Toolbar toolbar;
    private Context mContext;
    private String responce = null;

    private DilogCustom dilogCustom;
    private OrderSelectModel orderSelectModel;

    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private TextView next_tv_levalone;
    private ViewPagerAdapter adapter;

    public OrderSelectActivity.onCallback callbackSpicess;
    public OrderSelectActivity.onCallback callbackDryShop;
    public OrderSelectActivity.onCallback callbackMala;

    public interface onCallback {
        public void onData(boolean success, String response);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_select);
        mContext = OrderSelectActivity.this;
        dilogCustom = new DilogCustom();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);


        activity_title = (TextView) findViewById(R.id.activity_title);
        activity_title.setText(getString(R.string.app_name));
        SharedPref.removeSP(ConstantField.SELECTION_DATA);
        initialize();

        apiCall(getString(R.string.please_wait), AppUrl.GET_PRODUCTS, null, true, ConstantField.WHITCH_1);

    }

    private void initialize() {
        viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        viewPager.setPagingEnabled(false);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


        next_tv_levalone = (TextView) findViewById(R.id.next_tv_levalone);
        next_tv_levalone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getItem(+1);
                if (current < adapter.getCount()) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    Utils.startActivity(OrderSelectActivity.this, OrderSummryActivity.class);
                }

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SpicinessFragment(), getString(R.string.spiceness_level));
        adapter.addFragment(new DryShopFragment(), getString(R.string.dry_shop));
        adapter.addFragment(new MalaFragment(), getString(R.string.mala));
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText(getString(R.string.spiceness_level));
        tabLayout.getTabAt(0).setCustomView(tabOne);

        tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText(getString(R.string.dry_shop));
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText(getString(R.string.mala));
        tabLayout.getTabAt(2).setCustomView(tabThree);
        // TODO: 12/1/2017  Tab click disable 
//        LinearLayout tabStrip = ((LinearLayout) tabLayout.getChildAt(0));
//        for (int i = 0; i < tabStrip.getChildCount(); i++) {
//            tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    return true;
//                }
//            });
//
//        }
        selectTab(tabOne, tabTwo, tabThree);
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        getSelectTab(tab.getText().toString());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);

                    }
                }
        );
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    @Override
    public void onClick(View view) {

    }

    private void getSelectTab(String tabName) {
        switch (tabName) {
            case "Spiceness level":
                selectTab(tabOne, tabTwo, tabThree);
                break;
            case "Dry or Soup":
                selectTab(tabTwo, tabOne, tabThree);
                break;
            case "Mala Xiang Guo":
                selectTab(tabThree, tabOne, tabTwo);
                break;
        }
    }

    private void selectTab(TextView selected, TextView a, TextView b) {
  selected.setTextColor(getResources().getColor(R.color.txtWhite));
        a.setTextColor(getResources().getColor(R.color.txtBlack));
        b.setTextColor(getResources().getColor(R.color.txtBlack));

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    private void apiCall(String apiTitle, String apiUrl, RequestParams requestParams, boolean progressBar, int apiWhitch) {
        NetworkManager networkManager = new NetworkManager();
        networkManager.callAPI(mContext, ConstantField.VAL_POST, apiUrl, requestParams, apiTitle, this, progressBar, apiWhitch);
    }

    @Override
    public void onSuccess(boolean success, String response, int which) {
        Log.e("Responce", " Success " + response);
        this.responce = response;
        SharedPref.putSP(ConstantField.SELECTION_DATA, response);
        orderSelectModel = JsonDeserializer.deserializeJson(response, OrderSelectModel.class);
        if (orderSelectModel.status == 1) {
            try {
                // if (callbackDryShop != null)
                callbackDryShop.onData(true, responce);
                // if (callbackSpicess != null)
                callbackSpicess.onData(true, responce);
                // if (callbackMala != null)
                callbackMala.onData(true, responce);
            } catch (NullPointerException e) {

            }

        }


    }

    public void spicinessLevel(OrderSelectActivity.onCallback callback) {
        try {

            if (SharedPref.getSP(ConstantField.SELECTION_DATA) != null) {
                callbackSpicess.onData(true, SharedPref.getSP(ConstantField.SELECTION_DATA));
            } else {
                this.callbackSpicess = callback;
            }
        } catch (Exception e) {

        }


    }

    public void dryShopLevel(OrderSelectActivity.onCallback callback) {
        if (SharedPref.getSP(ConstantField.SELECTION_DATA) != null) {
            callbackDryShop.onData(true, SharedPref.getSP(ConstantField.SELECTION_DATA));
        } else {
            this.callbackDryShop = callback;
        }


    }


    public void malaXiangGuo(OrderSelectActivity.onCallback callback) {
        try {

            if (SharedPref.getSP(ConstantField.SELECTION_DATA) != null) {
                callbackMala.onData(true, SharedPref.getSP(ConstantField.SELECTION_DATA));
            } else {
                this.callbackMala = callback;
            }
        } catch (Exception e) {

        }
        // if (responce != null) {
        //    callbackMala.onData(true, responce);
        // } else {

        // }


    }

    @Override
    public void onFailure(boolean success, String response, int which) {
        Log.e("Responce", " Failed " + response);
    }

}
