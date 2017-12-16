package com.anilxpert.food.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anilxpert.food.R;
import com.anilxpert.food.loopjServcice.CmdParams;
import com.anilxpert.food.loopjServcice.ConstantField;
import com.anilxpert.food.loopjServcice.JsonDeserializer;
import com.anilxpert.food.loopjServcice.NetworkManager;
import com.anilxpert.food.models.OrderModel;
import com.anilxpert.food.utils.AppUrl;
import com.anilxpert.food.utils.Utils;
import com.loopj.android.http.RequestParams;

/**
 * Created by AnilXpert 9887230800 on 12-Dec-17.
 */

public class PaymentOrder extends AppCompatActivity implements View.OnClickListener, NetworkManager.onCallback {

    private TextView activity_title;
    private TextView txtPayAMount;
    private Button btnPlaceOrder;
    private Context mContext;

    private String intentData;
    private String intentPrice;
    private String cuponID;

    private String notes;
    private String total_amount;
    private View.OnClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        mContext = PaymentOrder.this;
        intentData = Utils.getintentString(ConstantField.INTENT_DATA, getIntent());
        intentPrice = Utils.getintentString(ConstantField.INTENT_PRICE, getIntent());

        cuponID = Utils.getintentString(ConstantField.INTENT_CUPON_ID, getIntent());

        notes = Utils.getintentString(ConstantField.INTENT_NOTES, getIntent());
        total_amount = Utils.getintentString(ConstantField.INTENT_TOTAL_AMOUNT, getIntent());


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
        activity_title.setText(getString(R.string.n_terms_of_use));

        txtPayAMount = (TextView) findViewById(R.id.txtPayAMount);
        btnPlaceOrder = (Button) findViewById(R.id.btnPlaceOrder);
        txtPayAMount.setText("Amount " + getString(R.string.price) + " " + intentPrice);

        btnPlaceOrder.setOnClickListener(this);

        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.dismissThanksDilog();
                Utils.clearPriviousActivity(mContext, DashBordActivity.class);
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlaceOrder:
                //  Utils.startActivityPutValue(mContext, DashBordActivity.class, "");
                RequestParams params = CmdParams.getParamsPlaceOrder(intentData, total_amount, intentPrice, cuponID, notes);
                apiCall(getString(R.string.please_wait), AppUrl.PLACE_ORDER, params, true, ConstantField.WHITCH_1);

                break;
        }
    }

    private void apiCall(String apiTitle, String apiUrl, RequestParams requestParams, boolean progressBar, int apiWhitch) {
        NetworkManager networkManager = new NetworkManager();
        networkManager.callAPI(mContext, ConstantField.VAL_POST, apiUrl, requestParams, apiTitle, this, progressBar, apiWhitch);
    }

    @Override
    public void onSuccess(boolean success, String response, int which) {
        OrderModel orderModel = JsonDeserializer.deserializeJson(response, OrderModel.class);
        if (orderModel.status == 1) {
            Utils.showThankYouDialog(mContext, clickListener, orderModel.orderId);

        } else {
            Toast.makeText(mContext, orderModel.message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(boolean success, String response, int which) {

    }


}


