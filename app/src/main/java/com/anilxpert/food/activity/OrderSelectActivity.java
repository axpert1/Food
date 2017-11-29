package com.anilxpert.food.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.custom_class.CustomViewPager;
import com.anilxpert.food.fragments.DryShopFragment;
import com.anilxpert.food.fragments.MalaFragment;
import com.anilxpert.food.fragments.SpicinessFragment;
import com.anilxpert.food.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 11/28/2017.
 */

public class OrderSelectActivity extends AppCompatActivity {
    private TextView activity_title;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private TextView next_tv_levalone;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_select);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);


        activity_title = (TextView) findViewById(R.id.activity_title);
        activity_title.setText(getString(R.string.app_name));

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
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText(getString(R.string.spiceness_level));
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText(getString(R.string.dry_shop));
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText(getString(R.string.mala));
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
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

}
