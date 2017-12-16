package com.anilxpert.food.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anilxpert.food.R;
import com.anilxpert.food.activity.DashBordActivity;
import com.anilxpert.food.adapter.DryShopAdapter;
import com.anilxpert.food.adapter.FindUsAdapter;
import com.anilxpert.food.custom_class.RecyclerTouchListener;
import com.anilxpert.food.fragments.HomeFragment;
import com.anilxpert.food.loopjServcice.CmdParams;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.loopjServcice.JsonDeserializer;
import com.anilxpert.food.loopjServcice.NetworkManager;
import com.anilxpert.food.models.FindUsModel;
import com.anilxpert.food.models.LoginModel;
import com.anilxpert.food.models.OrderSelectModel;
import com.anilxpert.food.utils.AppUrl;
import com.anilxpert.food.utils.SharedPref;
import com.anilxpert.food.utils.Utils;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by this pc on 11/23/2017.
 */

public class FindActivity extends AppCompatActivity implements View.OnClickListener, NetworkManager.onCallback {
    private TextView activity_title;
    private TextView temp_one_et;
    private Context mContext;

    private RecyclerView recyclerFindUs;
    private FindUsAdapter mAdapter;

    private List<FindUsModel.LocationsData> locations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find);
        mContext = FindActivity.this;
        // threadSplace();
        initialize();
    }

    private void initialize() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left);


        activity_title = (TextView) findViewById(R.id.activity_title);
        activity_title.setText(getString(R.string.n_find_us));

        recyclerFindUs = (RecyclerView) findViewById(R.id.recyclerFindUs);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerFindUs.setLayoutManager(mLayoutManager);
        recyclerFindUs.setItemAnimator(new DefaultItemAnimator());
        recyclerFindUs.addOnItemTouchListener(new RecyclerTouchListener(mContext, recyclerFindUs, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                FindUsModel.LocationsData malaXiangGuo = locations.get(position);
                SharedPref.putboolSP(ConstantField.FIND_US, true);
                SharedPref.putSP(ConstantField.ADDRESS_NAME, malaXiangGuo.landMark + ", " + malaXiangGuo.location + ", " + malaXiangGuo.pincode + "\n" + malaXiangGuo.city + ", " + malaXiangGuo.state + ", " + malaXiangGuo.country);
                SharedPref.putSP(ConstantField.ADDRESS_ID,malaXiangGuo.id );
                 Utils.resultActivity(mContext);
                //  ((DashBordActivity) getActivity()).gotoNextScreen(new HomeFragment(), mContext.getString(R.string.n_home));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        apiCall(getString(R.string.please_wait), AppUrl.GET_LOCATIONS, null, true, ConstantField.WHITCH_1);
    }


    private void apiCall(String apiTitle, String apiUrl, RequestParams requestParams, boolean progressBar, int apiWhitch) {
        NetworkManager networkManager = new NetworkManager();
        networkManager.callAPI(mContext, ConstantField.VAL_POST, apiUrl, requestParams, apiTitle, this, progressBar, apiWhitch);
    }

    @Override
    public void onSuccess(boolean success, String response, int which) {
        FindUsModel findUsModel = JsonDeserializer.deserializeJson(response, FindUsModel.class);
        if (findUsModel.status == 1) {
            if (findUsModel.locations != null && findUsModel.locations.size() > 0) {
                locations = findUsModel.locations;
                mAdapter = new FindUsAdapter(mContext, findUsModel.locations);
                recyclerFindUs.setAdapter(mAdapter);
            }


        }

    }

    @Override
    public void onFailure(boolean success, String response, int which) {

        Log.e("Responce", " Failed " + response);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //case R.id.temp_one_et:
            //SharedPref.putboolSP(ConstantField.FIND_US, true);
            // ((DashBordActivity) getActivity()).gotoNextScreen(new HomeFragment(), mContext.getString(R.string.n_home));
            //   break;
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