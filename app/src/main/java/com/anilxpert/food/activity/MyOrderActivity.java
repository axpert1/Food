package com.anilxpert.food.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.anilxpert.food.R;
import com.anilxpert.food.adapter.CuponCodeListAdapter;
import com.anilxpert.food.adapter.OrderListAdapter;
import com.anilxpert.food.custom_class.MyDividerItemDecoration;
import com.anilxpert.food.custom_class.RecyclerTouchListener;
import com.anilxpert.food.loopjServcice.CmdParams;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.loopjServcice.JsonDeserializer;
import com.anilxpert.food.loopjServcice.NetworkManager;
import com.anilxpert.food.models.CuponCodeListModel;
import com.anilxpert.food.models.OrderListModel;
import com.anilxpert.food.utils.AppUrl;
import com.anilxpert.food.utils.SharedPref;
import com.anilxpert.food.utils.Utils;
import com.loopj.android.http.RequestParams;

import java.util.List;

/**
 * Created by AnilXpert 9887230800 on 11/28/2017.
 */

public class MyOrderActivity extends BaseActivity_ implements NetworkManager.onCallback {
    private TextView activity_title;
    private TextView txtCheck;

    private Context mContext;
    private LinearLayout no_order;


    private RecyclerView recyclerCuponCode;
    private List<OrderListModel.OrderDetail> orderDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_order);
        setupToolbar(getString(R.string.n_my_orders));
        mContext = MyOrderActivity.this;
        initialize();
    }

    private void initialize() {

        txtCheck = (TextView) findViewById(R.id.txtCheck);
        no_order = (LinearLayout) findViewById(R.id.no_order);


        recyclerCuponCode = (RecyclerView) findViewById(R.id.recyclerCuponCode);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerCuponCode.setLayoutManager(mLayoutManager);
        recyclerCuponCode.setItemAnimator(new DefaultItemAnimator());
        recyclerCuponCode.addItemDecoration(new MyDividerItemDecoration(mContext, LinearLayoutManager.VERTICAL, 0));
        recyclerCuponCode.addOnItemTouchListener(new RecyclerTouchListener(mContext, recyclerCuponCode, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                // resultActivity(couponDetails.get(position).discountAmount, couponDetails.get(position).id, getString(R.string.coupon_code) + "  " + couponDetails.get(position).promoCode + "  " + getString(R.string.applied));
                Utils.startActivityPutValue(mContext, OrderDetails.class, orderDetails.get(position).id);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        if (SharedPref.getSP(ConstantField.USER_ID) != null) {

            RequestParams params = CmdParams.orderList();
            apiCall(getString(R.string.please_wait), AppUrl.GET_ORDER_LIST, params, true, ConstantField.WHITCH_1);
        } else {
           no_order.setVisibility(View.VISIBLE);
        }

    }

    private void apiCall(String apiTitle, String apiUrl, RequestParams requestParams, boolean progressBar, int apiWhitch) {
        NetworkManager networkManager = new NetworkManager();
        networkManager.callAPI(mContext, ConstantField.VAL_POST, apiUrl, requestParams, apiTitle, this, progressBar, apiWhitch);
    }




    @Override
    public void onSuccess(boolean success, String response, int which) {
        if (which == ConstantField.WHITCH_1) {
            OrderListModel model = JsonDeserializer.deserializeJson(response, OrderListModel.class);
            if (model.status == 1) {
                if (model.orderDetails != null && model.orderDetails.size() > 0) {
                    no_order.setVisibility(View.GONE);
                    recyclerCuponCode.setVisibility(View.VISIBLE);

                    this.orderDetails = model.orderDetails;
                    OrderListAdapter codeListAdapter = new OrderListAdapter(mContext, model.orderDetails);
                    recyclerCuponCode.setAdapter(codeListAdapter);
                } else {
                    recyclerCuponCode.setVisibility(View.GONE);
                    no_order.setVisibility(View.VISIBLE);
                }

            }

        }
    }

    @Override
    public void onFailure(boolean success, String response, int which) {

    }
}